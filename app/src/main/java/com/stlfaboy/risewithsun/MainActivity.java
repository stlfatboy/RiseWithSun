package com.stlfaboy.risewithsun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.stlfaboy.risewithsun.SunRise;

//import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity {

    SunRise sunrise = new SunRise();

    TextView debug1;
    TextView debug2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AndroidThreeTen.init(this);
        sunrise.cal();
        int hours = sunrise.getHours();
        int minutes = sunrise.getMinutes();
        setContentView(R.layout.activity_main);
        debug1 = (TextView) findViewById(R.id.textView1);
        debug2 = (TextView) findViewById(R.id.textView2);
        debug1.setText(String.valueOf(hours));
        debug2.setText(String.valueOf(minutes));











    }


}
