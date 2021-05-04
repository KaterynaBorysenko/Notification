package com.example.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class AlarmReceiver<СharSequence> extends BroadcastReceiver {

    private static final String CHANNEL_ID="CHANNEL_SAMPLE";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

    int notificationId=intent.getIntExtra("notificationId",0);
    String message=intent.getStringExtra("message");

    Intent mainIntent=new Intent(context,MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity( context,0,mainIntent,0);

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        {

        СharSequence channel_name= (СharSequence) "My Notification";
       int importance=NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel= null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                channel = new NotificationChannel(CHANNEL_ID,"My Notification",importance);
            }
            notificationManager.createNotificationChannel(channel);

    }

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("TITLE")
        .setContentText(message)
        .setContentIntent(contentIntent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true);


        notificationManager.notify(notificationId,builder.build());
}
}













