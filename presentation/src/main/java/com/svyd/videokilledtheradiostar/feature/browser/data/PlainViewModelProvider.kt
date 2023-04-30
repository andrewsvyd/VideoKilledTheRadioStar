package com.svyd.videokilledtheradiostar.feature.browser.data

import com.google.gson.Gson
import com.svyd.data.repository.ApiConstants
import com.svyd.data.repository.DirectoryService
import com.svyd.data.repository.NetworkDirectoryRepository
import com.svyd.data.repository.model.mapper.DirectoryMapper
import com.svyd.domain.common.exception.ErrorMapper
import com.svyd.domain.common.interactor.Interactor
import com.svyd.domain.common.interactor.ParametrizedInteractor
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.interactor.DirectoryInteractor
import com.svyd.domain.interactor.RootDirectoryInteractor
import com.svyd.domain.repository.DirectoryRepository
import com.svyd.domain.repository.model.Directory
import com.svyd.videokilledtheradiostar.feature.browser.model.UiDirectory
import com.svyd.videokilledtheradiostar.feature.browser.model.mapper.UiDirectoryMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * A service locator, used as a temporary solution  to save time.
 * Todo: migrate to Hilt or Dagger instead
 */

class PlainViewModelProvider {

    fun provideDirectoryInteractor() : ParametrizedInteractor<Directory, String> {
        return DirectoryInteractor(provideRepository(provideService(provideRetrofit()), DirectoryMapper()), ErrorMapper())
    }

    fun provideRootDirectoryInteractor() : Interactor<Directory> {
        return RootDirectoryInteractor(provideRepository(provideService(provideRetrofit()), DirectoryMapper()), ErrorMapper())
    }

    fun provideMapper() : TypeMapper<Directory, UiDirectory> {
        return UiDirectoryMapper()
    }

    private fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(ApiConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.WRITE_TIME_OUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.Paths.ROOT_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .build()
    }

    private fun provideService(retrofit: Retrofit): DirectoryService {
        return retrofit.create(DirectoryService::class.java)
    }

    private fun provideRepository(service: DirectoryService, directoryMapper: DirectoryMapper): DirectoryRepository {
        return NetworkDirectoryRepository(service, directoryMapper)
    }

}