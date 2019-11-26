package com.example.map_crime;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.map_crime.databinding.CrimeItemBinding;

import java.util.List;
import java.util.Objects;

public class CrimeListAdapter extends RecyclerView.Adapter<CrimeListAdapter.CrimeViewHolder> {
    List<? extends Crime> mCrimeList;

    @Nullable
    private final CrimeClickCallback mCrimeClickCallback;

    public CrimeListAdapter(@Nullable CrimeClickCallback clickCallback) {
        mCrimeClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setCrimeList(final List<? extends Crime> crimeList) {
        if (mCrimeList == null) {
            mCrimeList = crimeList;
            notifyItemRangeInserted(0, crimeList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCrimeList.size();
                }

                @Override
                public int getNewListSize() {
                    return mCrimeList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCrimeList.get(oldItemPosition).getId() ==
                            crimeList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Crime newCrime =  crimeList.get(newItemPosition);
                    Crime oldCrime = mCrimeList.get(oldItemPosition);
                    return newCrime.getId() == oldCrime.getId()
                            && Objects.equals(newCrime.getCrimeType(), oldCrime.getCrimeType())
                            && Objects.equals(newCrime.getDate(), oldCrime.getDate())
                            && newCrime.getWeapon() == oldCrime.getWeapon();
                }
            });
            mCrimeList = crimeList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CrimeItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.crime_item,
                        parent, false);
        binding.setCallback(mCrimeClickCallback);
        return new CrimeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CrimeViewHolder holder, int position) {
        holder.binding.setCrime(mCrimeList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCrimeList == null ? 0 : mCrimeList.size();
    }

    @Override
    public long getItemId(int position) {
        return mCrimeList.get(position).getId();
    }

    static class CrimeViewHolder extends RecyclerView.ViewHolder {

        final CrimeItemBinding binding;

        public CrimeViewHolder(CrimeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

