package com.thomas.tmgank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomas.tmgank.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @InjectView(R.id.swipeRefreshLayout)
    public  SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.recyclerView)
    public RecyclerView recyclerView;
    public BaseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.inject(this, view);
        initView(view);
        return view;
    }

    public void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void onFailures() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void onSuccess() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
    }

}
