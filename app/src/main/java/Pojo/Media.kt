package Pojo


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Media(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("subtype")
    val subtype: String? = null,
    @SerializedName("caption")
    val caption: String? = null,
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int? = null,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata?>? = null
)