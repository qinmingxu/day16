package com.example.zhoumolianxi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhoumolianxi.MainActivity;
import com.example.zhoumolianxi.R;
import com.example.zhoumolianxi.bean.Databean;

import java.util.List;

/**
 * Created by wan on 2017/12/16.
 */
public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Databean.DataBean> list;
    public Myadapter(Context context, List<Databean.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.title.setText(list.get(position).getTitle());
        int status = list.get(position).getStatus();
        if(status==0){
            holder1.status.setTextColor(Color.RED);
            holder1.status.setText("待支付");
        }else if(status==1){
            holder1.status.setText("已支付");
        }else {
            holder1.status.setText("已取消");
        }
        holder1.price.setText(list.get(position).getPrice()+"");
        holder1.time.setText(list.get(position).getCreatetime());
        if(status==0){
            holder1.button.setText("取消订单");
        }else {
            holder1.button.setText("查看订单");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView status;
        private final TextView price;
        private final TextView time;
        private final Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.time);
            button = itemView.findViewById(R.id.button);
        }
    }
}
