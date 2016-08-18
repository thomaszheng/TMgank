package com.thomas.tmgank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.thomas.tmgank.Config;
import com.thomas.tmgank.R;
import com.thomas.tmgank.model.Gank;
import com.thomas.tmgank.ui.activity.DailyActivity;

import java.util.List;

/**
 * Created by thomas on 2015/10/14.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder>{

    List<Gank> list;
    Context context;
    public ImageAdapter( Context context,List<Gank> list) {
        this.list=list;
        this.context=context;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {

        Gank gank=list.get(position);
        holder.gank=gank;
        holder.time.setText(gank.publishedAt.substring(0,10));
        holder.who.setText(gank.who);
        ImageLoader.getInstance().displayImage(gank.url,holder.icon, Config.pager_options);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icon;
        TextView who, time;
        Gank gank;
        public ImageHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            who = (TextView) itemView.findViewById(R.id.who);
            time = (TextView) itemView.findViewById(R.id.time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DailyActivity.class);
            intent.putExtra("url", gank.url);
            context.startActivity(intent);
        }
    }
}
