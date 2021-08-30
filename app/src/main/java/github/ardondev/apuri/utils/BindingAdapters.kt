package github.ardondev.apuri.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import github.ardondev.apuri.R
import kotlin.random.Random

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