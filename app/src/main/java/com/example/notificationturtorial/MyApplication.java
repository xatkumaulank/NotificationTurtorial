package com.example.notificationturtorial;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "MyChannel";
    public static final String CHANNEL_ID_2 = "MyChannel_2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri sound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.custom_notification);
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            //config channel 1
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_MIN;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setSound(uri,audioAttributes);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            // config channel 2
            CharSequence name_2 = getString(R.string.channel_name_2);
            String description_2 = getString(R.string.channel_description_2);
            int importance_2 = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, name_2, importance_2);
            channel_2.setDescription(description_2);
            channel_2.setSound(sound,audioAttributes);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel_2);
            }

        }
    }
}
