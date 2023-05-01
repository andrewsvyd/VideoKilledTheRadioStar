package com.svyd.videokilledtheradiostar.feature.browser.model

import com.svyd.videokilledtheradiostar.common.PlayerState

data class UiElement(
    val type: UiElementType,
    val text: String,
    val url: String?,
    val subtext: String?,
    val formats: String?,
    val playing: String?,
    val playingImage: String?,
    val image: String?,
    val currentTrack: String?,
    val children: List<UiElement>?,
    val playerState: PlayerState
)
