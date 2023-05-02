package com.svyd.data.repository.browser.model.mapper

import com.svyd.data.repository.browser.model.DirectoryEntity
import com.svyd.data.repository.browser.model.ElementEntity
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.model.Directory
import com.svyd.domain.repository.model.Element
import java.net.URLEncoder

class DirectoryMapper : TypeMapper<DirectoryEntity, Directory> {

    override fun map(input: DirectoryEntity): Directory {
        checkStatus(input)
        return Directory(
            input.head.title,
            transformElements(input.body)
        )
    }

    private fun checkStatus(input: DirectoryEntity) {
        if (input.head.status != 200)
            throw IllegalStateException(input.head.fault ?: "Response status is not OK")
    }

    private fun transformElements(elements: List<ElementEntity>?): List<Element> {
        if (elements == null) return emptyList()
        return elements.asSequence()
            .map { transformElement(it) }
            .toList()
    }

    private fun transformElement(element: ElementEntity): Element {
        return Element(
            element.type,
            element.text,
            encodeUrl(element.url),
            element.subtext,
            element.formats,
            element.playing,
            element.playingImage,
            element.image,
            element.currentTrack,
            transformElements(element.children)
        )
    }

    private fun encodeUrl(url: String?): String? {
        return URLEncoder.encode(url.orEmpty(), "utf-8")
    }
}
