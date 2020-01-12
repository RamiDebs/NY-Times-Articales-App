package Pojo


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@SuppressLint("ParcelCreator")
@SuppressWarnings("serial")
data class MediaMetadata(
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("format")
    val format: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("width")
    val width: Int? = null
) : Serializable