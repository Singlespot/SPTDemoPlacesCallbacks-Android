package com.spt.androiddemoplacescallbacks;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.spt.androiddemoplacescallbacks.MainActivity.CUSTOM_PLACE_ID;
import static com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig.PlaceTransition;
import static com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig.PlaceType;
import static com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig.SPT_BROADCAST_ID_KEY;
import static com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig.SPT_BROADCAST_TRANSITION_KEY;
import static com.sptproximitykit.geodata.places.SPTPlaceCallbackConfig.SPT_BROADCAST_TYPE_KEY;

public class PlaceCallbackReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null || intent.getExtras() == null) return;
        PlaceTransition transition = (PlaceTransition) intent.getExtras().getSerializable(SPT_BROADCAST_TRANSITION_KEY);
        PlaceType type = (PlaceType) intent.getExtras().getSerializable(SPT_BROADCAST_TYPE_KEY);
        if (type == PlaceType.CUSTOM) {
            int id = intent.getIntExtra(SPT_BROADCAST_ID_KEY, 0);
            if (id == CUSTOM_PLACE_ID) {
                customPlaceNotification(context);
            }
        }
        if (transition == PlaceTransition.ENTER && type == PlaceType.HOME)
            homeNotification(context);
        else if (transition == PlaceTransition.EXIT && type == PlaceType.WORK)
            workNotification(context);
    }

    private void homeNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setContentTitle("Singlespot \uD83C\uDFE0")
                .setContentText("Entering Home Place!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.mipmap.ic_launcher);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        notificationManager.notify(12500, builder.build());
    }

    private void workNotification(Context context) {
        // Handle Work Notification
    }

    private void customPlaceNotification(Context context) {
        // Handle Work Notification
    }
}
