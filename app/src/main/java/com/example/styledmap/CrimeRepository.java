package com.example.map_crime;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.map_crime.CrimeEntity;
import com.example.map_crime.CrimeRoomDatabase;

import java.util.List;

public class CrimeRepository {
    private static CrimeRepository sInstance;

    private final CrimeRoomDatabase mDatabase;
    private MediatorLiveData<List<CrimeEntity>> mObservableProducts;

    private CrimeRepository(final CrimeRoomDatabase database) {
        mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.crimeDao().loadAllCrimes(),
                crimeEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableProducts.postValue(crimeEntities);
                    }
                });
    }

    public static CrimeRepository getInstance(final CrimeRoomDatabase database) {
        if (sInstance == null) {
            synchronized (CrimeRepository.class) {
                if (sInstance == null) {
                    sInstance = new CrimeRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<CrimeEntity>> getCrimes() {
        return mObservableProducts;
    }

    public LiveData<CrimeEntity> loadCrime(final int crimeId) {
        return mDatabase.crimeDao().loadCrime(crimeId);
    }


   public LiveData<List<CrimeEntity>> searchCrimes(String query) {
        return mDatabase.crimeDao().searchAllCrimes(query);
    }
}