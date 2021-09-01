package github.ardondev.apuri.utils

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import github.ardondev.apuri.databinding.LayoutEpisodeDetailBinding
import github.ardondev.apuri.network.models.Episode

//View visibility

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

//View sizes

fun View.setFullWidth(
    marginStart: Int? = null,
    marginEnd: Int? = null,
    marginBottom: Int? = null,
    marginTop: Int? = null
) {
    val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    marginStart?.let { params.marginStart = it }
    marginEnd?.let { params.marginEnd = it }
    marginTop?.let { params.topMargin = it }
    marginBottom?.let { params.bottomMargin = it }
    this.layoutParams = params
}

fun Int.toDp(): Int {
    val displayMetrics = Resources.getSystem().displayMetrics
    return (this * (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

//Intents

fun Activity.openInYT(videoId: String?) {
    videoId?.let {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$it"))
        )
    }
}

//Dialogs

fun Activity.showEpisodeDetailBottomSheet(episode: Episode) {
    val binding = LayoutEpisodeDetailBinding.inflate(
        LayoutInflater.from(this), null, false
    ).apply {
        this.episode = episode
    }
    val bottomSheet = BottomSheetDialog(this).apply {
        setContentView(binding.root)
    }
    binding.episodeDetailCloseBtn.setOnClickListener {
        bottomSheet.dismiss()
    }
    bottomSheet.show()
}