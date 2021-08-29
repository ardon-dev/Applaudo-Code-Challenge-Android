package github.ardondev.apuri.ui.anime_all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.AnimeAdapter
import github.ardondev.apuri.adapters.AnimePagingAdapter
import github.ardondev.apuri.databinding.FragmentAnimeAllBinding
import github.ardondev.apuri.ui.anime.AnimeViewModel
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.setError
import github.ardondev.apuri.utils.setGone
import github.ardondev.apuri.utils.setVisible
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AnimeAllFragment : Fragment() {

    private lateinit var mBinding: FragmentAnimeAllBinding
    private val mViewModel: AnimeAllViewModel by inject()
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
        setAnimePagingAdapter()
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

}