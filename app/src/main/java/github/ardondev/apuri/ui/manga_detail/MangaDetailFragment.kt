package github.ardondev.apuri.ui.manga_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import github.ardondev.apuri.R
import github.ardondev.apuri.databinding.FragmentMangaDetailBinding

class MangaDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentMangaDetailBinding
    private val mArgs: MangaDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMangaDetailBinding.inflate(inflater).apply {
            lifecycleOwner = this@MangaDetailFragment
            manga = this@MangaDetailFragment.mArgs.manga
        }
        return mBinding.root
    }

}