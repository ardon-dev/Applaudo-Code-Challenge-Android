package github.ardondev.apuri.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.CategoryAdapter
import github.ardondev.apuri.adapters.CategoryPagingAdapter
import github.ardondev.apuri.databinding.FragmentCategoriesBinding
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CategoriesFragment : Fragment() {

    private lateinit var mBinding: FragmentCategoriesBinding
    private val mViewModel: CategoriesViewModel by inject()
    private val mArgs: CategoriesFragmentArgs by navArgs()
    private lateinit var mCategoryPagingAdapter: CategoryPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCategoriesBinding.inflate(inflater).apply {
            lifecycleOwner = this@CategoriesFragment
            viewModel = this@CategoriesFragment.mViewModel
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
        setCategoryPagingAdapter()
        setOnClickListener()
    }


    /*
    UI
     */

    private fun setOnClickListener() {

        //Back button
        mBinding.categoriesToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setCategoryPagingAdapter() {
        mCategoryPagingAdapter = CategoryPagingAdapter(CategoryAdapter.OnCategoryClickListener { category ->
            //Check if isManga argument is true, to navigate to manga all fragment
            if (mArgs.isManga) {
                findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToMangaAllFragment(
                    category = category.attributes?.slug,
                    categoryName = category.attributes?.title
                ))
            } else {
                //Go to anime all fragment
                findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToAnimeAllFragment(
                    category = category.attributes?.slug,
                    categoryName = category.attributes?.title
                ))
            }
        }).apply {
            //State listener
            addLoadStateListener { loadState ->
                when(loadState.refresh) {
                    is LoadState.Loading -> mViewModel.setCategoryListStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setCategoryListStatus(Status.ERROR)
                        mViewModel.setCategoryListError((loadState.refresh as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setCategoryListStatus(Status.SUCCESS)
                }
                when(loadState.append) {
                    is LoadState.Loading -> mViewModel.setCategoryListStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setCategoryListStatus(Status.ERROR)
                        mViewModel.setCategoryListError((loadState.append as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setCategoryListStatus(Status.SUCCESS)
                }
            }
        }
        mBinding.categoriesRecyclerView.adapter = mCategoryPagingAdapter
        getAllCategories()
    }

    /*
    REQUESTS
     */

    private fun getAllCategories() {
        lifecycleScope.launch {
            mViewModel.getAllCategories().collectLatest { categoryList ->
                mCategoryPagingAdapter.submitData(categoryList)
            }
        }
    }
    
}