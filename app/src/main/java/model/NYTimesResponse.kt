package model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class NYTimesResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("num_results")
    val numResults: Int? = null,
    @SerializedName("results")
    val results: List<Result?>? = null
)