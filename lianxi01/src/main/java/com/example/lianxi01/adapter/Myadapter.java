package com.example.lianxi01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxi01.R;
import com.example.lianxi01.bean.GetCartsbean;
import com.example.lianxi01.bean.PriceAndCount;
import com.example.lianxi01.view.SecondActivity;

import java.util.List;

/**
 * Created by wan on 2017/12/18.
 */
public class Myadapter extends BaseExpandableListAdapter{
    Context context;
    List<GetCartsbean.DataBean> group;
    List<List<GetCartsbean.DataBean.ListBean>> child;
    private final LayoutInflater inflater;


    public Myadapter(Context context, List<GetCartsbean.DataBean> group, List<List<GetCartsbean.DataBean.ListBean>> child) {
        this.child=child;
        this.context=context;
        this.group=group;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final GroupViewHolder holder;
        if(convertView==null){
            view = inflater.inflate(R.layout.group, null);
            holder = new GroupViewHolder();
            holder.textname=view.findViewById(R.id.shopname);
            holder.groupbox=view.findViewById(R.id.groupbox);
            view.setTag(holder);
        }else {
            view=convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        final GetCartsbean.DataBean dataBean = group.get(groupPosition);
        holder.textname.setText(dataBean.getSellerName());
        holder.groupbox.setChecked(dataBean.isChecked());
        holder.groupbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(holder.groupbox.isChecked());
                setChildrenCb(groupPosition,holder.groupbox.isChecked());
                ((SecondActivity) context).setAllChecked(isAllGroupCbChecked());
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });


        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder;
        if(convertView==null){
            view =  inflater.inflate(R.layout.child, null);
            holder=new ChildViewHolder();
            holder.childbox=view.findViewById(R.id.childbox);
            holder.image=view.findViewById(R.id.image);
            holder.name=view.findViewById(R.id.name);
            holder.tvSubhead=view.findViewById(R.id.tvSubhead);
            holder.price= view.findViewById(R.id.price);
            holder.delimage=view.findViewById(R.id.delimage);
            holder.num=view.findViewById(R.id.num);
            holder.addimage=view.findViewById(R.id.addimage);
            holder.button=view.findViewById(R.id.button);
            view.setTag(holder);
        }else {
            view=convertView;
            holder= (ChildViewHolder) view.getTag();
        }
        final GetCartsbean.DataBean.ListBean listBean = child.get(groupPosition).get(childPosition);
        holder.childbox.setChecked(child.get(groupPosition).get(childPosition).isChecked());
        String images = listBean.getImages();
        Glide.with(context).load(images.split("\\|")[0]).into(holder.image);
        holder.name.setText(listBean.getTitle());
        holder.tvSubhead.setText(listBean.getSubhead());
        holder.price.setText(listBean.getPrice()+"元");
        holder.num.setText(listBean.getNum()+"");
        holder.childbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setChecked(holder.childbox.isChecked());
                group.get(groupPosition).setChecked(isAllChildCbChecked(groupPosition));
                ((SecondActivity) context).setAllChecked(isAllGroupCbChecked());
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });
        holder.addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = listBean.getNum();
                num++;
                listBean.setNum(num);
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });
        holder.delimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = listBean.getNum();
                if(num<=1){
                    num=1;
                }else {
                    num--;
                }
                listBean.setNum(num);
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GetCartsbean.DataBean.ListBean> listBeen = child.get(groupPosition);
                if(listBeen.size()>0){
                    listBeen.remove(childPosition);
                }
                if (listBeen.size() == 0) {
                    child.remove(groupPosition);
                    group.remove(groupPosition);
                }
                setPriceAndCount();
                ((SecondActivity) context).setAllChecked(isAllGroupCbChecked());

                notifyDataSetChanged();
            }
        });
        return view;
    }

    /**
     * 判断子条目是否全选
     * @param groupPosition
     * @return
     */
    private boolean isAllChildCbChecked(int groupPosition) {
        List<GetCartsbean.DataBean.ListBean> listBeen = child.get(groupPosition);
        for (int i = 0; i <listBeen.size() ; i++) {
            if(!listBeen.get(i).isChecked()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        TextView textname;
        CheckBox groupbox;
    }
    class ChildViewHolder{
        CheckBox childbox;
        ImageView image;
        TextView name;
        TextView tvSubhead;
        TextView price;
        ImageView delimage;
        TextView num;
        ImageView addimage;
        Button button;
    }

    /**
     * 发送到View显示
     */
    private void setPriceAndCount(){
        ((SecondActivity)context).setPriceAndCount(compete());
    }

    /**
     *  计算钱和数量
     * @return
     */
    private PriceAndCount compete(){
        double price=0;
        int num=0;
        for (int i = 0; i < group.size(); i++) {
            List<GetCartsbean.DataBean.ListBean> listBeans = child.get(i);
            for (int j = 0; j < listBeans.size(); j++) {
                if (listBeans.get(j).isChecked()) {
                    price += listBeans.get(j).getPrice() * listBeans.get(j).getNum();
                    num += listBeans.get(j).getNum();
                }
            }
        }
        return new PriceAndCount((int) price,num);
    }

    /**
     * 判断一级条目是否全选
     * @return
     */

    private boolean isAllGroupCbChecked() {
        if (group.size() == 0) {
            return false;
        }
        for (int i = 0; i < group.size(); i++) {
            if (!group.get(i).isChecked()) {
                return false;
            }
        }
        return true;
    }
    private void setChildrenCb(int groupPosition,boolean bool){
        List<GetCartsbean.DataBean.ListBean> listBeen = child.get(groupPosition);
        for (int i = 0; i <listBeen.size() ; i++) {
            listBeen.get(i).setChecked(bool);
        }
    }
    public void AllOrNone(boolean b){
        for (int i = 0; i <group.size() ; i++) {
            group.get(i).setChecked(b);
            setChildrenCb(i,b);
        }
        setPriceAndCount();
        notifyDataSetChanged();
    }
}
