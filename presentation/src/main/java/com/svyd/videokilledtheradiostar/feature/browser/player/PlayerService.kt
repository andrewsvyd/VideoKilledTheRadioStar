package com.svyd.videokilledtheradiostar.feature.browser.player

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper

class PlayerService : Service() {

    private var currentTrackUrl: String = ""

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val newTrackUrl = intent?.getStringExtra(KEY_AUDIO_URL) ?: return START_STICKY

        if (newTrackUrl == currentTrackUrl) {
            pause(currentTrackUrl)
        } else {
            currentTrackUrl = newTrackUrl
            play(currentTrackUrl)
        }

        return START_STICKY
    }

    private fun pause(trackUrl: String) {
        sendPlayerState(PLAYER_STATE_PAUSE)
    }

    private fun play(trackUrl: String) {
        sendPlayerState(PLAYER_STATE_LOADING)
        Handler(Looper.getMainLooper()).postDelayed({
            sendPlayerState(PLAYER_STATE_PLAY)
        }, 3000)
    }

    private fun sendPlayerState(state: String) {
        val intent = Intent(ACTION_PLAYER_STATE_CHANGED)
        intent.putExtra(KEY_AUDIO_URL, currentTrackUrl)
        intent.putExtra(KEY_PLAYER_STATE, state)
        sendBroadcast(intent)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        const val ACTION_PLAYER_STATE_CHANGED =
            "com.svyd.videokilledtheradiostar.ACTION_PLAYER_STATE_CHANGED"

        const val KEY_AUDIO_URL = "com.svyd.videokilledtheradiostar.KEY_AUDIO_URL"
        const val KEY_PLAYER_STATE = "com.svyd.videokilledtheradiostar.KEY_PLAYER_STATE"

        const val PLAYER_STATE_PLAY = "com.svyd.videokilledtheradiostar.PLAYER_STATE_PLAY"
        const val PLAYER_STATE_PAUSE = "com.svyd.videokilledtheradiostar.PLAYER_STATE_PAUSE"
        const val PLAYER_STATE_LOADING = "com.svyd.videokilledtheradiostar.PLAYER_STATE_LOADING"
    }
}