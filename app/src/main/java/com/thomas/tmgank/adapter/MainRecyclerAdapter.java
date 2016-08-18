package com.thomas.tmgank.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thomas.tmgank.Config;
import com.thomas.tmgank.R;
import com.thomas.tmgank.model.ResultDaily;
import com.thomas.tmgank.ui.activity.GankDatailActivity;
import com.thomas.tmgank.ui.fragment.DailyFragment;
import com.thomas.tmgank.ui.widget.RatioImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 2015/10/17.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.RecyclerHolder> {

    private static List<ResultDaily> list= new ArrayList<>();
    private static Context context;
    private BitmapUtils bu;


    public MainRecyclerAdapter(Context context,List<ResultDaily> list){
        this.list=list;
        this.context=context;
        bu=new BitmapUtils(context);

    }
    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        ResultDaily gank=list.get(position);
        holder.gankDaily=gank;
        holder.textView.setText(gank.results.ganks.get(3).get(0).desc);
        int s=gank.results.types.size();
        bu.display( holder.imageView,gank.results.getGanhuo(s-1).get(0).url);
      //  ImageLoader.getInstance().displayImage(gank.results.getGanhuo(s-1).get(0).url, holder.imageView, Config.pager_options);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public static  class  RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RatioImageView imageView;
        public TextView textView;
        ResultDaily gankDaily;

        public RecyclerHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView= (RatioImageView) itemView.findViewById(R.id.iv_meizhi);
            textView= (TextView) itemView.findViewById(R.id.tv_title);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
           Intent intent= new Intent(context, GankDatailActivity.class);
            Bundle bundle= new Bundle();
            bundle.putSerializable(DailyFragment.gankDaily,gankDaily);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
