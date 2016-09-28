package com.stlfaboy.risewithsun;

import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity{

    SunRise sunrise = new SunRise();
    Alarm alarm = new Alarm();
    LocationResolver locationresolver = new LocationResolver();
    TextView debug1;
    public static int hours;
    public static int minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        debug1 = (TextView) findViewById(R.id.textView1);
        final Spinner location = (Spinner) findViewById(R.id.spinner);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String  mselection = location.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "selected "+ mselection, 30).show();

                double[] loc = locationresolver.resolver(String.valueOf(location.getSelectedItem()));

                sunrise.cal(loc[0],loc[1]);
                hours = sunrise.getHours();
                minutes = sunrise.getMinutes();
                debug1.setText("今日日出时间："+String.valueOf(hours)+"点"+String.valueOf(minutes)+"分");

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public void onClickButton(View view){

        alarm.setAlarm(this);

    }


}
