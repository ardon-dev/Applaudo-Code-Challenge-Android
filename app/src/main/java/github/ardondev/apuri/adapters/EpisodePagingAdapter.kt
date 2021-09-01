package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemEpisodeBinding
import github.ardondev.apuri.network.models.Episode

class EpisodePagingAdapter(
    private val onEpisodeClickListener: EpisodeAdapter.OnEpisodeClickListener
) : PagingDataAdapter<Episode, EpisodePagingAdapter.EpisodePagingViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem === newItem
        }
    }

    class EpisodePagingViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: Episode) {
            binding.executePendingBindings()
            binding.episode = episode
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodePagingViewHolder {
        return EpisodePagingViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodePagingViewHolder, position: Int) {
        val episode = getItem(position)
        episode?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener {
                onEpisodeClickListener.onClick(episode)
            }
        }
    }

}