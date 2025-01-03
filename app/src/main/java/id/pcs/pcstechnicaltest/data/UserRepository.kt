package id.pcs.pcstechnicaltest.data

import id.pcs.pcstechnicaltest.data.remote.ApiResponse
import id.pcs.pcstechnicaltest.data.remote.ApiService
import retrofit2.Call
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getAllData(): Call<List<ApiResponse>> {
        return apiService.getAllData()
    }
}