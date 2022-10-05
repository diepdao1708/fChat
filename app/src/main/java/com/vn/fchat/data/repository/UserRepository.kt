package com.vn.fchat.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vn.fchat.data.api.RetrofitHelper
import com.vn.fchat.data.api.response.loginresponse.LoginResponse
import com.vn.fchat.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class UserRepository(context: Context) {
    private val retrofitHelper: RetrofitHelper = RetrofitHelper.getInstance(context)

    suspend fun checkLogin(
        user: User
    ): LoginResponse? =
        withContext(Dispatchers.Default) {
            try {
                val body = HashMap<String, String>()
                body["PhoneNum"] = user.PhoneNum
                body["UserName"] = user.UserName
                body["Password"] = user.Password
                retrofitHelper.remoteServices.checkLogin(body)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }
}