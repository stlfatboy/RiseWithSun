package com.stlfaboy.risewithsun;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class AlarmActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        SeekBar sb = (SeekBar) findViewById(R.id.seekBar);


        final MediaPlayer mp= MediaPlayer.create(this, R.raw.cats_meowing);
        mp.start();

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (seekBar.getProgress() > 96) {

                    mp.release();
                    finish();


                } else {

                    //seekBar.setThumb(getResources().getDrawable(R.mipmap.ic_launcher));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
            boolean fromUser) {
                if(progress>95){
                    //seekBar.setThumb(getResources().getDrawable(R.drawable.load_img1));
                }

            }
        });
    }
}
