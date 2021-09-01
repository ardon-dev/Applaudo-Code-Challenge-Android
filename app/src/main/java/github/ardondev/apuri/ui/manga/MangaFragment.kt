package github.ardondev.apuri.ui.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.CategoryAdapter
import github.ardondev.apuri.adapters.MangaAdapter
import github.ardondev.apuri.databinding.FragmentMangaBinding
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.setError
import org.koin.android.ext.android.inject

class MangaFragment : Fragment() {

    private lateinit var mBinding: FragmentMangaBinding
    private val mViewModel: MangaViewModel by inject()
    private lateinit var mCategoryAdapter: CategoryAdapter
    private lateinit var mMangaAdapter: MangaAdapter
    private lateinit var mTrendingMangaAdapter: MangaAdapter

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
        setMangaAdapter()
        setTrendingMangaAdapter()
        setOnClickListeners()
    }


    /*
    UI
     */

    private fun setOnClickListeners() {

        //See more manga
        mBinding.mangaSeeMoreButton.setOnClickListener {
            findNavController().navigate(
                MangaFragmentDirections.actionMangaFragmentToMangaAllFragment(
                    category = null,
                    categoryName = null
                )
            )
        }

        //See all categories
        mBinding.mangaCategoriesSeeMoreButton.setOnClickListener {
            findNavController().navigate(
                MangaFragmentDirections.actionMangaFragmentToCategoriesFragment(
                    isManga = true
                )
            )
        }

    }

    private fun setCategoryAdapter() {
        mCategoryAdapter = CategoryAdapter(CategoryAdapter.OnCategoryClickListener { category ->
            //Send to manga all fragment to filter by category
            findNavController().navigate(MangaFragmentDirections.actionMangaFragmentToMangaAllFragment(
                category = category.attributes?.slug,
                categoryName = category.attributes?.title
            ))
        })
        mBinding.mangaCategoriesRecyclerView.adapter = mCategoryAdapter
    }

    private fun setMangaAdapter() {
        mMangaAdapter = MangaAdapter(MangaAdapter.OnMangaClickListener { manga ->
            //Navigate to details
            findNavController().navigate(
                MangaFragmentDirections.actionMangaFragmentToMangaDetailFragment(
                    manga
                )
            )
        })
        mBinding.mangaRecyclerView.adapter = mMangaAdapter
    }

    private fun setTrendingMangaAdapter() {
        mTrendingMangaAdapter = MangaAdapter(MangaAdapter.OnMangaClickListener { manga ->
            //Navigate to details
            findNavController().navigate(
                MangaFragmentDirections.actionMangaFragmentToMangaDetailFragment(
                    manga
                )
            )
        })
        mBinding.mangaTrendingRecyclerView.adapter = mTrendingMangaAdapter
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


        //Manga

        mViewModel.mangaListResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { mangaList ->
                if (mangaList.isNotEmpty()) {
                    mMangaAdapter.submitList(mangaList)
                } else {
                    mBinding.mangaCategoriesErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })


        //Trending

        mViewModel.trendingMangaResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { mangaList ->
                if (mangaList.isNotEmpty()) {
                    mTrendingMangaAdapter.submitList(mangaList)
                } else {
                    mBinding.mangaTrendingErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })

    }

}