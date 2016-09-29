package com.stlfaboy.risewithsun;

/**
 * Created by stlfa on 9/28/2016.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.Calendar;

import static com.stlfaboy.risewithsun.MainActivity.hours;
import static com.stlfaboy.risewithsun.MainActivity.minutes;


public class Alarm extends BroadcastReceiver
{


    @Override
    public void onReceive(Context context, Intent intent)
    {


        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        //mediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK);
        wl.acquire();

        //Toast.makeText(context, "起床了！", Toast.LENGTH_LONG).show();
        intent.setClass(context, AlarmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        wl.release();
        //cancelAlarm(context);


    }

    public void setAlarm(Context context)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);

        /*Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        AlarmManager am =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 6*1000, pi);
        */

        long triggerTime = calendar.getTimeInMillis();
        if (triggerTime  <= System.currentTimeMillis() + 3000)
        {
            // 3 Second distance
            calendar.add(Calendar.DATE, 1);  // Add 1 day --> Trigger 1 day later from now
        }

        Intent i = new Intent(context, Alarm.class);

        boolean isWorking = (PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_NO_CREATE) != null);
        if (isWorking) {
            Toast.makeText(context, "闹钟已设置，无需重复设置", Toast.LENGTH_SHORT).show();
        }

       if (!isWorking) {
           PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
           AlarmManager am =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
           am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
           //am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + time, pi);
           Toast.makeText(context, "闹钟设置为："+hours+"点"+minutes+"分", Toast.LENGTH_SHORT).show();
       }

    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        sender.cancel();
    }
}