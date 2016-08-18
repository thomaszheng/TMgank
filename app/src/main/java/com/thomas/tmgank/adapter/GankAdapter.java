package com.thomas.tmgank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thomas.tmgank.R;
import com.thomas.tmgank.model.Gank;
import com.thomas.tmgank.ui.activity.DailyActivity;
import com.thomas.tmgank.util.TimeKit;

import java.util.Date;
import java.util.List;

/**
 * Created by thomas on 2015/10/14.
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankHolder> {

    List<Gank> list;
    Context context;
    public GankAdapter( Context context,List<Gank> list) {
        this.list=list;
        this.context=context;
    }

    @Override
    public GankHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, null);
        return new GankHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(GankHolder holder, int position) {

        Gank gank = list.get(position);

        holder.title.setText(gank.desc);
        holder.user.setText(gank.who);
        holder.time.setText(gank.publishedAt.substring(0,10));
        holder.gank=gank;
    }

     class GankHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView title, user, time;
        public Gank gank;
        public GankHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            user = (TextView) itemView.findViewById(R.id.user);
            time = (TextView) itemView.findViewById(R.id.time);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DailyActivity.class);
            intent.putExtra("url", gank.url);
            context.startActivity(intent);

        }

        @Override
        public boolean onLongClick(View v) {
            //条目被长按的事件
            return false;
        }
    }
}
