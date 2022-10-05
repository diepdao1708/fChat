package com.vn.fchat.data.api

import com.vn.fchat.data.api.response.loginresponse.LoginResponse
import com.vn.fchat.ui.utils.Constants.LOGIN_URL
import retrofit2.http.*

interface AuthService {

    @POST(LOGIN_URL)
    suspend fun checkLogin(@Body user: String): LoginResponse

}