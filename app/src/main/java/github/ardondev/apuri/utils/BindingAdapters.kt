package github.ardondev.apuri.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import github.ardondev.apuri.R
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.network.models.Manga

@BindingAdapter("app:setRemoteImage")
fun ImageView.setRemoteImage(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.ic_anime)
            .into(this)
    }
}

@BindingAdapter("app:setProgressVisibility")
fun View.setProgressVisibility(status: Status?) {
    status?.let {
        when (it) {
            Status.LOADING -> this.setVisible()
            else -> this.setGone()
        }
    }
}

@BindingAdapter("status", "message")
fun TextView.setError(status: Status?, message: String?) {
    status?.let {
        message?.let {
            when (status) {
                Status.ERROR -> {
                    this.setVisible()
                    this.text = message
                }
                else -> {
                    this.setGone()
                }
            }
        }
    }
}

@BindingAdapter("app:setRandomStrokeColor")
fun MaterialCardView.setRandomStrokeColor(colorize: Boolean?) {
    colorize?.let {
        val randomNumber = java.util.Random().nextInt(0xffffff + 1)
        val color = String.format("#%06x", randomNumber)
        this.strokeColor = Color.parseColor(color)
    }
}

@BindingAdapter("app:setAnimeStatus")
fun TextView.setAnimeStatus(anime: Anime?) {
    anime?.let {
        val startYear = it.attributes?.startDate?.substring(0, 4)
        val endYear = if (it.attributes?.endDate != null) {
            "- ${it.attributes.endDate.substring(0, 4)}"
        } else {
            ""
        }
        val status = it.attributes?.status
        this.text = "$startYear $endYear ($status)"
    }
}

@BindingAdapter("app:setMangaStatus")
fun TextView.setMangaStatus(manga: Manga?) {
    manga?.let {
        val startYear = it.attributes?.startDate?.substring(0, 4)
        val endYear = if (it.attributes?.endDate != null) {
            "- ${it.attributes.endDate.substring(0, 4)}"
        } else {
            ""
        }
        val status = it.attributes?.status
        this.text = "$startYear $endYear ($status)"
    }
}

@BindingAdapter("app:setRating")
fun AppCompatRatingBar.setRating(rating: String?) {
    rating?.let {
        this.max = 100
        this.progress = it.toDouble().toInt()
    }
}