package Pojo


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@SuppressLint("ParcelCreator")
@SuppressWarnings("serial")
data class Result (
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("section")
    val section: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("abstract")
    val `abstract`: String? = null,
    @SerializedName("published_date")
    val publishedDate: String? = null,
    @SerializedName("source")
    val source: String? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("views")
    val views: Int? = null,
    @SerializedName("media")
    val media: List<Media?>? = null,
    @SerializedName("column")
    val column: String? = null
)  : Serializable

