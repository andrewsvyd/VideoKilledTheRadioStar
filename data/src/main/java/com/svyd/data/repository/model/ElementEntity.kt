package com.svyd.data.repository.model

import com.google.gson.annotations.SerializedName

data class ElementEntity(
    val element: String,
    val type: String?,
    val text: String,
    val key: String?,
    @SerializedName("URL")
    val url: String?,
    val bitrate: Int?,
    val reliability: Int?,
    @SerializedName("guide_id")
    val guideId: String?,
    val subtext: String?,
    @SerializedName("genre_id")
    val genreId: String?,
    val formats: String?,
    val playing: String?,
    @SerializedName("show_id")
    val showId: String?,
    @SerializedName("playing_image")
    val playingImage: String?,
    val item: String?,
    val image: String?,
    @SerializedName("current_track")
    val currentTrack: String?,
    @SerializedName("now_playing_id")
    val nowPlayingId: String?,
    @SerializedName("preset_id")
    val presetId: String?,
    val children: List<ElementEntity>?
)