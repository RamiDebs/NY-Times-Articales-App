package debs.cora.nytimesarticlaes.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import debs.cora.nytimesarticlaes.R

fun ImageView.loadImage(imageURL: String) {

    Glide.with(this.context)
        .setDefaultRequestOptions(
            RequestOptions()
                .circleCrop()
        )
        .load(imageURL)
        .placeholder(R.color.colorAccent)
        .into(this)
}

