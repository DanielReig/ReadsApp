package com.example.readsapp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.readsapp.fragments.CompletedChallengesFragment;
import com.example.readsapp.fragments.CurrentChallengesFragment;

public class ChallengesViewPagerAdapter extends FragmentStatePagerAdapter {

    public ChallengesViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return CurrentChallengesFragment.newInstance("hola", "adios");
            case 1: return CompletedChallengesFragment.newInstance("hola", "adios");

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Current";
            case 1: return "Completed";
        }
        return "";
    }
}
