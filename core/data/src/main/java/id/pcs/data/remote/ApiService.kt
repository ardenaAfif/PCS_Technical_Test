package id.pcs.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("getData/test")
    fun getAllData(): Call<List<ApiResponse>>
}
