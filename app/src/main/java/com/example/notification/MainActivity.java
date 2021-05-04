package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        EditText editText=findViewById(R.id.editText);
        TimePicker timePicker=findViewById(R.id.timePicker);

        Intent intent=new Intent(MainActivity.this,AlarmReceiver.class);
        int notificationId = 1;
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message",editText.getText().toString());

        PendingIntent pendingIntent=PendingIntent.getBroadcast
                (MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()){
            case R.id.setBtn:
                int hour=timePicker.getHour();
                int minute= timePicker.getMinute();

               Calendar startTime=Calendar.getInstance();
               startTime.set(Calendar.HOUR_OF_DAY,hour);
               startTime.set(Calendar.MINUTE,minute);
               startTime.set(Calendar.SECOND,0);
               long alarmStartTime=startTime.getTimeInMillis();

               alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,pendingIntent);
               Toast.makeText(this,"Done!",Toast.LENGTH_SHORT).show();
               break;

               case R.id.cancelBtn:
             alarmManager.cancel(pendingIntent);
                Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show();
                break;
        }


    }
}