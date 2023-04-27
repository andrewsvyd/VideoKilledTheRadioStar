package com.svyd.domain.repository.model

data class Element(
    val type: String?,
    val text: String,
    val url: String?,
    val subtext: String?,
    val formats: String?,
    val playing: String?,
    val playingImage: String?,
    val image: String?,
    val currentTrack: String?,
    val children: List<Element?>
)
