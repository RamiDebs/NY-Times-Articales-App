package debs.cora.nytimesarticlaes.utils

import android.content.Context
import android.net.ConnectivityManager

fun isConnectedToNetwork(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
}