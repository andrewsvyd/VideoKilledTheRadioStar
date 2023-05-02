package com.svyd.videokilledtheradiostar.feature.browser.model.mapper

import com.svyd.domain.common.mapper.TypeModifier
import com.svyd.videokilledtheradiostar.common.PlayerState
import com.svyd.videokilledtheradiostar.feature.browser.model.UiDirectory
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElement

class UiDirectoryModifier : TypeModifier<UiDirectory, PlayerState> {

    override fun modify(input: UiDirectory, modifier: PlayerState): UiDirectory {
        return input.copy(
            title = input.title,
            body = transformElements(input.body, modifier)
        )
    }

    private fun transformElements(
        elements: List<UiElement>?,
        modifier: PlayerState
    ): List<UiElement> {
        return elements
            .orEmpty()
            .asSequence()
            .map { transformElement(it, modifier) }
            .toList()
    }

    private fun transformElement(
        element: UiElement,
        modifier: PlayerState
    ): UiElement {
        return if (modifier.url == element.url) {
            element.copy(
                playerState = modifier,
                children = transformElements(element.children, modifier)
            )
        } else
            return element.copy(children = transformElements(element.children, modifier))
    }
}
