package com.arvirotech.monev.marketing.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.arvirotech.monev.marketing.konstruksi.TabAllKon;
import com.arvirotech.monev.marketing.konstruksi.TabLimapuluhKon;
import com.arvirotech.monev.marketing.konstruksi.TabNolKon;
import com.arvirotech.monev.marketing.konstruksi.TabSeratusKon;

public class viewPagerAdapterKon extends FragmentPagerAdapter {
    public viewPagerAdapterKon(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new TabAllKon();
            case 1: return new TabNolKon();
            case 2: return new TabLimapuluhKon();
            case 3: return new TabSeratusKon();
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
