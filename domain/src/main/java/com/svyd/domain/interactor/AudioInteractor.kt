package com.svyd.domain.interactor

import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.interactor.ParametrizedInteractor
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.AudioRepository
import kotlinx.coroutines.flow.Flow

/**
 * Links to audio files come from API in format that is not supported by any of ExoPlayer's
 * MediaTypes (DASH, HLS, SmoothStreaming, Progressive, RSTP)
 *
 * A service simply returns links to audio sources as a string, separated with new line
 * Although the actual response's headers contain Content-Type : audio/x-mpegurl,
 * which seems to be APPLICATION_M3U8 mime type, which in turn should be easily handled by
 * ExoPlayer as an adaptive HLS stream, it won't work, throwing an exception:
 * androidx.media3.common.ParserException: Input does not start with the #EXTM3U header.
 *
 * Considering the above, another (this) use case was introduced to fetch list of links
 * from the link provided by API, and one of resulting links is used in turn.
 *
 */
class AudioInteractor(
    private val repository: AudioRepository,
    errorMapper: TypeMapper<Throwable, Failure>
) : ParametrizedInteractor<List<String>, String>(errorMapper) {

    override suspend fun run(params: String): Flow<List<String>> = repository.audio(params)
}
