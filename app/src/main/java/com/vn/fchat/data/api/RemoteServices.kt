package com.vn.fchat.data.api

import com.vn.fchat.ui.utils.Constants.LOGIN_URL
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemoteServices {
    @FormUrlEncoded
    @POST(LOGIN_URL)
    suspend fun checkLogin(
        @Field("type") type: String,
        @Field("user") user: String
    ): String
}