package com.wevois.checkpushnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        showNotification(remoteMessage.getNotification());

//        sendNotification();
    }

    private void showNotification(RemoteMessage.Notification notification) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Push Notification")
                .setContentText("Welcomes You")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NotificationManager.class);
        notificationManager.notify(0,builder.build());
    }


    private void sendNotification() {

        NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        android.app.Notification notify = null;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel("MAN", "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert notif != null;
            mBuilder.setChannelId("MAN").setContentText("hello").setContentText("hiii").setSmallIcon(R.drawable.ic_launcher_background).setColor(Color.GREEN);
            notif.createNotificationChannel(notificationChannel);
            assert notif != null;
            notif.notify(0 /* Request Code */, mBuilder.build());
        } else {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                notify = new android.app.Notification.Builder
                        (getApplicationContext()).setContentTitle("WeVOIS Labs द्वारा नोटिफ़िकेशन").setContentText("कचरे वाली गाड़ी जल्द ही आपके द्वार पर पहुंचने वाली है।  कृप्या कचरा गाडी में डाले। ").
                        setContentTitle("WeVOIS Labs द्वारा नोटिफ़िकेशन").setSmallIcon(R.drawable.ic_launcher_background)
                        .build();
            }
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notif.notify(0, notify);
        }

    }

}
