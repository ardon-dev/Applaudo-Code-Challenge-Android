package github.ardondev.apuri.ui.anime_all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import github.ardondev.apuri.adapters.AnimeAdapter
import github.ardondev.apuri.adapters.AnimePagingAdapter
import github.ardondev.apuri.databinding.FragmentAnimeAllBinding
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AnimeAllFragment : Fragment() {

    private lateinit var mBinding: FragmentAnimeAllBinding
    private val mArgs: AnimeAllFragmentArgs by navArgs()
    private val mViewModel: AnimeAllViewModel by inject {
        parametersOf(mArgs.category)
    }
    private lateinit var mAnimePagingAdapter: AnimePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAnimeAllBinding.inflate(inflater).apply {
            lifecycleOwner = this@AnimeAllFragment
            viewModel = this@AnimeAllFragment.mViewModel
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
        setObservers()
        setAnimePagingAdapter()
        setupSearchView()
    }


    /*
    UI
     */

    private fun setAnimePagingAdapter() {
        mAnimePagingAdapter = AnimePagingAdapter(AnimeAdapter.OnAnimeClickListener { anime ->

        }).apply {
            //State listener
            addLoadStateListener { loadState ->
                when(loadState.refresh) {
                    is LoadState.Loading -> mViewModel.setAnimeListStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setAnimeListStatus(Status.ERROR)
                        mViewModel.setAnimeListError((loadState.refresh as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setAnimeListStatus(Status.SUCCESS)
                }
                when(loadState.append) {
                    is LoadState.Loading -> mViewModel.setAnimeListStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setAnimeListStatus(Status.ERROR)
                        mViewModel.setAnimeListError((loadState.append as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setAnimeListStatus(Status.SUCCESS)
                }
            }
        }

        mBinding.animeAllRecyclerView.adapter = mAnimePagingAdapter
        getAllAnime()

    }

    private fun setupSearchView() {
        mBinding.animeAllSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mViewModel.searchQuery.postValue(p0)
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0?.isEmpty() == true) {
                    mViewModel.searchQuery.postValue(null)
                    getAllAnime()
                }
                return true
            }
        })
    }


    /*
    REQUESTS
     */

    private fun getAllAnime() {
        lifecycleScope.launch {
            mViewModel.getAllAnime().collectLatest { animeList ->
                mAnimePagingAdapter.submitData(animeList)
            }
        }
    }


    /*
    OBSERVERS
     */

    private fun setObservers() {

        //Search query
        mViewModel.searchQuery.observe(viewLifecycleOwner, Observer { searchQuery ->
            getAllAnime()
        })

    }

}