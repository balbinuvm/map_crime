package com.example.map_crime;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

public class CrimeListViewModel extends AndroidViewModel {

    private final CrimeRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<CrimeEntity>> mObservableCrimes;

    public CrimeListViewModel(Application application) {
        super(application);

        mObservableCrimes = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        //mObservableCrimes.setValue(null);

        mRepository = ((BasicApp) application).getRepository();
        LiveData<List<CrimeEntity>> crimes = mRepository.getCrimes();

        // observe the changes of the products from the database and forward them
        mObservableCrimes.addSource(crimes, mObservableCrimes::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<CrimeEntity>> getCrimes() {
        return mObservableCrimes;
    }

    public LiveData<List<CrimeEntity>> searchCrimes(String query) {
        return mRepository.searchCrimes(query);
    }
}
