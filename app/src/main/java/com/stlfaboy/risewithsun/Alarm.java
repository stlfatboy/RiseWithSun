package com.stlfaboy.risewithsun;

/**
 * Created by stlfa on 9/28/2016.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.Calendar;

import static com.stlfaboy.risewithsun.MainActivity.hours;
import static com.stlfaboy.risewithsun.MainActivity.minutes;


public class Alarm extends BroadcastReceiver
{

    static MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        mediaPlayer= MediaPlayer.create(context, R.raw.cats_meowing);

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        //mediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK);
        wl.acquire();

        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();

        mediaPlayer.start();
        wl.release();

    }

    public void setAlarm(Context context)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);

        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        //For test
        //am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 5 * 1000, pi);
        Toast.makeText(context, "Set with "+hours+":"+minutes, Toast.LENGTH_SHORT).show();

    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}