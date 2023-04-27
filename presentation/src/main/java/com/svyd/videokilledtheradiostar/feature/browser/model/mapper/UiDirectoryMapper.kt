package com.svyd.videokilledtheradiostar.feature.browser.model.mapper

import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.model.Directory
import com.svyd.domain.repository.model.Element
import com.svyd.videokilledtheradiostar.feature.browser.model.UiDirectory
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElement
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElementType
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElementType.*

class UiDirectoryMapper : TypeMapper<Directory, UiDirectory> {

    override fun map(input: Directory): UiDirectory {
        return UiDirectory(
            input.title,
            transformElements(input.elements)
        )
    }

    private fun transformElements(elements: List<Element?>): List<UiElement> {
        return elements.asSequence()
            .filterNotNull()
            .map { transformElement(it) }
            .toList()
    }

    private fun transformElement(element: Element): UiElement {
        return UiElement(
            transformType(element.type),
            element.text,
            element.url,
            element.subtext,
            element.formats,
            element.playing,
            element.playingImage,
            element.image,
            element.currentTrack,
            transformElements(element.children)
        )
    }

    private fun transformType(type: String?): UiElementType {
        return when (type) {
            TYPE_AUDIO -> AUDIO
            TYPE_LINK -> LINK
            else -> SECTION
        }
    }

    companion object {
        const val TYPE_AUDIO = "audio"
        const val TYPE_LINK = "link"
    }

}
