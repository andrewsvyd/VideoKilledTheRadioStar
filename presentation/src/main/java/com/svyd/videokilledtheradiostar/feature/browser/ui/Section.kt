package com.svyd.videokilledtheradiostar.feature.browser.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElement
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElementType

@Composable
fun Element(element: UiElement, onLinkClick: (url: String, title: String) -> Unit) {
    when (element.type) {
        UiElementType.SECTION -> Section(element, onLinkClick)
        UiElementType.LINK -> VerticalLink(element, onLinkClick)
        UiElementType.AUDIO -> VerticalAudio(element)
    }
}

@Composable
fun Section(element: UiElement, onLinkClick: (url: String, title: String) -> Unit) {
    Column {
        Text(
            text = element.text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h6
        )
        element.children?.let {
            LazyRow(
                contentPadding = PaddingValues(16.dp, 1.dp, 0.dp, 4.dp),
            ) {
                items(element.children) {
                    when (it.type) {
                        UiElementType.LINK -> HorizontalLink(element = it, onLinkClick)
                        UiElementType.AUDIO -> HorizontalAudio(element = it)
                        else -> {}
                    }
                }
            }
        }
    }
}