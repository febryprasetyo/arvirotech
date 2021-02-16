package com.arvirotech.monev.marketing.perencana;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.compon.AddDataMar;
import com.arvirotech.monev.marketing.MarMain;
import com.arvirotech.monev.marketing.adapter.viewPagerAdapterMar;
import com.google.android.material.tabs.TabLayout;

public class PerencanaMarketing extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    viewPagerAdapterMar viewPagerAdapter;

    ImageButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perencana_marketing);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        fab = findViewById(R.id.fabAdd);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPagerAdapter = new viewPagerAdapterMar(getSupportFragmentManager());

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerencanaMarketing.this, AddDataMar.class));
            }
        });


    }

    //back button

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PerencanaMarketing.this, MarMain.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}