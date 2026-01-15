package com.spt.androiddemoplacescallbacks;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sptproximitykit.SPTProximityKit;
import com.sptproximitykit.SPTTestPlacesCallbacks;
import com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig;

public class MainActivity extends AppCompatActivity {

    private static SPTProximityKit.LocationRequestMode locMode = SPTProximityKit.LocationRequestMode.serverBased;
    private static SPTProximityKit.CmpMode cmpMode = SPTProximityKit.CmpMode.atLaunch;

    private final static double LATITUDE_LOUVRE = 49.0333;
    private final static double LONGITUDE_LOUVRE = 2.5;

    private final static double LATITUDE_EIFFEL_TOWER = 48.858370;
    private final static double LONGITUDE_EIFFEL_TOWER = 2.294481;

    public final static int CUSTOM_PLACE_ID = 2352;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSinglespot(this);
        SPTTestPlacesCallbacks.setHomePlaceAtLatLong(this, LATITUDE_LOUVRE, LONGITUDE_LOUVRE);
        SPTTestPlacesCallbacks.setWorkPlaceAtLatLong(this, LATITUDE_EIFFEL_TOWER, LONGITUDE_EIFFEL_TOWER);
    }

    private void initSinglespot(final Activity activity) {
        SPTProximityKit.init(activity, locMode, cmpMode);
        setSinglespotPlacesCallback();
        SPTProximityKit.registerPlaceBroadcastReceiver(activity, new PlaceCallbackReceiver()); // Make sure you register the BroadcastReceiver
    }

    /**
     * If you do not receive the notifications, make sure you're not outside of the time slots defined in the parameters
     */
    private void setSinglespotPlacesCallback() {
        // For this example, we allow the Home Broadcast to be sent only after 4pm or before 9am
        // We also don't want a more than one trigger per two hour window and set it
        // accordingly in the SPTPlaceCallbackConfig as shown below:
        SPTPlaceCallbackConfig homeConfiguration = new SPTPlaceCallbackConfig(16, 9, 0);

        // Simple second example for a Broadcast to be sent to the PlaceCallbackReceiver
        // only between 9am and 5pm. Moreover, we don't want more than one broadcast per hour.
        SPTPlaceCallbackConfig workConfiguration = new SPTPlaceCallbackConfig(9, 17, 0);

        // Using the setEnterHomeCallback ensures the Broadcast to be received only upon entering
        // the Home area, this won't be fired on exit.
        SPTProximityKit.setEnterHomeCallback(this, homeConfiguration);

        // Complete opposite to the first example, this Broadcast will only be fired when the device
        // exits the Work area.
        SPTProximityKit.setExitWorkCallback(this, workConfiguration);

        // Allows you setup callbacks for places you determine. Make sure to fill the
        SPTPlaceCallbackConfig.Builder configBuilder = new SPTPlaceCallbackConfig.Builder()
                .setAfterHourOfTheDay(0)
                .setBeforeHourOfTheDay(24)
                .setMinHoursBetweenEvents(0)
                .setPlaceId(CUSTOM_PLACE_ID) // You determine the id you want, make sure it's coherent with the idea you check for in the Receiver
                .setLatitude(23.0)
                .setLongitude(25.0)
                .setPlaceTransition(SPTPlaceCallbackConfig.PlaceTransition.ENTER) // ENTER or EXIT, as you would for a Geofence.
                .setDistanceTrigger(70); // How many meters away from the geo point, is you Place ? This is the radius of the place.

        SPTProximityKit.setCustomPlaceCallback(this, configBuilder.build()); // Don't forget to build()
    }
}