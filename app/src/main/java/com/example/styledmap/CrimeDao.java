package com.example.map_crime;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.map_crime.CrimeEntity;

import java.util.List;

@Dao
public interface CrimeDao{

    @Query("SELECT *, `rowid` from CRIME_TABLE")
    LiveData<List<CrimeEntity>> loadAllCrimes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll( List<CrimeEntity> crime);

   @Query("DELETE FROM CRIME_TABLE")
    void deleteAll();

    @Query("select *, `rowid` from crime_table where 'id' = :crimeId")
    LiveData<CrimeEntity> loadCrime(int crimeId);

    @Query("SELECT *, `rowid` FROM CRIME_TABLE "
            + "WHERE crime_table MATCH :query")
    LiveData<List<CrimeEntity>> searchAllCrimes(String query);
}
