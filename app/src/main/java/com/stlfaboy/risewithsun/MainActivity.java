package com.stlfaboy.risewithsun;

import java.util.Calendar;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity{

    SunRise sunrise = new SunRise();
    Alarm alarm = new Alarm();
    LocationResolver locationresolver = new LocationResolver();
    TextView debug1;
    LinearLayout layout;
    TextView tv1;
    public static int hours;
    public static int minutes;
    private static int alarmnumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        debug1 = (TextView) findViewById(R.id.textView1);
        final Spinner location = (Spinner) findViewById(R.id.spinner);
        layout  = (LinearLayout) findViewById(R.id.root_layout);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                //String  mselection = location.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), "selected "+ mselection, Toast.LENGTH_SHORT).show();

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

        //add view
        if (alarmnumber == 0){
            tv1 = new TextView(view.getContext());
            tv1.setText("已设置下次日出闹钟："+hours+"点"+minutes+"分");
            tv1.setTextSize(22);
            tv1.setTypeface(Typeface.defaultFromStyle(1));
            tv1.setGravity(0x11);
            layout.addView(tv1);
            alarmnumber ++;
        }
    }

    public void onClickButton2(View view){
        alarm.cancelAlarm(view.getContext());
        alarmnumber --;
        Toast.makeText(view.getContext(), "闹钟已取消", Toast.LENGTH_SHORT).show();
        layout.removeView(tv1);

    }


}
