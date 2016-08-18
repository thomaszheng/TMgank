package com.thomas.tmgank.ui.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thomas.tmgank.adapter.CollectAdapter;
import com.thomas.tmgank.dao.CollectionDao;
import com.thomas.tmgank.model.Collection;

import java.util.List;

/**
 * Created by thomas on 2015/10/16.
 */
public class CollectFragment extends BaseFragment {
    List<Collection>list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CollectionDao dao=new CollectionDao(getContext());
        list =dao.select();
    }

    public static CollectFragment newInstance() {
        CollectFragment fragment = new CollectFragment();
        return fragment;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("收藏");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        CollectAdapter adapter= new CollectAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //TODO 上拉加载数据
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        // 下拉刷新
    }
}
