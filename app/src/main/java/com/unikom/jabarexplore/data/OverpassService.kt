package com.unikom.jabarexplore.data

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OverpassService {
    @POST("interpreter")
    suspend fun getTourismData(@Body query: RequestBody): Response<ResponseBody>
}