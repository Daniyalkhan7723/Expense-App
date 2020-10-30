package com.sis.expensiveaapp.TablayoutActivivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.sis.expensiveaapp.R;

public class DetailsActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TabLayout tabLayout = findViewById(R.id.tablayout);
        setSupportActionBar(toolbar);
        ViewPager viewPager = findViewById(R.id.viewPager);
        PageAdaptorActivity incomeTabLayout = new PageAdaptorActivity(getSupportFragmentManager());
        incomeTabLayout.AddingTabFragment(new TabIncome(), "Income");
        incomeTabLayout.AddingTabFragment(new TabSaving(), "Savings");
        incomeTabLayout.AddingTabFragment(new TabLiability(), "Liability");
        incomeTabLayout.AddingTabFragment(new TabAssets(), "Assets");
        viewPager.setAdapter(incomeTabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }
}

//      TabLayout tabLayout=findViewById(R.id.tablayout);
//     tabLayout.addTab(tabLayout.newTab().setText("INCOME"));
//      tabLayout.addTab(tabLayout.newTab().setText("SAVINGS"));
//      tabLayout.addTab(tabLayout.newTab().setText("Liability"));
//      tabLayout.addTab(tabLayout.newTab().setText("Assets"));
//      tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//      ViewPager viewPager=findViewById(R.id.viewPager);
//       getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,new TabIncome()).commit();
//       PageAdaptorActivity pagerAdapter=new PageAdaptorActivity(getSupportFragmentManager(),tabLayout.getTabCount());
//       viewPager.setAdapter(pagerAdapter);
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.)
//     return super.onOptionsItemSelected(item);
//
//    }
//}