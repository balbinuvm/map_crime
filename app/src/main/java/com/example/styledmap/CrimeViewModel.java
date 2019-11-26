package com.example.map_crime;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.IdentityHashMap;
import java.util.List;

public class CrimeViewModel extends AndroidViewModel {
    private final LiveData<CrimeEntity> mObservableCrime;

    public ObservableField<CrimeEntity> crime = new ObservableField<>();

    private final int mCrimeId;


    public CrimeViewModel(@NonNull Application application, CrimeRepository repository,
                          final int crimeId) {
        super(application);
        mCrimeId = crimeId;

        mObservableCrime = repository.loadCrime(mCrimeId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */

    public LiveData<CrimeEntity> getObservableCrime() {
        return mObservableCrime;
    }

    public void setCrime(CrimeEntity crime) {
        this.crime.set(crime);
    }
    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mCrimeId;

        private final CrimeRepository mRepository;

        public Factory(@NonNull Application application, int crimeId) {
            mApplication = application;
            mCrimeId = crimeId;
            mRepository = ((BasicApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CrimeViewModel(mApplication, mRepository, mCrimeId);
        }
    }
}

