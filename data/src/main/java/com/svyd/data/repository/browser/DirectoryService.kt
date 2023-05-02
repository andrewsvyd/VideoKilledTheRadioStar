package com.svyd.data.repository.browser

import com.svyd.data.repository.browser.model.DirectoryEntity
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DirectoryService {

    @GET
    suspend fun directory(@Url url: String, @Query("render") string: String = "json") : DirectoryEntity

}