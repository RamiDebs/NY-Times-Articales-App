package NetworkHelper

import model.NYTimesResponse
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import model.Result
import androidx.lifecycle.ViewModel

class ArticlesViewModel : ViewModel() {

    private val TAG = "ArticlesRepository"
    internal val mArticleLiveData = MutableLiveData<List<Result>>()

    val NYTimesAPIServer =
        RetrofitClient.retrofitInstance?.create<NYTimesAPIServer>(
            NYTimesAPIServer::class.java)
    
    fun createArticleLiveData(): MutableLiveData<List<Result>> {
        NYTimesAPIServer?.getRepos()
            ?.enqueue(object : Callback<NYTimesResponse> {
                override fun onResponse(call: Call<NYTimesResponse>, response: Response<NYTimesResponse>) {
                    Log.d(TAG, "call done. with Response " + response.body())
                    val allServerNYTimesResponse = response.body()
                    if (allServerNYTimesResponse != null) {
                            mArticleLiveData.value = allServerNYTimesResponse.results as List<Result>?
                         }
                }

                override fun onFailure(call: Call<NYTimesResponse>?, t: Throwable?) {
                    Log.e(TAG, "call failed. with message" + t?.message)
                }
            })
        return mArticleLiveData
    }

    fun getMutableLiveData(): MutableLiveData<List<Result>> {return mArticleLiveData}

}