package github.ardondev.apuri.ui.anime_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.GenreAdapter
import github.ardondev.apuri.databinding.FragmentAnimeDetailBinding
import github.ardondev.apuri.utils.Status
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

    }

    private fun setGenreAdapter() {
        mGenreAdapter = GenreAdapter()
        mBinding.animeDetailGenresRecyclerView.adapter = mGenreAdapter
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

    }

}