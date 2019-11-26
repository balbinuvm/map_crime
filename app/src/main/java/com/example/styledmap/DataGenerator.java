package com.example.map_crime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator {
    private static final String[] TYPE = new String[]{
            "Robbery", "Robbery", "Robbery", "Robbery", "Assault with Deadly Weapon"};
    private static final String[] WEAPON = new String[]{
            "Strong-Arm", "Strong-Arm", "Knife with Blade over 6 inches", "Strong-Arm","Hand Gun"};
    private static final Double[] LATITUDE = new Double[]{
            33.9528, 33.9893, 34.038, 34.1059, 34.0814};
    private static final Double[] LONGITUDE = new Double[]{
            -118.292, -118.256, -118.444, -118.362, -118.296};

    public static List<CrimeEntity> generateCrimes() {
        List<CrimeEntity> crimes = new ArrayList<>(TYPE.length);
        //Random rnd = new Random();
        for (int i = 0; i < TYPE.length; i++) {
                CrimeEntity crime = new CrimeEntity();
                crime.setCrimeType(TYPE[i]);
                crime.setWeapon(WEAPON[i]);
                crime.setLatitude(LATITUDE[i]);
                crime.setLongitude(LONGITUDE[i]);
                crime.setId(TYPE.length * i + 1);
                crimes.add(crime);
            }
        return crimes;
    }



}
