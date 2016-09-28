package com.stlfaboy.risewithsun;

/**
 * Created by stlfa on 9/26/2016.
 */
import android.util.Log;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;
import java.util.TimeZone;
//import java.time.format.DateTimeFormatter;
import java.lang.String;
import java.lang.Integer;

public class SunRise {

    private static int hours, minutes;

    public static void main(String[] args) {
        //String isodate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    }


    public void cal() {
        cal(39.733, -104.983);
    }

    public void cal(double Glat, double Long){
        LocalDate date = LocalDate.now();
        LocalDate odate = LocalDate.of(2000, 1, 1);

        //System.out.println(ChronoUnit.DAYS.between(odate, date));

        //double Glat = 39.733;
        //double Long = -104.983;
        double UTo = 180;
        double UT = 0.00;
        long days = ChronoUnit.DAYS.between(odate, date);

        while(Math.abs(UT - UTo) > 0.1){

            UTo = UT;

            double t = (days + 1 + UTo / 360) / 36525;

            double L = 280.460 + 36000.770 * t;

            double G = 357.528 +35999.050 * t;

            double lamda = L + 1.915 * Math.sin(Math.toRadians(G)) + 0.020 * Math.sin(Math.toRadians(2*G));

            double e = 23.4393 - 0.0130 * t;

            double theata =  Math.asin(Math.sin(Math.toRadians(e)) * Math.sin(Math.toRadians(lamda)));

            double GHA = UTo - 180 - 1.915 * Math.sin(Math.toRadians(G)) -
                    0.020 * Math.sin(Math.toRadians(2 * G)) + 2.466 * Math.sin(Math.toRadians(2 * lamda)) -
                    0.053 * Math.sin(Math.toRadians(4 * lamda));

            double error = Math.acos((Math.sin(Math.toRadians(-0.833)) - Math.sin(Math.toRadians(Glat)) * Math.sin(theata)) /
                    (Math.cos(Math.toRadians(Glat)) * Math.cos(theata)));

            UT = UTo - (GHA + Long + Math.toDegrees(error));

        }

        int tz = TimeZone.getDefault().getRawOffset();

        double T = UT / 15 + tz / 3600000;

        //System.out.println(T);

        String[] time = String.valueOf(T).split("\\.");

        hours = Integer.parseInt(time[0]);
        //System.out.println(String.valueOf(hours));

        time = String.valueOf((T - hours) * 60).split("\\.");

        minutes = Integer.parseInt(time[0]);
        //System.out.println(minutes);
    }
    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }
}
