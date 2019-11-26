package com.example.map_crime;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.map_crime.databinding.ListFragmentBinding;

import java.util.List;

public class CrimeListFragment extends Fragment {

    public static final String TAG = "CrimeListFragment";

    private CrimeListAdapter mCrimeAdapter;

    private ListFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        mCrimeAdapter = new CrimeListAdapter(mCrimeClickCallback);
        mBinding.crimeList.setAdapter(mCrimeAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CrimeListViewModel viewModel =
                new ViewModelProvider(this).get(CrimeListViewModel.class);

        mBinding.crimesSearchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable query = mBinding.crimesSearchBox.getText();
                if (query == null || query.toString().isEmpty()) { subscribeUi(viewModel.getCrimes());
                } else {
                    subscribeUi(viewModel.searchCrimes("*" + query + "*"));
                }
            }
        });

        subscribeUi(viewModel.getCrimes());
    }

    private void subscribeUi(LiveData<List<CrimeEntity>> liveData) {
        // Update the list when the data changes
        liveData.observe(getViewLifecycleOwner(), new Observer<List<CrimeEntity>>() {
            @Override
            public void onChanged(@Nullable List<CrimeEntity> myCrimes) {
                if (myCrimes != null) {
                    mBinding.setIsLoading(false);
                    mCrimeAdapter.setCrimeList(myCrimes);
                } else {
                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final CrimeClickCallback mCrimeClickCallback = new CrimeClickCallback() {
        @Override
        public void onClick(Crime crime) {

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((CrimeListActivity) getActivity()).show(crime);
            }
        }
    };
}