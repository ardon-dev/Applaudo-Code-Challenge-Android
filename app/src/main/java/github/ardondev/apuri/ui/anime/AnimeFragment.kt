package github.ardondev.apuri.ui.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.AnimeAdapter
import github.ardondev.apuri.databinding.FragmentAnimeBinding
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.setError
import org.koin.android.ext.android.inject

class AnimeFragment : Fragment() {

    private lateinit var mBinding: FragmentAnimeBinding
    private val mViewModel: AnimeViewModel by inject()

    private lateinit var mAnimeAdapter: AnimeAdapter
    private lateinit var mAnimeTrendingAdapter: AnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAnimeBinding.inflate(inflater).apply {
            lifecycleOwner = this@AnimeFragment
            viewModel = this@AnimeFragment.mViewModel
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
        setObservers()
        setAnimeAdapter()
        setAnimeTrendingAdapter()
        setOnClickListener()
    }


    /*
    UI
     */

    private fun setOnClickListener() {

        //See more anime button
        mBinding.animeSeeMoreButton.setOnClickListener {
            findNavController().navigate(AnimeFragmentDirections.actionAnimeFragmentToAnimeAllFragment())
        }

    }

    private fun setAnimeAdapter() {
        mAnimeAdapter = AnimeAdapter(AnimeAdapter.OnAnimeClickListener { anime ->
            //Navigate to detail
        })
        mBinding.animeRecyclerView.adapter = mAnimeAdapter
    }

    private fun setAnimeTrendingAdapter() {
        mAnimeTrendingAdapter = AnimeAdapter((AnimeAdapter.OnAnimeClickListener { anime ->

        }))
        mBinding.animeTrendingRecyclerView.adapter = mAnimeTrendingAdapter
    }


    /*
    OBSERVERS
     */

    private fun setObservers() {

        //Anime

        mViewModel.animeListResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { animeList ->
                if (animeList.isNotEmpty()) {
                    mAnimeAdapter.submitList(animeList)
                } else {
                    mBinding.animeErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })


        //Trending

        mViewModel.animeTrendingResponse.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { trendingList ->
                if (trendingList.isNotEmpty()) {
                    mAnimeTrendingAdapter.submitList(trendingList)
                } else {
                    mBinding.animeTrendingErrorTxt.setError(
                        status = Status.ERROR,
                        message = getString(R.string.txt_empty_list)
                    )
                }
            }
        })

    }

}