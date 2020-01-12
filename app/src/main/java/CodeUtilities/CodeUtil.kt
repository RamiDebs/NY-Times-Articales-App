package CodeUtilities

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import debs.cora.nytimesarticlaes.R
import java.util.*


object CodeUtil {

    fun isConnectedToNetwork(context : Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

    fun loadImage(imageView: ImageView, imageURL: String) {

        Glide.with(imageView.context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .circleCrop()
            )
            .load(imageURL)
            .placeholder(R.color.colorAccent)
            .into(imageView)
    }

}