package com.svyd.videokilledtheradiostar.common.ui.composable

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(context, action) {
        val intentFilter = IntentFilter(action)
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentEvent(intent)
            }
        }
        /**
         *todo:
         * should be unregistered when the screen dies, but if unsubscribed upon new screen
         * appearance - the event would be missed.
         * possible solution is to use database as a single source of truth, but that brings
         * a challenge of invalidation of such state in db
         */
        context.registerReceiver(receiver, intentFilter)
    }
}
