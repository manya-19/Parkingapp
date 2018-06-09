package com.example.manya.locationapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

/**
 * Created by Manya on 27/06/2017.
 */

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = GeofenceBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.i(TAG,"broadcast received");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.e(TAG, String.format("Error code : %d", geofencingEvent.getErrorCode()));
            return;
        }
        //Whenever user enters/exits from any geofence an intent will be send from PendingIntent request and we then extract the transition
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
       // if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
         //   setRingerMode(context, AudioManager.RINGER_MODE_SILENT);//set phone on silent mode on entry
        //} else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
          //  setRingerMode(context, AudioManager.RINGER_MODE_NORMAL);//set phone on normal mode on exit
        //} else {
            // Log the error.
         //   Log.e(TAG, String.format("Unknown transition : %d", geofenceTransition));
           // return;
        //}
        sendNotification(context, geofenceTransition);}

    private void sendNotification(Context context, int transitionType) {
        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(MainActivity.class);

        // Push the content Intent onto the stack.
       stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
              stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        // Check the transition type to display the relevant icon image
       // if (transitionType == Geofence.GEOFENCE_TRANSITION_ENTER) {
       //     builder.setSmallIcon(R.drawable.ic_volume_off_white_24dp)
        //            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
         //                   R.drawable.ic_volume_off_white_24dp))
           //         .setContentTitle(context.getString(R.string.silent_mode_activated));
       // } else if (transitionType == Geofence.GEOFENCE_TRANSITION_EXIT) {
        //    builder.setSmallIcon(R.drawable.ic_volume_up_white_24dp)
              //      .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
              //              R.drawable.ic_volume_up_white_24dp))
              //      .setContentTitle(context.getString(R.string.back_to_normal));
        //}

        // Continue building the notification
        builder.setContentText("Parking location");
       builder.setContentIntent(notificationPendingIntent);


        builder.setAutoCancel(true);

        // Get an instance of the Notification manager
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Issue the notification
     mNotificationManager.notify(0, builder.build());
    }



 /*   private void setRingerMode(Context context, int mode) {
        try {
            if (Build.VERSION.SDK_INT < 23) {
                AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                audio.setRingerMode(0);
            } else if( Build.VERSION.SDK_INT >= 23 ) {
                this.requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp(context,mode);
            }
        } catch ( SecurityException e ) {

        }}
    private void requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp(Context context,int mode) {

        if( Build.VERSION.SDK_INT < 23 ) {
            return;
        }
        NotificationManager n = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (n.isNotificationPolicyAccessGranted()) {
            AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(mode);
        } else {
            // Ask the user to grant access
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }*/
}




