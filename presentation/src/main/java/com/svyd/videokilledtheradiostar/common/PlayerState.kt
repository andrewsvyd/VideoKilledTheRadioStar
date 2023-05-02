package com.svyd.videokilledtheradiostar.common

sealed class PlayerState(val url: String) {
    class Loading(url: String) : PlayerState(url)
    class Playing(url: String) : PlayerState(url)
    class Pause(url: String) : PlayerState(url)
}
