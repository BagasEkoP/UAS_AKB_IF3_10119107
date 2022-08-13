package com.example.uas_akb_if3_10119107;

//NIM   : 10119107
//Nama  : Bagas Eko Pambudi
//Kelas : IF-3

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewpagerActivity extends AppCompatActivity {

    ViewPager viewpager;
    viewpageradapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        viewpager = findViewById(R.id.view_pager);
        viewPagerAdapter = new viewpageradapter(this);

        viewpager.setAdapter(viewPagerAdapter);
    }

    public void onClick(View view){
        Intent intent = new Intent(ViewpagerActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}