package com.example.musicappui.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.musicappui.MainActivity
import com.example.musicappui.MusicBroadcastReceiver
import com.example.musicappui.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer() // Cấu hình nguồn nhạc của bạn tại đây
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "ACTION_PLAY" -> playMusic()
            "ACTION_PAUSE" -> pauseMusic()
        }
        return START_STICKY
    }

    private fun playMusic() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            showNotification("Playing")
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            showNotification("Paused")
        }
    }

    private fun showNotification(state: String) {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification: Notification = NotificationCompat.Builder(this, "MUSIC_CHANNEL")
            .setContentTitle("Now Playing")
            .setContentText(state)
            .setSmallIcon(R.drawable.ic_music_note)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_play, "Play", getPendingBroadcastIntent("ACTION_PLAY"))
            .addAction(R.drawable.ic_pause, "Pause", getPendingBroadcastIntent("ACTION_PAUSE"))
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .build()

        startForeground(1, notification)
    }

    private fun getPendingBroadcastIntent(action: String): PendingIntent {
        val intent = Intent(this, MusicBroadcastReceiver::class.java).apply { this.action = action }
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
