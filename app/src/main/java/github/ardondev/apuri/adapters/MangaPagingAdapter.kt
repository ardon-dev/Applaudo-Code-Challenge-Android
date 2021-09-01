package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemMangaBinding
import github.ardondev.apuri.network.models.Manga
import github.ardondev.apuri.utils.setFullWidth
import github.ardondev.apuri.utils.toDp

class MangaPagingAdapter(
    private val onMangaClickListener: MangaAdapter.OnMangaClickListener
) : PagingDataAdapter<Manga, MangaPagingAdapter.MangaPagingViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Manga>() {
        override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem === newItem
        }
    }

    class MangaPagingViewHolder(private val binding: ItemMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(manga: Manga) {
            binding.executePendingBindings()
            binding.manga = manga
            binding.itemMangeParentLyt.setFullWidth(
                marginTop = 8.toDp(),
                marginEnd = 8.toDp()
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaPagingViewHolder {
        return MangaPagingViewHolder(
            ItemMangaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MangaPagingViewHolder, position: Int) {
        val manga = getItem(position)
        manga?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener {
                onMangaClickListener.onClick(manga)
            }
        }
    }

}