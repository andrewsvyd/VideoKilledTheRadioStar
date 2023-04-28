package com.svyd.videokilledtheradiostar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.svyd.videokilledtheradiostar.common.ui.theme.VideoKilledTheRadioStarTheme
import com.svyd.videokilledtheradiostar.feature.browser.ui.VideoKilledTheRadioStarApp

class VideoKilledTheRadioStarActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoKilledTheRadioStarTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    VideoKilledTheRadioStarApp()
                }
            }
        }
    }
}
