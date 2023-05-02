package com.svyd.data.repository.audio

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface AudioService {

    @GET
    suspend fun audio(@Url url: String) : Response<ResponseBody>

}