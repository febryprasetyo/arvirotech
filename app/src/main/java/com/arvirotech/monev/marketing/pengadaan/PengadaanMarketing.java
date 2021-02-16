package com.arvirotech.monev.marketing.pengadaan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.adapter.viewPagerAdapterPeng;
import com.arvirotech.monev.model.listData;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class PengadaanMarketing extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    viewPagerAdapterPeng viewPagerAdapter;

    ImageButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengadaan_marketing);

        setContentView(R.layout.activity_konstruksi_marketing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Pengadaan");

        fab = findViewById(R.id.fabAdd);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPagerAdapter = new viewPagerAdapterPeng(getSupportFragmentManager());

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PengadaanMarketing.this, AddDataPengMar.class));
            }
        });
    }
}