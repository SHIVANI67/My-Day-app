package com.example.mydayapplication.Adapters;

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
    private ArrayList<Tasks> morningList;
    private ArrayList<Tasks> afternoonList;
    private ArrayList<Tasks> eveningList;
    private ArrayList<Tasks> nightList;

    public DaysPagerAdapter(@NonNull FragmentManager fm, int behavior, int index, ArrayList<Tasks> morningList, ArrayList<Tasks> afternoonList, ArrayList<Tasks> eveningList, ArrayList<Tasks> nightList) {
        super(fm, behavior);
        this.index = index;
        this.morningList = morningList;
        this.afternoonList = afternoonList;
        this.eveningList = eveningList;
        this.nightList = nightList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        UserTaskFragment fragment;
        switch (position) {
            case 0:
                fragment = UserTaskFragment.newInstance(index, morningList);
                return fragment;
            case 1:
                fragment = UserTaskFragment.newInstance(index, afternoonList);
                return fragment;
            case 2:
                fragment = UserTaskFragment.newInstance(index, eveningList);
                return fragment;
            case 3:
                fragment = UserTaskFragment.newInstance(index, nightList);
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
