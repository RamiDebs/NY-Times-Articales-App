package NetworkHelper

import Pojo.NYTimesResponse
import retrofit2.Call
import retrofit2.http.GET

private const val API_KEY: String="Z6Z7Dr6BpeIdZYa0IRKAzG9oUwI0FNga"

interface NYTimesAPIServer {
    @GET("/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=$API_KEY")
    fun getRepos(): Call<NYTimesResponse>
}