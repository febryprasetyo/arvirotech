package com.arvirotech.monev.marketing.konstruksi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.MarMain;
import com.arvirotech.monev.marketing.compon.AddDataKonMar;
import com.arvirotech.monev.marketing.adapter.viewPagerAdapterKon;
import com.arvirotech.monev.marketing.compon.AddDataMar;
import com.arvirotech.monev.marketing.perencana.PerencanaMarketing;
import com.google.android.material.tabs.TabLayout;

public class KonstruksiMarketing extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    viewPagerAdapterKon viewPagerAdapter;

    ImageButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konstruksi_marketing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Konstruksi");

        fab = findViewById(R.id.fabAdd);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPagerAdapter = new viewPagerAdapterKon(getSupportFragmentManager());

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KonstruksiMarketing.this, AddDataKonMar.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}