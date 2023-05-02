package com.svyd.data.repository.audio.mapper

import com.svyd.domain.common.mapper.TypeMapper

class AudioLinkMapper : TypeMapper<String, List<String>> {

    override fun map(input: String): List<String> {
        return input
            .split(NEW_LINE)
            .asSequence()
            .filter { it.isNotEmpty() }
            .toList()
    }

    companion object {
        private const val NEW_LINE = "\n"
    }
}