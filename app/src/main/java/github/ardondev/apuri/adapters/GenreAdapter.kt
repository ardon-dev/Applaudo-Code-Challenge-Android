package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemGenreBinding
import github.ardondev.apuri.network.models.Genre
import github.ardondev.apuri.utils.GenericAdapter

class GenreAdapter() : GenericAdapter<List<Genre>>,
    ListAdapter<Genre, GenreAdapter.GenreViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem === newItem
        }
    }

    class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) {
            binding.executePendingBindings()
            binding.genre = genre
        }

    }

    override fun setData(data: List<Genre>) {
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre)
    }

}