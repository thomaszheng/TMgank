package com.thomas.tmgank.ui.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.thomas.tmgank.adapter.GankAdapter;
import com.thomas.tmgank.http.DataManager;
import com.thomas.tmgank.model.Gank;
import com.thomas.tmgank.util.JsonKit;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 2015/10/15.
 */
public class GankFragment extends BaseFragment {

    private static final String URL = "param1";
    String url;
    List<Gank> list= new ArrayList<>();
    GankAdapter adapter;
    int pagercount=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(URL);
            DataManager.getGankList(url,mHandler);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {
            String result="";
            try {

                InputStream in=new ByteArrayInputStream(bytes);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] b= new byte[1024];
                int len=-1;
                while((len=in.read(b))!=-1){
                    out.write(b, 0, len);
                }

                result= new String(out.toByteArray(),"UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result!=null){
                List<Gank> n= JsonKit.generateList(result);
                list.addAll(n);
                adapter.notifyDataSetChanged();
            }

            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            onFailures();
        }
    };

    public static GankFragment newInstance(String param1){
        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putString(URL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("资源分类");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter= new GankAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    url=url.substring(0,url.length() - 1) + pagercount;
                DataManager.getGankList(url,mHandler);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        // 下拉刷新
    }
}
