package com.thomas.tmgank.ui.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.thomas.tmgank.Config;
import com.thomas.tmgank.R;
import com.thomas.tmgank.adapter.DailyAdapter;
import com.thomas.tmgank.model.ResultDaily;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DailyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyFragment extends Fragment implements View.OnClickListener {

    public  static final String gankDaily = "gankDaily";
    private ResultDaily gank;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @InjectView(R.id.icon)
    ImageView icon;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    private DailyAdapter dailyAdapter;

    public static DailyFragment newInstance(ResultDaily g) {
        DailyFragment fragment = new DailyFragment();
        Bundle args = new Bundle();
        args.putSerializable(gankDaily,g);
        fragment.setArguments(args);
        return fragment;
    }

    public DailyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            gank = (ResultDaily) getArguments().getSerializable(gankDaily);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_daily, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitle(R.string.app_name);

        int s=gank.results.types.size();
        ImageLoader.getInstance().displayImage(gank.results.getGanhuo(s-1).get(0).url,icon, Config.pager_options);

        dailyAdapter = new DailyAdapter(gank.results);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(dailyAdapter);

        fab.setOnClickListener(this);

    }

    protected void setSupportActionBar(Toolbar toolbar) {
        Activity activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            appCompatActivity.setSupportActionBar(toolbar);
        }
    }

    protected ActionBar getSupportActionBar() {
        Activity activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            return appCompatActivity.getSupportActionBar();
        }
        return null;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                //浮动按钮点击
                break;

        }

    }
}
