package com.stlfaboy.risewithsun;

/**
 * Created by stlfa on 9/28/2016.
 */

public class LocationResolver {

    private String loc;

    public double[] resolver(String loc){
        double l[] = {39.733, -104.983};
        switch (loc){
            case "Denver":
                break;
            case "New York":
                l = new double[]{40.713, -74.006};
                break;
            case "Los Angeles":
                l = new double[]{34.052, -118.244};
                break;
            case "北京":
                l = new double[]{39.904, 116.407};
                break;
            case "秦皇岛":
                l = new double[]{39.935, 119.600};
                break;
            case "杭州":
                l = new double[]{30.274, 120.155};
                break;
            default:
                break;
        }
        return l;
    }
}
