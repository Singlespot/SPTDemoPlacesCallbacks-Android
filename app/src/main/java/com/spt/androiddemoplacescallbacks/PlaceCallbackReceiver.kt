package com.spt.androiddemoplacescallbacks

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig

class PlaceCallbackReceiver : BroadcastReceiver() {
    
    companion object {
        private const val CHANNEL_ID = "spt_channel"
        private const val CHANNEL_NAME = "SPT Places"
        private const val NOTIFICATION_ID_HOME = 12501
        private const val NOTIFICATION_ID_WORK = 12502
        private const val NOTIFICATION_ID_CUSTOM = 12503
    }
    
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        intent ?: return
        val extras = intent.extras ?: return
        
        // Check notification permission for Android 13+
        if (!hasNotificationPermission(context)) {
            return
        }
        
        // Extract place information
        val transition = extras.getSerializable(SPTPlaceCallbackConfig.SPT_BROADCAST_TRANSITION_KEY) 
            as? SPTPlaceCallbackConfig.PlaceTransition
        val type = extras.getSerializable(SPTPlaceCallbackConfig.SPT_BROADCAST_TYPE_KEY) 
            as? SPTPlaceCallbackConfig.PlaceType
        
        // Handle different place events
        when {
            transition == SPTPlaceCallbackConfig.PlaceTransition.ENTER && type == SPTPlaceCallbackConfig.PlaceType.HOME -> {
                showHomeNotification(context)
            }
            transition == SPTPlaceCallbackConfig.PlaceTransition.EXIT && type == SPTPlaceCallbackConfig.PlaceType.WORK -> {
                showWorkNotification(context)
            }
            type == SPTPlaceCallbackConfig.PlaceType.CUSTOM -> {
                val placeId = extras.getInt(SPTPlaceCallbackConfig.SPT_BROADCAST_ID_KEY, -1)
                if (placeId == MainActivity.CUSTOM_PLACE_ID) {
                    showCustomPlaceNotification(context)
                }
            }
        }
    }
    
    private fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true // Permission not required before Android 13
        }
    }
    
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for SPT place events"
                enableVibration(true)
                setShowBadge(true)
            }
            
            val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(channel)
        }
    }
    
    @SuppressLint("MissingPermission")
    private fun showHomeNotification(context: Context) {
        createNotificationChannel(context)
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("SPT ProximityKit \uD83C\uDFE0")
            .setContentText("Vous arrivez à la maison !")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Bienvenue à la maison ! Le lieu a été détecté avec succès."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
            .build()
        
        if (hasNotificationPermission(context)) {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_HOME, notification)
        }
    }
    
    @SuppressLint("MissingPermission")
    private fun showWorkNotification(context: Context) {
        createNotificationChannel(context)
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("SPT ProximityKit \ud83d\udcbc")
            .setContentText("Vous quittez le travail !")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Merci pour votre travail aujourd'hui. Passez une bonne soirée !"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark))
            .build()
        
        if (hasNotificationPermission(context)) {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_WORK, notification)
        }
    }
    
    @SuppressLint("MissingPermission")
    private fun showCustomPlaceNotification(context: Context) {
        createNotificationChannel(context)
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("SPT ProximityKit \ud83d\udccd")
            .setContentText("Lieu personnalisé détecté !")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Vous êtes entré dans une zone personnalisée définie dans l'application."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
            .build()
        
        if (hasNotificationPermission(context)) {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_CUSTOM, notification)
        }
    }
}
