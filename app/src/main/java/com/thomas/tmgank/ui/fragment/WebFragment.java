package com.thomas.tmgank.ui.fragment;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thomas.tmgank.App;
import com.thomas.tmgank.R;
import com.thomas.tmgank.dao.CollectionDao;
import com.thomas.tmgank.model.Collection;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebFragment extends Fragment {
    private static final String URL = "param1";
    private static final String TITLE = "param2";

    @InjectView(R.id.web_view)
    WebView webView;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    String url,title;
    MenuItem itemCollect;
    CollectionDao dao;

    public static WebFragment newInstance(String param1,String param2) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString(URL, param1);
        args.putString(TITLE, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dao= new CollectionDao(getContext());
        if (getArguments() != null) {
            url = getArguments().getString(URL);
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_web, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    progressBar.setVisibility(View.GONE);
                    progressBar.setProgress(0);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    // 加载中
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getActivity().setTitle(title);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDisplayZoomControls(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

    }




    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                getActivity().finish();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_web, menu);
        itemCollect = menu.findItem(R.id.menu_collect);
        if (dao.isExist(url)) {
            itemCollect.setTitle(R.string.cancel_collect);
        } else {
            itemCollect.setTitle(R.string.menu_collect);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_copy:
                copyUri(url);
                toast(R.string.success_copy);
                return true;
            case R.id.menu_open:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            case R.id.menu_collect:
               collect();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void collect() {
        if (dao.isExist(url)){
            dao.delete(url);
            Toast.makeText(getActivity(), R.string.success_uncollect, Toast.LENGTH_SHORT).show();
            itemCollect.setTitle(R.string.menu_collect);
        }else {

            dao.insert(new Collection(url, webView.getTitle(),new Date()));
            Toast.makeText(getActivity(), R.string.success_collect, Toast.LENGTH_SHORT).show();
            itemCollect.setTitle(R.string.cancel_collect);
        }
    }

    void toast(int id) {
        Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
    }
    public  void copyUri(String text) {
        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
    }


}
