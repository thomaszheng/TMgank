package com.thomas.tmgank.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thomas.tmgank.App;
import com.thomas.tmgank.R;
import com.thomas.tmgank.model.DailyGank;
import com.thomas.tmgank.model.Gank;
import com.thomas.tmgank.util.TextKit;

import java.util.List;

/**
 * Created by thomas on 2015/10/12.
 */
public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyHolder> {

    private DailyGank results;
    public DailyAdapter(DailyGank results) {
        this.results=results;
    }


    @Override
    public DailyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily, null);
        DailyHolder holder=new DailyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DailyHolder holder, int position) {

        List<Gank>item= results.getGanhuo(position);
        if (item!=null ){
            holder.title.setText(results.getType(position));
            holder.content.setText(TextKit.generate(item, App.getContext().getResources().getColor(R.color.material_lightBlue)));
        }



    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    class DailyHolder extends  RecyclerView.ViewHolder{


        public TextView title, content;
        public View itemView;

        public DailyHolder(View itemView) {
            super(itemView);
        this.itemView=itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            content.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
