package com.vn.fchat.ui.login

import androidx.lifecycle.*
import com.vn.fchat.data.api.response.DataResponse
import com.vn.fchat.data.api.response.LoadingStatus
import com.vn.fchat.data.api.response.loginresponse.LoginResponse
import com.vn.fchat.data.model.User
import com.vn.fchat.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var loginResult = MutableLiveData<DataResponse<LoginResponse>>(DataResponse.DataIdle())

    val isLoading: LiveData<Boolean> = Transformations.map(loginResult) {
        loginResult.value!!.loadingStatus == LoadingStatus.Loading
    }

    fun checkLogin(user: User) {
        if (loginResult.value!!.loadingStatus != LoadingStatus.Loading) {
            loginResult.value!!.loadingStatus = LoadingStatus.Loading

            viewModelScope.launch(Dispatchers.IO) {
                val res = userRepository.checkLogin(user)
                if (res != null) {
                    loginResult.postValue(DataResponse.DataSuccess(res))
                } else {
                    loginResult.postValue(DataResponse.DataError())
                }
            }
        }
    }
}