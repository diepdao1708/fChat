package com.vn.fchat.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.vn.fchat.data.api.response.DataResponse
import com.vn.fchat.data.api.response.LoadingStatus
import com.vn.fchat.data.api.response.loginresponse.LoginResponse
import com.vn.fchat.data.model.User
import com.vn.fchat.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(val app: Application) : ViewModel() {
    private val userRepository = UserRepository(app)
    var loginResult = MutableLiveData<DataResponse<LoginResponse>>(DataResponse.DataIdle())

    val isLoading: LiveData<Boolean> = Transformations.map(loginResult) {
        loginResult.value!!.loadingStatus == LoadingStatus.Loading
    }

    fun checkLogin(user: User) {
        if (loginResult.value!!.loadingStatus != LoadingStatus.Loading) {
            loginResult.value!!.loadingStatus = LoadingStatus.Loading

            viewModelScope.launch(Dispatchers.IO) {
                val res =  userRepository.checkLogin(user)
                Log.e("----", "checkLogin: $res", )
                if (res != null) {
                    loginResult.postValue(DataResponse.DataSuccess(res))
                }
                else {
                    loginResult.postValue(DataResponse.DataError())
                }
            }
        }
    }

    class Factory(private val app: Application) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(app) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}