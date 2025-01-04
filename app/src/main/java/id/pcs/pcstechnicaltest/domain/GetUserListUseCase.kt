package id.pcs.pcstechnicaltest.domain

import id.pcs.pcstechnicaltest.data.UserRepository
import id.pcs.pcstechnicaltest.data.remote.ApiResponse
import retrofit2.Call
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(): Call<List<ApiResponse>> {
        return userRepository.getAllData()
    }
}