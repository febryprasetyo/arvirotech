package com.arvirotech.monev.marketing.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.arvirotech.monev.marketing.pengadaan.TabAllPeng;
import com.arvirotech.monev.marketing.pengadaan.TabLimaPeng;
import com.arvirotech.monev.marketing.pengadaan.TabNolPeng;
import com.arvirotech.monev.marketing.pengadaan.TabSeratusPeng;

public class viewPagerAdapterPeng extends FragmentPagerAdapter {
    public viewPagerAdapterPeng(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new TabAllPeng();
            case 1: return new TabNolPeng();
            case 2: return new TabLimaPeng();
            case 3: return new TabSeratusPeng();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Semua";
                case 1: return "0%";
                case 2: return "50%";
                case 3: return "100%";
            }
            return null;
        }
}
