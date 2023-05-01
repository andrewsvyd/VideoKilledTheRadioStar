package com.svyd.videokilledtheradiostar.feature.browser.player

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext

@Composable
fun ComposableBroadcastReceiver(
    action: String,
    onEvent: (intent: Intent?) -> Unit
) {
    val context = LocalContext.current
    val currentEvent by rememberUpdatedState(newValue = onEvent)

    DisposableEffect(context, action) {
        val intentFilter = IntentFilter(action)
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentEvent(intent)
            }
        }
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}
