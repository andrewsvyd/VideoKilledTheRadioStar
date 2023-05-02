package com.svyd.videokilledtheradiostar.feature.browser.player.model

import com.svyd.domain.common.mapper.TypeMapper

class AudioStreamMapper : TypeMapper<List<String>, AudioStream> {

    override fun map(input: List<String>): AudioStream {
        return AudioStream(input)
    }
}
