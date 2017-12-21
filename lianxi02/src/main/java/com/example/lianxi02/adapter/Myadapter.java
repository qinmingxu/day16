package com.example.lianxi02.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxi02.R;
import com.example.lianxi02.bean.GetCartsbean;
import com.example.lianxi02.bean.PriceAndCount;
import com.example.lianxi02.view.ThirdActivity;

import java.util.List;

/**
 * Created by wan on 2017/12/20.
 */
public class Myadapter extends BaseExpandableListAdapter{
    Context context;
    List<GetCartsbean.DataBean> group;
    List<List<GetCartsbean.DataBean.ListBean>> child;


    public Myadapter(Context context, List<GetCartsbean.DataBean> group, List<List<GetCartsbean.DataBean.ListBean>> child) {
        this.context=context;
        this.group=group;
        this.child=child;
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
        final GroupViewHolder holder;
        View view;
        if(convertView==null){
            view = View.inflate(context, R.layout.group,null);
            holder=new GroupViewHolder();
            holder.groupBox=view.findViewById(R.id.groupBox);
            holder.text=view.findViewById(R.id.shopName);
            view.setTag(holder);
        }else {
            view=convertView;
            holder= (GroupViewHolder) view.getTag();
        }
        final GetCartsbean.DataBean dataBean = group.get(groupPosition);
        holder.groupBox.setChecked(dataBean.isChecked());
        holder.text.setText(dataBean.getSellerName());
        holder.groupBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要改变三个checkbox的状态值
                //1.一级列表的checkbox状态值
                dataBean.setChecked(holder.groupBox.isChecked());
                //2.二级列表的checkbox状态值
                setChildrenCb(groupPosition, holder.groupBox.isChecked());
                //3.全选的checkbox状态值
                ((ThirdActivity) context).setAllChecked(isAllGroupCbChecked());
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新界面
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder holder;
        View view;
        if(convertView==null){
            view = View.inflate(context, R.layout.child,null);
            holder=new ChildViewHolder();
            holder.childbox=view.findViewById(R.id.childBox);
            holder.image=view.findViewById(R.id.image);
            holder.title=view.findViewById(R.id.title);
            holder.tvSubhead=view.findViewById(R.id.tvSubhead);
            holder.price= view.findViewById(R.id.price);
            holder.delimage=view.findViewById(R.id.delimage);
            holder.count=view.findViewById(R.id.count);
            holder.addimage=view.findViewById(R.id.addimage);
            holder.button=view.findViewById(R.id.button);
            view.setTag(holder);
        }else {
            view=convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        final GetCartsbean.DataBean.ListBean listBean = child.get(groupPosition).get(childPosition);
        holder.childbox.setChecked(listBean.isChecked());
        String images = listBean.getImages();
        Glide.with(context).load(images.split("\\|")[0]).into(holder.image);
        holder.title.setText(listBean.getTitle());
        holder.tvSubhead.setText(listBean.getSubhead());
        holder.price.setText(listBean.getPrice()+"");
        holder.count.setText(listBean.getNum()+"");
        holder.childbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要改变三个checkbox的状态值
                //1.二级列表的checkbox状态值
                listBean.setChecked(holder.childbox.isChecked());
                //2.一级列表的checkbox状态值
                group.get(groupPosition).setChecked(isAllChildCbChecked(groupPosition));
                //3.全选的checkbox状态值
                ((ThirdActivity) context).setAllChecked(isAllGroupCbChecked());
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新界面
                notifyDataSetChanged();
            }
        });
        holder.addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取目前显示的值
                int num = listBean.getNum();
                num++;
                //改变JavaBean里的状态值
                listBean.setNum(num);
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新列表
                notifyDataSetChanged();
            }
        });
        holder.delimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取目前显示的值
                int num = listBean.getNum();
                if (num <= 1) {
                    num = 1;
                } else {
                    num--;
                }
                //改变JavaBean里的状态值
                listBean.setNum(num);
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新列表
                notifyDataSetChanged();
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其实就是删除集合
                List<GetCartsbean.DataBean.ListBean> listBeans = child.get(groupPosition);
                if (listBeans.size() > 0) {
                    listBeans.remove(childPosition);
                }
                if (listBeans.size() == 0) {
                    child.remove(groupPosition);
                    group.remove(groupPosition);
                }
                //计算钱和数量并显示
                setPriceAndCount();
                //改变全选状态
                ((ThirdActivity) context).setAllChecked(isAllGroupCbChecked());
                //刷新列表
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        CheckBox groupBox;
        TextView text;
    }
    class ChildViewHolder{
        CheckBox childbox;
        ImageView image;
        TextView title;
        TextView tvSubhead;
        TextView price;
        ImageView delimage;
        TextView count;
        ImageView addimage;
        Button button;
    }
    /**
     * 计算钱和数量
     */
    private PriceAndCount compute() {
        double price = 0;
        int count = 0;
        for (int i = 0; i < group.size(); i++) {
            List<GetCartsbean.DataBean.ListBean> listBeans = child.get(i);
            for (int j = 0; j < listBeans.size(); j++) {
                if (listBeans.get(j).isChecked()) {
                    price += listBeans.get(j).getPrice() * listBeans.get(j).getNum();
                    count += listBeans.get(j).getNum();
                }
            }
        }
        return new PriceAndCount(price, count);
    }
    /**
     * 判断二级列表checkbox状态
     *
     * @return
     */
    private boolean isAllChildCbChecked(int groupPosition) {
        List<GetCartsbean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            if (!listBeans.get(i).isChecked()) {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断一级列表checkbox状态
     *
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
    /**
     * 设置钱和数量
     */
    private void setPriceAndCount() {
        ((ThirdActivity) context).setPriceAndCount(compute());
    }
    /**
     * 设置一级列表对应的二级列表checkbox状态
     *
     * @param groupPosition
     * @param bool
     */
    private void setChildrenCb(int groupPosition, boolean bool) {
        List<GetCartsbean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            listBeans.get(i).setChecked(bool);
        }
    }
    /**
     * 全选或者全不选
     *
     * @param bool
     */
    public void AllOrNone(boolean bool) {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).setChecked(bool);
            setChildrenCb(i, bool);
        }
        setPriceAndCount();
        notifyDataSetChanged();
    }
}
