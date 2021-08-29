package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemEpisodeBinding
import github.ardondev.apuri.network.models.Episode
import github.ardondev.apuri.utils.GenericAdapter

class EpisodeAdapter() : GenericAdapter<List<Episode>>,
    ListAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem === newItem
        }
    }

    class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: Episode) {
            binding.executePendingBindings()
            binding.episode = episode
        }

    }

    override fun setData(data: List<Episode>) {
        submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeAdapter.EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeAdapter.EpisodeViewHolder, position: Int) {
        val episode = getItem(position)
        holder.bind(episode)
    }

}