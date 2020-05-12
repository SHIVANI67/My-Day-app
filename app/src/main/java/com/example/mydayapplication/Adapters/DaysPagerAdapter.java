package com.example.mydayapplication.Adapters;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mydayapplication.Fragment.UserTaskFragment;
import com.example.mydayapplication.Pojo.Tasks;

import java.util.ArrayList;

public class DaysPagerAdapter extends FragmentPagerAdapter {
    int index;

    public DaysPagerAdapter(@NonNull FragmentManager fm, int behavior, int index) {
        super(fm, behavior);
        this.index = index;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        UserTaskFragment fragment;
        switch (position) {
            case 0:
                fragment = UserTaskFragment.newInstance(index, 0);
                return fragment;
            case 1:
                fragment = UserTaskFragment.newInstance(index, 1);
                return fragment;
            case 2:
                fragment = UserTaskFragment.newInstance(index, 2);
                return fragment;
            case 3:
                fragment = UserTaskFragment.newInstance(index, 3);
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Morning";
            case 1: return "Afternoon";
            case 2: return "Evening";
            case 3: return "Night";
            default: return null;
        }
    }
}
