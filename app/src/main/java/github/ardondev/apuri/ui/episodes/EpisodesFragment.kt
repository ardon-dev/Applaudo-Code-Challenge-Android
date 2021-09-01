package github.ardondev.apuri.ui.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import github.ardondev.apuri.R
import github.ardondev.apuri.databinding.FragmentEpisodesBinding

class EpisodesFragment : Fragment() {

    private lateinit var mBinding: FragmentEpisodesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentEpisodesBinding.inflate(inflater).apply {
            lifecycleOwner = this@EpisodesFragment
        }
        return mBinding.root
    }

}