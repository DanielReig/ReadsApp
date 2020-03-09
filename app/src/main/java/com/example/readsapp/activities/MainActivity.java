package com.example.readsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.readsapp.R;
import com.example.readsapp.fragments.ChallengesFragment;
import com.example.readsapp.fragments.DiscoverFragment;
import com.example.readsapp.fragments.ListsFragment;
import com.example.readsapp.fragments.ProfileFragment;
import com.example.readsapp.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new DiscoverFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()) {
                        case R.id.discover_nav_item:
                            selectedFragment = new DiscoverFragment();
                            break;
                        case R.id.lists_nav_item:
                            selectedFragment = new ListsFragment();
                            break;
                        case R.id.profile_nav_item:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.search_nav_item:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.challenges_nav_item:
                            selectedFragment = new ChallengesFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
