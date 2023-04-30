package com.svyd.videokilledtheradiostar.feature.browser.model

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
    val children: List<UiElement>?
)
