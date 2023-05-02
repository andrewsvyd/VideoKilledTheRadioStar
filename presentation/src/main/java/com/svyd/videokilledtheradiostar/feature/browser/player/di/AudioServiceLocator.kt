package com.svyd.videokilledtheradiostar.feature.browser.player.di

import com.svyd.data.common.ApiConstants
import com.svyd.data.repository.audio.AudioService
import com.svyd.data.repository.audio.NetworkAudioRepository
import com.svyd.data.repository.audio.mapper.AudioLinkMapper
import com.svyd.domain.common.exception.ErrorMapper
import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.interactor.ParametrizedInteractor
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.interactor.AudioInteractor
import com.svyd.domain.repository.AudioRepository
import com.svyd.videokilledtheradiostar.feature.browser.player.model.AudioStream
import com.svyd.videokilledtheradiostar.feature.browser.player.model.AudioStreamMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class AudioServiceLocator {

    fun provideAudioInteractor(): ParametrizedInteractor<List<String>, String> {
        return AudioInteractor(provideAudioRepository(), provideErrorMapper())
    }

    fun provideAudioStreamMapper() : TypeMapper<List<String>, AudioStream>{
        return AudioStreamMapper()
    }

    private fun provideErrorMapper(): TypeMapper<Throwable, Failure> {
        return ErrorMapper()
    }

    private fun provideAudioRepository(): AudioRepository {
        return NetworkAudioRepository(provideService(provideRetrofit()), provideAudioLinkMapper())
    }

    private fun provideAudioLinkMapper(): TypeMapper<String, List<String>> {
        return AudioLinkMapper()
    }

    private fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(ApiConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.WRITE_TIME_OUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.Paths.ROOT_ENDPOINT)
            .client(client)
            .build()
    }

    private fun provideService(retrofit: Retrofit): AudioService {
        return retrofit.create(AudioService::class.java)
    }

}