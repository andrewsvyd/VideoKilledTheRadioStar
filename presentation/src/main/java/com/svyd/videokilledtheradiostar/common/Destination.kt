package com.svyd.videokilledtheradiostar.common

sealed class Destination(val route: String) {
    object Browser : Destination("browser?url={$URL_ARGUMENT}") {
        fun createRoute(url: String) = "browser?url=$url"
    }

    companion object {
        const val URL_ARGUMENT = "url"
    }
}
