package github.ardondev.apuri.ui.anime_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import github.ardondev.apuri.R
import github.ardondev.apuri.databinding.FragmentAnimeDetailBinding

class AnimeDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentAnimeDetailBinding
    private val mArgs: AnimeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAnimeDetailBinding.inflate(inflater).apply {
            lifecycleOwner = this@AnimeDetailFragment
            anime = this@AnimeDetailFragment.mArgs.anime
        }
        initFlow()
        return mBinding.root
    }

    private fun initFlow() {
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

}