package github.ardondev.apuri.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import github.ardondev.apuri.R

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