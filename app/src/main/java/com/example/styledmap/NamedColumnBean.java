package com.example.map_crime;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class NamedColumnBean extends CsvBean {

    @CsvBindByName(column = "rowid")
    public int id;

    public int setId(int id) {
        return this.id = id;
    }

    public int getId() {
        return this.id;
    }


    @CsvBindByName(column = "Crime_Type")
    public String mCrimeType;

    public String setCrimeType(@NonNull String crime) {
        return this.mCrimeType = crime;
    }

    public String getCrimeType() {
        return this.mCrimeType;
    }

    @CsvBindByName(column = "Date_Occurred")
    @CsvDate("dd/mm/yyyy")
    public Date mDate;

    public Date setDate(@NonNull Date date) {
        return this.mDate = date;
    }

    public Date getDate() {
        return this.mDate;
    }


    @CsvBindByName(column = "Weapon")
    public String mWeapon;

    public String Weapon(@NonNull String weapon) {
        return this.mWeapon = weapon;
    }

    public String getWeapon() {
        return this.mWeapon;
    }


    @CsvBindByName(column = "Latitude")
    public float mLatitude;

    public Float setLatitude(@NonNull float latitude) {
        return this.mLatitude = latitude;
    }

    public Float getLatitude() {
        return this.mLatitude;
    }


    @CsvBindByName(column = "Longitude")
    public float mLongitude;

    public Float Longitude(@NonNull float longitude) {
        return this.mLongitude = longitude;
    }

    public Float getLongitude() {
        return this.mLongitude;
    }


}
