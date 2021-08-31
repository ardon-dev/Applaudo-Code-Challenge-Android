package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemMangaBinding
import github.ardondev.apuri.network.models.Manga
import github.ardondev.apuri.utils.GenericAdapter

class MangaAdapter(
    private val onMangaClickListener: OnMangaClickListener
) : GenericAdapter<List<Manga>>,
    ListAdapter<Manga, MangaAdapter.MangaViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Manga>() {
        override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem === newItem
        }
    }

    class OnMangaClickListener(private val clickListener: (manga: Manga) -> Unit) {
        fun onClick(manga: Manga) = clickListener(manga)
    }

    class MangaViewHolder(private val binding: ItemMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(manga: Manga) {
            binding.executePendingBindings()
            binding.manga = manga
        }

    }

    override fun setData(data: List<Manga>) {
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        return MangaViewHolder(
            ItemMangaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = getItem(position)
        holder.bind(manga)
        holder.itemView.setOnClickListener {
            onMangaClickListener.onClick(manga)
        }
    }

}