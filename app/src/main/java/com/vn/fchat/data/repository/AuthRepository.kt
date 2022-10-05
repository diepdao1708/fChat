package com.vn.fchat.data.repository

import android.util.Log
import com.google.gson.Gson
import com.vn.fchat.data.api.AuthService
import com.vn.fchat.data.api.response.loginresponse.LoginResponse
import com.vn.fchat.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserRepository {
    suspend fun checkLogin(user: User): LoginResponse?
}

class UserRepositoryImpl @Inject constructor(
    private val service: AuthService,
) : UserRepository {
    override suspend fun checkLogin(user: User): LoginResponse? =
        withContext(Dispatchers.Default) {
            try {
                val gson = Gson()
                val json = gson.toJson(user)
                Log.e("----", "checkLogin: $json")
                service.checkLogin(json)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }
}