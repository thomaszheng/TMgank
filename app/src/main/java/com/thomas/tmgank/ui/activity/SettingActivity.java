package com.thomas.tmgank.ui.activity;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.thomas.tmgank.R;
import com.thomas.tmgank.ui.fragment.SettingFragment;
import com.thomas.tmgank.ui.fragment.WebFragment;

public class SettingActivity extends AppCompatActivity {

    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    SettingFragment settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
    }
    private void initData() {
        String title = getResources().getString(R.string.setting);
        setTitle(title);
        settingFragment = new SettingFragment();
        getFragmentManager().beginTransaction().replace(R.id.container,settingFragment).commit();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.container);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (settingFragment!=null){
            return  settingFragment.onKeyDown(keyCode, event);
        }else {
            return super.onKeyDown(keyCode, event);

        }
    }
}
