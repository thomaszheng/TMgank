package com.thomas.tmgank.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.thomas.tmgank.R;
import com.thomas.tmgank.adapter.ClassiftAdapter;
import com.thomas.tmgank.model.Collection;
import com.thomas.tmgank.ui.fragment.CollectFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TabActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;


    boolean iscollect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.inject(this);
        iscollect= getIntent().getBooleanExtra("collect",false);

        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar= getSupportActionBar();
        //actionBar.setTitle("TMgank");
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (iscollect){
            tabLayout.setVisibility(View.GONE);
            FragmentPagerAdapter a= new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return  CollectFragment.newInstance();
                }

                @Override
                public int getCount() {
                    return 1;
                }
            };
            viewPager.setAdapter(a);


        }else {
            final String[] classify = getResources().getStringArray(R.array.classify);

            ClassiftAdapter adapter=new ClassiftAdapter(getSupportFragmentManager(),classify);
            viewPager.setAdapter(adapter);

            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabsFromPagerAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
