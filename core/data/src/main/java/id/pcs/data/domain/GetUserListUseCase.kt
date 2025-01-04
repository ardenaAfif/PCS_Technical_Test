package id.pcs.data.domain

import id.pcs.data.UserRepository
import id.pcs.data.remote.ApiResponse
import retrofit2.Call
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(): Call<List<ApiResponse>> {
        return userRepository.getAllData()
    }
}