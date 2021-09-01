package github.ardondev.apuri.ui.manga_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.ChapterAdapter
import github.ardondev.apuri.adapters.GenreAdapter
import github.ardondev.apuri.databinding.FragmentMangaDetailBinding
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.setError
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MangaDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentMangaDetailBinding
    private val mArgs: MangaDetailFragmentArgs by navArgs()
    private val mViewModel: MangaDetailViewModel by inject {
        parametersOf(mArgs.manga)
    }
    private lateinit var mGenreAdapter: GenreAdapter
    private lateinit var mChapterAdapter: ChapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMangaDetailBinding.inflate(inflater).apply {
            lifecycleOwner = this@MangaDetailFragment
            manga = this@MangaDetailFragment.mArgs.manga
            viewModel = this@MangaDetailFragment.mViewModel
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
        setObservers()
        setGenreAdapter()
        setChapterAdapter()
    }


    /*
    UI
     */

    private fun setGenreAdapter() {
        mGenreAdapter = GenreAdapter()
        mBinding.mangaDetailGenresRecyclerView.adapter = mGenreAdapter
    }

    private fun setChapterAdapter() {
        mChapterAdapter = ChapterAdapter()
        mBinding.animeDetailChaptersRecyclerView.adapter = mChapterAdapter
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
                    mBinding.mangaDetailGenresErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })


        //Chapters

        mViewModel.chapterListResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { chapterList ->
                if (chapterList.isNotEmpty()) {
                    mChapterAdapter.submitList(chapterList)
                } else {
                    mBinding.animeDetailChaptersErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })

    }

}