package com.svyd.videokilledtheradiostar.feature.browser.player

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.interactor.ParametrizedInteractor
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.videokilledtheradiostar.feature.browser.player.di.AudioServiceLocator
import com.svyd.videokilledtheradiostar.feature.browser.player.model.AudioStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.net.URLDecoder

/**
 *todo:
 * implement as a foreground service; provide ability to control player via notification
 * service is a bit messy at the moment,
 * needs some refactoring,
 * maybe an introduction of StateFlow to handle changes of it's state in one place and
 * provide more scalability
 */

class PlayerService : Service() {

    private var player: ExoPlayer? = null
    private var currentTrackUrl: String = ""
    private var currentTrack: String = ""

    private lateinit var audioInteractor: ParametrizedInteractor<List<String>, String>
    private lateinit var audioStreamMapper: TypeMapper<List<String>, AudioStream>

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        initDependencies()
        initializePlayer()

        val newTrackUrl = intent?.getStringExtra(KEY_AUDIO_URL)

        if (newTrackUrl == null) {
            sendCurrentState()
        } else {

            val sameTrack = newTrackUrl == currentTrackUrl
            if (sameTrack && player?.isPlaying == true) {
                pause()
            } else {
                currentTrackUrl = newTrackUrl
                pause()
                sendPlayerState(PLAYER_STATE_LOADING)
                if (sameTrack && currentTrack.isNotEmpty()) play(currentTrack)
                else fetchUrl(decodeUrl(newTrackUrl))
            }
        }

        return START_STICKY
    }

    private fun sendCurrentState() {
        player?.let {
            if (it.isPlaying) sendPlayerState(PLAYER_STATE_PLAY)
        }
    }

    private fun fetchUrl(newTrackUrl: String) {
        audioInteractor(
            newTrackUrl,
            CoroutineScope(Dispatchers.Main),
            audioStreamMapper
        ) {
            it.fold(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(audioStream: AudioStream) {
        currentTrack = audioStream.urls[0]
        play(currentTrack)
    }

    /**
     *todo:
     * handle failure cases, send error state with extra data
     * for cause to handle on view.
     * try another stream link in case if there are multiple.
     */
    private fun handleFailure(failure: Failure) {
        sendPlayerState(PLAYER_STATE_PAUSE)
    }

    private fun decodeUrl(url: String?): String {
        return URLDecoder.decode(url.orEmpty(), "utf-8")
    }

    /**
     *todo:
     * use Dagger or Hilt instead of manual DI
     */
    private fun initDependencies() {
        if (!this::audioInteractor.isInitialized) {
            audioInteractor = AudioServiceLocator().provideAudioInteractor()
        }
        if (!this::audioStreamMapper.isInitialized) {
            audioStreamMapper = AudioServiceLocator().provideAudioStreamMapper()
        }
    }

    private fun initializePlayer() {
        if (player == null) {
            player = ExoPlayer
                .Builder(this)
                .build()
            player?.addListener(object : Player.Listener{

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    if (isPlaying) {
                        sendPlayerState(PLAYER_STATE_PLAY)
                    } else {
                        sendPlayerState(PLAYER_STATE_PAUSE)
                    }
                }

                override fun onPlayerError(error: PlaybackException) {
                    sendPlayerState(PLAYER_STATE_PAUSE)
                }
            })
        }
    }

    private fun pause() {
        player?.playWhenReady = false
    }

    private fun play(trackUrl: String) {
        player?.let {
            val mediaItem = MediaItem.fromUri(trackUrl)
            it.setMediaItem(mediaItem)
            it.prepare()
            it.playWhenReady = true
        }
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
