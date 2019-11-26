package com.example.map_crime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.map_crime.databinding.CrimeFragmentBinding;

public class CrimeFragment extends Fragment {

    private static final String KEY_CRIME_ID = "crime_id";

    private CrimeFragmentBinding mBinding;

    private CrimeListAdapter mCrimeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.crime_fragment, container, false);

        // Create and set the adapter for the RecyclerView.
        mCrimeAdapter = new CrimeListAdapter(mCrimeClickCallback);
        mBinding.crimeList.setAdapter(mCrimeAdapter);
        return mBinding.getRoot();
    }

    private final CrimeClickCallback mCrimeClickCallback = new CrimeClickCallback() {
        @Override
        public void onClick(Crime crime) {
            // no-op

        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CrimeViewModel.Factory factory = new CrimeViewModel.Factory(
                requireActivity().getApplication(), getArguments().getInt(KEY_CRIME_ID));

        final CrimeViewModel model = new ViewModelProvider(this, factory)
                .get(CrimeViewModel.class);

        mBinding.setCrimeViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final CrimeViewModel model) {

        // Observe product data
        model.getObservableCrime().observe(getViewLifecycleOwner(), new Observer<CrimeEntity>() {
            @Override
            public void onChanged(@Nullable CrimeEntity crimeEntity) {
                model.setCrime(crimeEntity);
            }
        });

    }

    /** Creates product fragment for specific product ID */
    public static CrimeFragment forCrime(int crimeId) {
        CrimeFragment fragment = new CrimeFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_CRIME_ID, crimeId);
        fragment.setArguments(args);
        return fragment;
    }
}
