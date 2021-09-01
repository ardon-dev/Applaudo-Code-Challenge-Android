package github.ardondev.apuri.ui.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import github.ardondev.apuri.adapters.EpisodeAdapter
import github.ardondev.apuri.adapters.EpisodePagingAdapter
import github.ardondev.apuri.databinding.FragmentEpisodesBinding
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class EpisodesFragment : Fragment() {

    private lateinit var mBinding: FragmentEpisodesBinding
    private val mArgs: EpisodesFragmentArgs by navArgs()
    private val mViewModel: EpisodesViewModel by inject {
        parametersOf(mArgs.anime)
    }
    private lateinit var mEpisodePagingAdapter: EpisodePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentEpisodesBinding.inflate(inflater).apply {
            lifecycleOwner = this@EpisodesFragment
            viewModel = this@EpisodesFragment.mViewModel
            anime = this@EpisodesFragment.mArgs.anime
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
        setEpisodePagingAdapter()
        setOnClickListeners()
    }


    /*
    UI
     */

    private fun setOnClickListeners() {

        //Back button
        mBinding.episodesToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setEpisodePagingAdapter() {
        mEpisodePagingAdapter = EpisodePagingAdapter(EpisodeAdapter.OnEpisodeClickListener { episode ->

        }).apply {
            //State listener
            addLoadStateListener { loadState ->
                when(loadState.refresh) {
                    is LoadState.Loading -> mViewModel.setEpisodeStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setEpisodeStatus(Status.ERROR)
                        mViewModel.setEpisodeError((loadState.refresh as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setEpisodeStatus(Status.SUCCESS)
                }
                when(loadState.append) {
                    is LoadState.Loading -> mViewModel.setEpisodeStatus(Status.LOADING)
                    is LoadState.Error -> {
                        mViewModel.setEpisodeStatus(Status.ERROR)
                        mViewModel.setEpisodeError((loadState.append as LoadState.Error).error.message)
                    }
                    is LoadState.NotLoading -> mViewModel.setEpisodeStatus(Status.SUCCESS)
                }
            }
        }

        mBinding.episodesRecyclerView.adapter = mEpisodePagingAdapter
        getAllAnimeEpisodes()

    }


    /*
    REQUESTS
     */

    private fun getAllAnimeEpisodes() {
        lifecycleScope.launch {
            mViewModel.getAllAnimeEpisodes().collectLatest { episodeList ->
                mEpisodePagingAdapter.submitData(episodeList)
            }
        }
    }

}