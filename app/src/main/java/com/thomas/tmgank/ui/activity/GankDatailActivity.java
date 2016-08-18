package com.thomas.tmgank.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.thomas.tmgank.R;
import com.thomas.tmgank.model.ResultDaily;
import com.thomas.tmgank.ui.fragment.DailyFragment;

public class GankDatailActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getIntent().getExtras();
        ResultDaily gankDaily= (ResultDaily) bundle.getSerializable(DailyFragment.gankDaily);
        DailyFragment fragment= DailyFragment.newInstance(gankDaily);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
        setContentView(R.layout.activity_gank_datail);

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

}
