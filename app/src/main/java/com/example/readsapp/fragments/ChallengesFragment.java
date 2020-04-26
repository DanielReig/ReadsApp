package com.example.readsapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.readsapp.R;
import com.example.readsapp.adapters.ChallengesViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ChallengesFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChallengesViewPagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_challenges, container, false);

        /** VIEW PAGER*/
        pagerAdapter = new ChallengesViewPagerAdapter(getChildFragmentManager());
        viewPager = mView.findViewById(R.id.challenges_viewpager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = mView.findViewById(R.id.challenges_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
