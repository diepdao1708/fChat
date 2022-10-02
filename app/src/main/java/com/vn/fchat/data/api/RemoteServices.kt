package com.hola360.lwac.data.api

import com.hola360.lwac.data.api.response.recommendresponse.RecommendResponse
import com.hola360.lwac.data.api.response.submitresponse.SubmitResponse
import com.hola360.lwac.utils.Constants
import com.hola360.lwac.utils.Constants.APP_URL
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteServices {
    @FormUrlEncoded
    @POST(APP_URL)
    suspend fun getRecommendTheme(
        @Field("type") params: String
    ): RecommendResponse

    @FormUrlEncoded
    @POST(APP_URL)
    suspend fun getRecomendList(
        @Field("type") type: String,   @Field("sort") sort: String,   @Field("page") page: Int,   @Field("limit") limit: Int
    ): RecommendResponse

    @FormUrlEncoded
    @POST(APP_URL)
    suspend fun submitTheme(
        @Field("type") type: String,
        @Field("items") items: String
    ) : SubmitResponse?
}