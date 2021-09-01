package github.ardondev.apuri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.ardondev.apuri.databinding.ItemCategoryBinding
import github.ardondev.apuri.network.models.Category
import github.ardondev.apuri.utils.setFullWidth
import github.ardondev.apuri.utils.toDp

class CategoryPagingAdapter(
    private val onCategoryClickListener: CategoryAdapter.OnCategoryClickListener
): PagingDataAdapter<Category, CategoryPagingAdapter.CategoryPagingViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }
    }

    class CategoryPagingViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                this.executePendingBindings()
                this.category = category
                this.categoryItemParentLyt.setFullWidth(
                    marginEnd = 16.toDp(),
                    marginTop = 16.toDp()
                )
                this.categoryItemTitleTxt.setLines(2)
                this.categoryItemTitleTxt.maxLines = 2
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPagingViewHolder {
        return CategoryPagingViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryPagingViewHolder, position: Int) {
        val category = getItem(position)
        category?.let {
            holder.bind(category)
            holder.itemView.setOnClickListener {
                onCategoryClickListener.onClick(category)
            }
        }
    }

}