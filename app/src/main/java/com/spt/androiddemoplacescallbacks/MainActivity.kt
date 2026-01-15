package com.spt.androiddemoplacescallbacks

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sptproximitykit.SPTProximityKit
import com.sptproximitykit.SPTTestPlacesCallbacks
import com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig

class MainActivity : AppCompatActivity() {

    // UI elements
    private lateinit var tvStatus: TextView
    private lateinit var tvPermissions: TextView

    // Location coordinates for demo
    companion object {
        private const val LATITUDE_LOUVRE = 49.0333
        private const val LONGITUDE_LOUVRE = 2.5
        private const val LATITUDE_EIFFEL_TOWER = 48.858370
        private const val LONGITUDE_EIFFEL_TOWER = 2.294481
        const val CUSTOM_PLACE_ID = 2352
    }

    // Permission launchers
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            when {
                permissions.getOrDefault(ACCESS_FINE_LOCATION, false) -> {
                    // Location granted, check notification permission
                    if (PermissionUtils.hasNotificationPermission(this)) {
                        initializeProximityKit()
                    } else {
                        requestNotificationPermission()
                    }
                }
                permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) -> {
                    // Location granted, check notification permission
                    if (PermissionUtils.hasNotificationPermission(this)) {
                        initializeProximityKit()
                    } else {
                        requestNotificationPermission()
                    }
                }
                else -> tvPermissions.text = "Permissions refusées"
            }
        }
    
    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                initializeProximityKit()
            } else {
                tvPermissions.text = "Permission de notification refusée"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI
        tvStatus = findViewById(R.id.tvStatus)
        tvPermissions = findViewById(R.id.tvPermissions)

        // Initialize SPT ProximityKit immediately
        initializeSPT()
    }

    private fun initializeSPT() {
        // Check permissions first
        if (PermissionUtils.hasLocationPermissions(this)) {
            tvPermissions.text = "Permissions accordées"
            // Also check notification permission for Android 13+
            if (PermissionUtils.hasNotificationPermission(this)) {
                initializeProximityKit()
            } else {
                tvPermissions.text = "Permission de notification requise"
                requestNotificationPermission()
            }
        } else {
            tvPermissions.text = "Permissions requises"
            requestLocationPermissions()
        }
    }
    
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            initializeProximityKit()
        }
    }
    
    private fun requestLocationPermissions() {
        requestPermissionLauncher.launch(PermissionUtils.getLocationPermissions())
    }

    private fun initializeProximityKit() {
        tvStatus.text = "Initialisation SPT..."

        // Initialize SPT ProximityKit with your credentials
        val apiKey = "ENTER_YOURS_HERE"
        val apiSecret = "ENTER_YOURS_HERE"

        SPTProximityKit.init(applicationContext, apiKey, apiSecret)

        // Configure place callbacks
        setupPlaceCallbacks()

        // Register for place events
        SPTProximityKit.geodata.registerPlaceBroadcastReceiver(this, PlaceCallbackReceiver())

        // Set test places
        SPTTestPlacesCallbacks.setHomePlaceAtLatLong(this, LATITUDE_LOUVRE, LONGITUDE_LOUVRE)
        SPTTestPlacesCallbacks.setWorkPlaceAtLatLong(
            this,
            LATITUDE_EIFFEL_TOWER,
            LONGITUDE_EIFFEL_TOWER
        )

        tvStatus.text = "SPT ProximityKit prêt !"
    }

    private fun setupPlaceCallbacks() {
        // Home place: triggers when entering (after 4pm or before 9am)
        val homeConfig = SPTPlaceCallbackConfig(16, 9, 0)
        SPTProximityKit.geodata.setEnterHomeCallback(this, homeConfig)

        // Work place: triggers when exiting (9am to 5pm)
        val workConfig = SPTPlaceCallbackConfig(9, 17, 0)
        SPTProximityKit.geodata.setExitWorkCallback(this, workConfig)

        // Custom place with specific coordinates
        val customConfig = SPTPlaceCallbackConfig.Builder()
            .setPlaceId(CUSTOM_PLACE_ID)
            .setLatitude(23.0)
            .setLongitude(25.0)
            .setPlaceTransition(SPTPlaceCallbackConfig.PlaceTransition.ENTER)
            .setDistanceTrigger(70) // 70 meters radius
            .build()
        SPTProximityKit.geodata.setCustomPlaceCallback(this, customConfig)
    }
}