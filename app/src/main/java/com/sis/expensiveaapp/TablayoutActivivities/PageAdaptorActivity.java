package com.sis.expensiveaapp.TablayoutActivivities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdaptorActivity extends FragmentPagerAdapter {
    List<String> tabname=new ArrayList<>();
    List<Fragment> tabfragment=new ArrayList<>();
    public PageAdaptorActivity(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabfragment.get(position);
    }
    public CharSequence getPageTitle(int position) {
        return tabname.get(position);
    }

    @Override
    public int getCount() {
        return tabname.size();
    }
    public void AddingTabFragment(Fragment fragment, String name){
        tabname.add(name);
        tabfragment.add(fragment);
    }

}
