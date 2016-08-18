package com.thomas.tmgank.ui.activity;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.thomas.tmgank.R;
import com.thomas.tmgank.ui.fragment.WebFragment;

public class DailyActivity extends AppCompatActivity {

    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    WebFragment webFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        initView();
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        String title = getResources().getString(R.string.app_name);
        setTitle(title);
        webFragment =WebFragment.newInstance(url, title);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,webFragment).commit();

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
        if (webFragment!=null){
            return  webFragment.onKeyDown(keyCode, event);
        }else {
            return super.onKeyDown(keyCode, event);

        }
    }
}
