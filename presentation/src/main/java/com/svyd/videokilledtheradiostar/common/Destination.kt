package com.svyd.videokilledtheradiostar.common

sealed class Destination(val route: String) {
    object Browser : Destination(
        "browser?$URL={$URL}&$TITLE={$TITLE}"
    ) {
        fun createRoute(url: String, title: String) =
            "browser?$URL=$url&$TITLE=$title"
    }

    companion object ARGUMENT{
        const val URL = "url"
        const val TITLE = "title"
    }
}
