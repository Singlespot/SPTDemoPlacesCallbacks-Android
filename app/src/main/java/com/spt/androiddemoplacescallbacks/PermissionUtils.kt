package com.spt.androiddemoplacescallbacks

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.core.content.ContextCompat

/**
 * Utility class for permission handling
 */
object PermissionUtils {

    /**
     * Check if the app has location permissions
     */
    fun hasLocationPermissions(activity: androidx.appcompat.app.AppCompatActivity): Boolean {
        return (ContextCompat.checkSelfPermission(
            activity,
            ACCESS_FINE_LOCATION
        ) == PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(
                    activity,
                    ACCESS_COARSE_LOCATION
                ) == PERMISSION_GRANTED)
    }

    /**
     * Get the location permissions array for request
     */
    fun getLocationPermissions(): Array<String> {
        return arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
    }
    
    /**
     * Check if the app has notification permission (Android 13+)
     */
    fun hasNotificationPermission(context: android.content.Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PERMISSION_GRANTED
        } else {
            true // Permission not required before Android 13
        }
    }
}
