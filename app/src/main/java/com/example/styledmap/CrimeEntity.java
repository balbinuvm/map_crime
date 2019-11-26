package com.example.map_crime;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Fts4
@Entity(tableName = "crime_table")
public class CrimeEntity implements Crime{
 /*   private Context mContext;
    // String csvFile = "C:\\Users\\AllBen\\Desktop\\CS275-Final-project\\StyledMap\\app\\src\\main\\res\\raw\\last6monthstrimmedbycrimenweapon.csv";

    public InputStream getCSV(Context context) {
        this.mContext = context;
        InputStream csv = context.getResources().openRawResource(R.raw.last6monthstrimmedbycrimenweapon);
        return csv;
    }*/

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int id;

    public int setId(int id) {
        return this.id = id;
    }
    @Override
    public int getId() {
        return this.id;
    }

    @ColumnInfo(name = "Crime_Type")
    public String mCrimeType;

    public String setCrimeType(@NonNull String crimeType) {
        return this.mCrimeType = crimeType;
    }
    @Override
    public String getCrimeType() {
        return this.mCrimeType;
    }
    @ColumnInfo(name = "Date_Occurred")
    public Date mDate;

    public Date setDate(@NonNull Date date) {
        return this.mDate = date;
    }
    @Override
    public Date getDate() {
        return this.mDate;
    }

    @ColumnInfo(name = "Weapon")
    public String mWeapon;

    public String setWeapon(@NonNull String weapon) {
        return this.mWeapon = weapon;
    }
    @Override
    public String getWeapon() {
        return this.mWeapon;
    }

    @ColumnInfo(name = "Latitude")
    public double mLatitude;

    public double setLatitude(@NonNull double latitude) {
        return this.mLatitude = latitude;
    }
    @Override
    public double getLatitude() {
        return this.mLatitude;
    }

    @ColumnInfo(name = "Longitude")
    public double mLongitude;

    public double setLongitude(@NonNull double longitude) {
        return this.mLongitude = longitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public CrimeEntity(){}

    @Ignore
    public CrimeEntity(int id, String crime, Date date, String weapon, double latitude, double longitude){
        this.id=id;
        this.mCrimeType= crime;
        this.mDate= date;
        this.mWeapon = weapon;
        this.mLatitude= latitude;
        this.mLongitude= longitude;
    }

    public CrimeEntity(Crime crime){
        this.id=crime.getId();
        this.mCrimeType=crime.getCrimeType();
        this.mDate=crime.getDate();
        this.mWeapon=crime.getWeapon();
        this.mLatitude=crime.getLatitude();
        this.mLongitude=crime.getLongitude();
    }
}









