package github.ardondev.apuri.ui.anime_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.EpisodeAdapter
import github.ardondev.apuri.adapters.GenreAdapter
import github.ardondev.apuri.databinding.FragmentAnimeDetailBinding
import github.ardondev.apuri.databinding.LayoutEpisodeDetailBinding
import github.ardondev.apuri.network.models.Episode
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.openInYT
import github.ardondev.apuri.utils.setError
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AnimeDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentAnimeDetailBinding
    private val mArgs: AnimeDetailFragmentArgs by navArgs()
    private val mViewModel: AnimeDetailViewModel by inject {
        parametersOf(mArgs.anime?.id)
    }
    private lateinit var mGenreAdapter: GenreAdapter
    private lateinit var mEpisodeAdapter: EpisodeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAnimeDetailBinding.inflate(inflater).apply {
            lifecycleOwner = this@AnimeDetailFragment
            viewModel = this@AnimeDetailFragment.mViewModel
            anime = this@AnimeDetailFragment.mArgs.anime
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
        setObservers()
        setGenreAdapter()
        setEpisodeAdapter()
        setOnClickListeners()
    }


    /*
    UI
     */

    private fun setOnClickListeners() {

        //Back button
        mBinding.animeDetailToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        //Play button
        mBinding.animeDetailPlayBtn.setOnClickListener {
            requireActivity().openInYT(mArgs.anime?.attributes?.youtubeVideoId ?: "")
        }

        //More episodes
        mBinding.animeDetailEpisodesMoreBtn.setOnClickListener {
            findNavController().navigate(
                AnimeDetailFragmentDirections.actionAnimeDetailFragmentToEpisodesFragment(
                    anime = mArgs.anime
                )
            )
        }

    }

    private fun setGenreAdapter() {
        mGenreAdapter = GenreAdapter()
        mBinding.animeDetailGenresRecyclerView.adapter = mGenreAdapter
    }

    private fun setEpisodeAdapter() {
        mEpisodeAdapter = EpisodeAdapter(EpisodeAdapter.OnEpisodeClickListener { episode ->
            showEpisodeDetailBottomSheet(episode)
        })
        mBinding.animeDetailEpisodesRecyclerView.adapter = mEpisodeAdapter
    }

    private fun showEpisodeDetailBottomSheet(episode: Episode) {
        val binding = LayoutEpisodeDetailBinding.inflate(
            LayoutInflater.from(requireContext()), null, false
        ).apply {
            this.episode = episode
        }
        val bottomSheet = BottomSheetDialog(requireContext()).apply {
            setContentView(binding.root)
        }
        binding.episodeDetailCloseBtn.setOnClickListener {
            bottomSheet.dismiss()
        }
        bottomSheet.show()
    }


    /*
    OBSERVERS
     */

    private fun setObservers() {

        //Genres

        mViewModel.genreListResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { genreList ->
                if (genreList.isNotEmpty()) {
                    mGenreAdapter.submitList(genreList)
                } else {
                    mBinding.animeDetailGenresErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })


        //Episodes

        mViewModel.episodeListResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { episodeList ->
                if (episodeList.isNotEmpty()) {
                    mEpisodeAdapter.submitList(episodeList)
                } else {
                    mBinding.animeDetailEpisodesErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })

    }

}