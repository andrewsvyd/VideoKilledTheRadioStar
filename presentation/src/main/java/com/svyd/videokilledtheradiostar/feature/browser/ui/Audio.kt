package com.svyd.videokilledtheradiostar.feature.browser.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.svyd.videokilledtheradiostar.R
import com.svyd.videokilledtheradiostar.common.PlayerState
import com.svyd.videokilledtheradiostar.common.ui.theme.Scrim
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElement

@Composable
fun HorizontalAudio(
    element: UiElement,
    onAudioClick: (url: String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(0.dp, 0.dp, 16.dp, 0.dp)
            .height(86.dp)
    ) {
        AudioBody(element = element, onAudioClick = onAudioClick)
    }
}

@Composable
fun VerticalAudio(
    element: UiElement,
    onAudioClick: (url: String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
            .height(86.dp)
            .fillMaxWidth()
    ) {
        AudioBody(element = element, onAudioClick)
    }
}

@Composable
fun AudioBody(
    element: UiElement,
    onAudioClick: (url: String) -> Unit
) {
    Row(modifier = Modifier.clickable { element.url?.let { onAudioClick(it) } }) {
        Box(
            modifier = Modifier
                .size(86.dp)
                .background(MaterialTheme.colors.primary)
        ) {
            AsyncImage(
                model = element.image,
                contentDescription = "Cover",
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .size(86.dp)
                    .background(Scrim)
            )
            Crossfade(targetState = element.playerState) {
                Box(modifier = Modifier.size(86.dp)) {
                    when (it) {
                        is PlayerState.Pause ->
                            Image(
                                painter = painterResource(id = R.drawable.play),
                                contentDescription = "Folder",
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(4.dp)
                                    .align(Alignment.BottomEnd)
                            )

                        is PlayerState.Playing ->
                            Image(
                                painter = painterResource(id = R.drawable.pause),
                                contentDescription = "Folder",
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(4.dp)
                                    .align(Alignment.BottomEnd)
                            )

                        is PlayerState.Loading ->
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(8.dp)
                                    .align(Alignment.BottomEnd),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 0.dp)
                .defaultMinSize(Dp.Unspecified, 40.dp)
                .widthIn(128.dp, 256.dp)
        ) {
            Text(
                text = element.text,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2
            )
            element.subtext?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 2
                )
            }
        }
    }
}
