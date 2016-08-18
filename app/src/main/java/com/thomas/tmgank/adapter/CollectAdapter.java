package com.thomas.tmgank.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thomas.tmgank.R;
import com.thomas.tmgank.dao.CollectionDao;
import com.thomas.tmgank.model.Collection;
import com.thomas.tmgank.ui.activity.DailyActivity;
import com.thomas.tmgank.util.TimeKit;

import java.util.List;

/**
 * Created by thomas on 2015/10/18.
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.CollectHolder>{

    List<Collection> list;
    Context context;
    public CollectAdapter(Context context,List<Collection> list) {
        this.list=list;
        this.context=context;
    }

    @Override
    public CollectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank,null);
        return new CollectHolder(view);
    }

    @Override
    public void onBindViewHolder(CollectHolder holder, int position) {

        Collection collection=list.get(position);
        holder.title.setText(collection.title);
        holder.time.setText(TimeKit.format(collection.time));
        holder.user.setVisibility(View.GONE);
        holder.collection=collection;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CollectHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView title, user, time;
        public Collection collection;
        public CollectHolder(View itemView) {
            super(itemView);
            this.collection=collection;
            title = (TextView) itemView.findViewById(R.id.title);
            user = (TextView) itemView.findViewById(R.id.user);
            time = (TextView) itemView.findViewById(R.id.time);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DailyActivity.class);
            intent.putExtra("url", collection.url);
            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {

            final CollectionDao dao=new CollectionDao(context);
            AlertDialog.Builder builder= new AlertDialog.Builder(context);
            builder.setTitle("删除").setMessage("真的要删除这个收藏吗？不要后悔哦!")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.delete(collection.url);
                           list.remove(collection);
                            notifyDataSetChanged();
                        }
                    });
            AlertDialog dialog=builder.create();
            dialog.show();

            return true;
        }
    }
}
