package com.hola360.lwac.data.api

import android.content.Context
import com.hola360.lwac.data.api.base.BaseRetrofitHelper
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vn.fchat.data.api.RemoteServices
import com.vn.fchat.ui.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper private constructor(context: Context) : BaseRetrofitHelper(context) {
    var remoteServices: RemoteServices

    init {
        GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient!!).build()

        remoteServices = retrofit.create(RemoteServices::class.java)
    }

    companion object {
        fun getInstance(context: Context): RetrofitHelper {
            return RetrofitHelper(context)
        }
    }
}