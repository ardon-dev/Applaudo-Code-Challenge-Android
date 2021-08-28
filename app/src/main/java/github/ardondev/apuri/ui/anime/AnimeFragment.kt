package github.ardondev.apuri.ui.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.AnimeAdapter
import github.ardondev.apuri.databinding.FragmentAnimeBinding
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.setError
import github.ardondev.apuri.utils.setGone
import github.ardondev.apuri.utils.setVisible
import org.koin.android.ext.android.inject

class AnimeFragment : Fragment() {

    private lateinit var mBinding: FragmentAnimeBinding
    private val mViewModel: AnimeViewModel by inject()

    private lateinit var mAnimeAdapter: AnimeAdapter

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
    }


    /*
    UI
     */

    private fun setAnimeAdapter() {
        mAnimeAdapter = AnimeAdapter(AnimeAdapter.OnAnimeClickListener { anime ->
            //Navigate to detail
        })
        mBinding.animeRecyclerView.adapter = mAnimeAdapter
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

    }

}