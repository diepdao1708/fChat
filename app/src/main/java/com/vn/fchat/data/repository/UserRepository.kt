package com.vn.fchat.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.hola360.lwac.data.api.RetrofitHelper
import com.vn.fchat.data.api.response.loginresponse.LoginResponse
import com.vn.fchat.data.model.User
import com.vn.fchat.ui.utils.Constants.LOGIN_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {
    private val retrofitHelper: RetrofitHelper = RetrofitHelper.getInstance(context)

    suspend fun checkLogin(
        user: User
    ): LoginResponse? =
        withContext(Dispatchers.Default) {
            try {
                val gson = Gson()
                val json = gson.toJson(user)
                Log.e("----", "checkLogin: $json", )
                retrofitHelper.remoteServices.checkLogin(json)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }
}