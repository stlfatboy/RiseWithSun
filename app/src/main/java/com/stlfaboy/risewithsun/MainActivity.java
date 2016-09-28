package com.stlfaboy.risewithsun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity {

    SunRise sunrise = new SunRise();
    LocationResolver locationresolver = new LocationResolver();

    TextView debug1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AndroidThreeTen.init(this);
        debug1 = (TextView) findViewById(R.id.textView1);
        final Spinner location = (Spinner) findViewById(R.id.spinner);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String  mselection = location.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "selected "+ mselection, 30).show();
                /**** do your code*****/
                //loc = locationresolver.resolver(location.getSelectedItem().toString());
                System.out.println(location.getSelectedItem());
                double[] loc = locationresolver.resolver(String.valueOf(location.getSelectedItem()));

                sunrise.cal(loc[0],loc[1]);
                int hours = sunrise.getHours();
                int minutes = sunrise.getMinutes();
                debug1.setText("今日日出时间："+String.valueOf(hours)+"点"+String.valueOf(minutes)+"分");

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });






    }



}
