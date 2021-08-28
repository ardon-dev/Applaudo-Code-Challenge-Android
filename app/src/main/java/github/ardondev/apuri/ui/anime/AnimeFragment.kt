package github.ardondev.apuri.ui.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import github.ardondev.apuri.R
import github.ardondev.apuri.adapters.AnimeAdapter
import github.ardondev.apuri.databinding.FragmentAnimeBinding

class AnimeFragment : Fragment() {

    private lateinit var mBinding: FragmentAnimeBinding

    private lateinit var mAnimeAdapter: AnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAnimeBinding.inflate(inflater).apply {
            lifecycleOwner = this@AnimeFragment
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
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

}