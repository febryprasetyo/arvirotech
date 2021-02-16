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
import com.arvirotech.monev.marketing.perencana.TabAll;
import com.arvirotech.monev.marketing.perencana.TabLimaPuluh;
import com.arvirotech.monev.marketing.perencana.TabNol;
import com.arvirotech.monev.marketing.perencana.TabSeratus;

public class viewPagerAdapterMar extends FragmentPagerAdapter {
    public viewPagerAdapterMar(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new TabAll();
            case 1: return new TabNol();
            case 2: return new TabLimaPuluh();
            case 3: return new TabSeratus();
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
