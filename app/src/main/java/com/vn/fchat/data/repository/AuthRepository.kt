package com.vn.fchat.data.repository

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
                val body = HashMap<String, String>()
                body["PhoneNum"] = user.PhoneNum
                body["UserName"] = user.UserName
                body["Password"] = user.Password
                service.checkLogin(body)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }
}