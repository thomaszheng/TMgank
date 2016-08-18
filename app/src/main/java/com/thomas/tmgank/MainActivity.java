package com.thomas.tmgank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.thomas.tmgank.http.DataManager;
import com.thomas.tmgank.adapter.MainRecyclerAdapter;
import com.thomas.tmgank.model.ResultDaily;
import com.thomas.tmgank.ui.activity.SettingActivity;
import com.thomas.tmgank.ui.activity.TabActivity;
import com.thomas.tmgank.ui.fragment.InfoDialog;
import com.thomas.tmgank.util.JsonKit;
import com.thomas.tmgank.util.TextKit;
import com.thomas.tmgank.util.TimeKit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private LinearLayoutManager mLayoutManager;
    MainRecyclerAdapter adapter;
    DrawerLayout drawer;
    SwipeRefreshLayout srl_refresh;
    private static int FIRSTITEMCOUNT=10;
    private int itemCount=0;
    private List<ResultDaily> list=new ArrayList<ResultDaily>();
    private Date lastDate;
    private boolean addtoheader =true;

    int lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        lastDate=new Date();
        loadData();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //TODO github登陆
            }
        });


       srl_refresh = (SwipeRefreshLayout) findViewById(R.id.srl_refresh);
        RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);


        final StaggeredGridLayoutManager mLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(mLayoutManager);
        adapter = new MainRecyclerAdapter(this,list);
        recycler_view.setAdapter(adapter);


        recycler_view.setOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    addtoheader=false;
                    System.out.println("上拉加载了");
                    srl_refresh.setRefreshing(true);
                    loadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems =mLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem =visibleItems[0]>visibleItems[1]?visibleItems[0]:visibleItems[1];
            }

        });


        srl_refresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("下拉刷新");
                loadData();

                addtoheader=true;
            }


        });


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler(){

        @Override
        public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
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

                ResultDaily resultDaily= JsonKit.generate(result);

                if (resultDaily.results!=null && resultDaily.results.types.size()!=0){
                    if (addtoheader){
                        list.add(resultDaily);
                        itemCount++;
                        adapter.notifyDataSetChanged();
                    }else {
                        //上拉加载
                        list.add(list.size(),resultDaily);
                        itemCount++;
                        adapter.notifyDataSetChanged();
                    }
                }

                srl_refresh.setRefreshing(false);

            }
        }

        @Override
        public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {

            System.out.println("失败了");
        }
    };
    // 下拉异步加载数据
    private void loadData() {

        for (int i=0;i<FIRSTITEMCOUNT;i++){
                //TODO 需要完善
                String s=TimeKit.toDate(lastDate);
                DataManager.getGankList("day/"+s, mHandler);
                lastDate=TimeKit.getLastdayDate(lastDate);

        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.type) {
            Intent intent = new Intent(this, TabActivity.class);
            intent.putExtra("collect",false);
            startActivity(intent);
       /* } else if (id == R.id.random) {*/

        } else if (id == R.id.collect) {
            Intent intent = new Intent(this, TabActivity.class);
            intent.putExtra("collect",true);
            startActivity(intent);
        } else if (id == R.id.setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.info) {
            InfoDialog info=InfoDialog.newInstance("关于", TextKit.getInfo());
            info.show(getSupportFragmentManager(),"info");
        }

        return true;
    }


}
