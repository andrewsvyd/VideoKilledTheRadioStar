package com.svyd.data.repository.audio

import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.AudioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkAudioRepository(
    private val audioService: AudioService,
    private val audioMapper: TypeMapper<String, List<String>>
) : AudioRepository {

    override fun audio(url: String): Flow<List<String>> {
        return flow {
            val responseBody = audioService.audio(url).body()
                ?: throw IllegalStateException("No response body for $url")
            emit(audioMapper.map(responseBody.string()))
        }
    }

}
