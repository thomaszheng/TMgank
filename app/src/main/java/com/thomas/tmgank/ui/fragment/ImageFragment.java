package com.thomas.tmgank.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.thomas.tmgank.adapter.ImageAdapter;
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


public class ImageFragment extends BaseFragment {

    private static final String URL = "param1";
    String url;
    List<Gank> list= new ArrayList<>();
    int pagercount=1;
    ImageAdapter adapter;


    public static ImageFragment newInstance(String param1) {
        ImageFragment fragment = new ImageFragment();
        Bundle bundle= new Bundle();
        bundle.putString(URL,param1);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b=getArguments();
        if (b!=null){
            url=b.getString(URL);
        }
        DataManager.getGankList(url, mHandler);

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

        }
    };

    @Override
    public void initView(View view) {
        super.initView(view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter= new ImageAdapter(getContext(),list);
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
