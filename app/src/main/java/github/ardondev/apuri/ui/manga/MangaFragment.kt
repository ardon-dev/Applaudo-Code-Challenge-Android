package github.ardondev.apuri.ui.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.CategoryAdapter
import github.ardondev.apuri.databinding.FragmentMangaBinding
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.setError
import org.koin.android.ext.android.inject

class MangaFragment : Fragment() {

    private lateinit var mBinding: FragmentMangaBinding
    private val mViewModel: MangaViewModel by inject()
    private lateinit var mCategoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMangaBinding.inflate(inflater).apply {
            lifecycleOwner = this@MangaFragment
            viewModel = this@MangaFragment.mViewModel
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
        setObservers()
        setCategoryAdapter()
    }


    /*
    UI
     */

    private fun setCategoryAdapter() {
        mCategoryAdapter = CategoryAdapter(CategoryAdapter.OnCategoryClickListener { category ->  })
        mBinding.mangaCategoriesRecyclerView.adapter = mCategoryAdapter
    }


    /*
    OBSERVERS
     */

    private fun setObservers() {

        //Categories

        mViewModel.categoryListResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { categoryList ->
                if (categoryList.isNotEmpty()) {
                    mCategoryAdapter.submitList(categoryList)
                } else {
                    mBinding.mangaCategoriesErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })

    }

}