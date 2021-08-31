package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemAnimeBinding
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.utils.setFullWidth
import github.ardondev.apuri.utils.toDp

class AnimePagingAdapter(
    private val onAnimeClickListener: AnimeAdapter.OnAnimeClickListener
) :
    PagingDataAdapter<Anime, AnimePagingAdapter.AnimePagingViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Anime>() {
        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem === newItem
        }
    }

    class AnimePagingViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(anime: Anime) {
            binding.executePendingBindings()
            binding.anime = anime
            binding.animeItemParentLyt.setFullWidth(
                marginTop = 8.toDp(),
                marginEnd = 8.toDp()
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimePagingViewHolder {
        return AnimePagingViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimePagingViewHolder, position: Int) {
        val anime = getItem(position)
        anime?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener {
                onAnimeClickListener.onClick(anime)
            }
        }
    }

}