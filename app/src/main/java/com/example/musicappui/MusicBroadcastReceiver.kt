package com.example.musicappui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.musicappui.service.MusicService

class MusicBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "ACTION_PLAY" -> context.startService(Intent(context, MusicService::class.java).apply {
                this.action = "ACTION_PLAY"
            })
            "ACTION_PAUSE" -> context.startService(Intent(context, MusicService::class.java).apply {
                this.action = "ACTION_PAUSE"
            })
        }
    }
}