package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemAnimeBinding
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.utils.GenericAdapter

class AnimeAdapter(
    private val onAnimeClickListener: OnAnimeClickListener
) : GenericAdapter<List<Anime>>,
    ListAdapter<Anime, AnimeAdapter.AnimeViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Anime>() {
        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem === newItem
        }
    }

    class OnAnimeClickListener(private val clickListener: (anime: Anime) -> Unit) {
        fun onClick(anime: Anime) = clickListener(anime)
    }

    class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(anime: Anime) {
            binding.executePendingBindings()
            binding.anime = anime
        }

    }

    override fun setData(data: List<Anime>) {
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
        holder.itemView.setOnClickListener {
            onAnimeClickListener.onClick(anime)
        }
    }

}