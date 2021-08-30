package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemCategoryBinding
import github.ardondev.apuri.network.models.Category
import github.ardondev.apuri.utils.GenericAdapter

class CategoryAdapter(
    private val onCategoryClickListener: OnCategoryClickListener
) : GenericAdapter<List<Category>>,
    ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.executePendingBindings()
            binding.category = category
        }

    }

    class OnCategoryClickListener(private val clickListener: (category: Category) -> Unit) {
        fun onClick(category: Category) = clickListener(category)
    }

    override fun setData(data: List<Category>) {
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
        holder.itemView.setOnClickListener {
            onCategoryClickListener.onClick(category)
        }
    }

}