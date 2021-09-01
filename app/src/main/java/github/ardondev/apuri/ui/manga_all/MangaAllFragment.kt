package github.ardondev.apuri.ui.manga_all

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
import github.ardondev.apuri.adapters.MangaAdapter
import github.ardondev.apuri.adapters.MangaPagingAdapter
import github.ardondev.apuri.databinding.FragmentMangaAllBinding
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MangaAllFragment : Fragment() {

    private lateinit var mBinding: FragmentMangaAllBinding
    private val mArgs: MangaAllFragmentArgs by navArgs()
    private val mViewModel: MangaAllViewModel by inject {
        parametersOf(mArgs.category)
    }
    private lateinit var mMangaPagingAdapter: MangaPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMangaAllBinding.inflate(inflater).apply {
            lifecycleOwner = this@MangaAllFragment
            viewModel = this@MangaAllFragment.mViewModel
            categoryName = this@MangaAllFragment.mArgs.category
        }
        initFLow()
        return mBinding.root
    }

    private fun initFLow() {
        setObservers()
        setMangaPagingAdapter()
        setupSearchView()
        setOnClickListeners()
    }


    /*
    UI
     */

    private fun setOnClickListeners() {

        //Back button
        mBinding.mangaAllToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setMangaPagingAdapter() {
        mMangaPagingAdapter = MangaPagingAdapter(MangaAdapter.OnMangaClickListener { manga ->
            //Navigate to detail
            findNavController().navigate(
                MangaAllFragmentDirections.actionMangaAllFragmentToMangaDetailFragment(manga)
            )
        }).apply {
            //State listener
            addLoadStateListener { loadState ->
                when(loadState.refresh) {
                    is LoadState.Loading -> mViewModel.setMangaListStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setMangaListStatus(Status.ERROR)
                        mViewModel.setMangaListError((loadState.refresh as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setMangaListStatus(Status.SUCCESS)
                }
                when(loadState.append) {
                    is LoadState.Loading -> mViewModel.setMangaListStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setMangaListStatus(Status.ERROR)
                        mViewModel.setMangaListError((loadState.append as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setMangaListStatus(Status.SUCCESS)
                }
            }
        }

        mBinding.mangaAllRecyclerView.adapter = mMangaPagingAdapter
        getAllManga()

    }

    private fun setupSearchView() {
        mBinding.mangaAllSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mViewModel.searchQuery.postValue(p0)
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0?.isEmpty() == true) {
                    mViewModel.searchQuery.postValue(null)
                    getAllManga()
                }
                return true
            }
        })
    }


    /*
    REQUESTS
     */

    private fun getAllManga() {
        lifecycleScope.launch {
            mViewModel.getAllManga().collectLatest { mangaList ->
                mMangaPagingAdapter.submitData(mangaList)
            }
        }
    }


    /*
    OBSERVERS
     */

    private fun setObservers() {

        //Search query
        mViewModel.searchQuery.observe(viewLifecycleOwner, Observer { searchQuery ->
            getAllManga()
        })

    }

}