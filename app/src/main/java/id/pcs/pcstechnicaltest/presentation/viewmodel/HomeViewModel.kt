package id.pcs.pcstechnicaltest.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pcs.pcstechnicaltest.data.UserRepository
import id.pcs.pcstechnicaltest.data.remote.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _userData = MutableLiveData<List<ApiResponse>>()
    val userData: LiveData<List<ApiResponse>> get() = _userData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchData()
    }

    private fun fetchData() {
        _loading.value = true
        userRepository.getAllData().enqueue(object : Callback<List<ApiResponse>> {
            override fun onResponse(
                call: Call<List<ApiResponse>>,
                response: Response<List<ApiResponse>>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _userData.value = response.body()
                } else {
                    _error.value = "Failed to fetch data"
                }
            }

            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                _loading.value = false
                _error.value = t.message
            }

        })
    }
}