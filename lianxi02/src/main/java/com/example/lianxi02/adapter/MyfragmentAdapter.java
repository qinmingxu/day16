package com.example.lianxi02.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lianxi02.R;
import com.example.lianxi02.bean.GetOrdersbean;

import java.util.List;

/**
 * Created by wan on 2017/12/21.
 */
public class MyfragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<GetOrdersbean.DataBean> list;
    public MyfragmentAdapter(Context context, List<GetOrdersbean.DataBean> list) {
        this.list=list;
        this.context=context;
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
        holder1.price.setText(list.get(position).getPrice()+"");
        holder1.time.setText(list.get(position).getCreatetime());
        int status = list.get(position).getStatus();
        if(status==0){
            holder1.status.setTextColor(Color.RED);
            holder1.status.setText("待支付");
            holder1.button.setText("取消订单");
        }else if (status==1){
            holder1.status.setText("已支付");
            holder1.button.setText("查看订单");
        }else if (status==2){
            holder1.status.setText("已取消");
            holder1.button.setText("查看订单");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView price;
        private final TextView time;
        private final TextView status;
        private final Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
            button = itemView.findViewById(R.id.dingdan);

        }
    }
}
