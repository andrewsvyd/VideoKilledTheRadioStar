package com.svyd.videokilledtheradiostar.feature.browser.player.ui

import androidx.compose.runtime.Composable
import com.svyd.videokilledtheradiostar.common.PlayerState
import com.svyd.videokilledtheradiostar.common.ui.composable.ComposableBroadcastReceiver
import com.svyd.videokilledtheradiostar.feature.browser.player.PlayerService

@Composable
fun PlayerReceiver(onPlayerStateChanged: (PlayerState) -> Unit) {

    ComposableBroadcastReceiver(PlayerService.ACTION_PLAYER_STATE_CHANGED) { intent ->

        intent ?: return@ComposableBroadcastReceiver
        val intentState = intent.getStringExtra(PlayerService.KEY_PLAYER_STATE)
        val intentUrl = intent.getStringExtra(PlayerService.KEY_AUDIO_URL)

        intentUrl ?: return@ComposableBroadcastReceiver

        val playerState = when (intentState) {
            PlayerService.PLAYER_STATE_LOADING -> PlayerState.Loading(intentUrl)
            PlayerService.PLAYER_STATE_PLAY -> PlayerState.Playing(intentUrl)
            PlayerService.PLAYER_STATE_PAUSE -> PlayerState.Pause(intentUrl)
            else -> return@ComposableBroadcastReceiver
        }

        onPlayerStateChanged(playerState)
    }
}
