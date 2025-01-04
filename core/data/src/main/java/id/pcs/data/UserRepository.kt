package id.pcs.data

import id.pcs.data.remote.ApiResponse
import id.pcs.data.remote.ApiService
import retrofit2.Call
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getAllData(): Call<List<ApiResponse>> {
        return apiService.getAllData()
    }
}