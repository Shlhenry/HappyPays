package com.futeng.happypays.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.futeng.happypays.R;
import com.futeng.happypays.tools.DensityUtil;
import com.futeng.happypays.utils.ShopWaterBean;

import java.util.List;

public class WaterAdapter extends RecyclerView.Adapter<WaterAdapter.SimpleAdapterViewHolder> {
    private List<ShopWaterBean> list;
    private int largeCardHeight, smallCardHeight;

    public WaterAdapter(List<ShopWaterBean> list, Context context) {
        this.list = list;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 100);
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position) {
        ShopWaterBean shopWaterBean = list.get(position);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            holder.rootView.getLayoutParams().height = position % 2 != 0 ? largeCardHeight : smallCardHeight;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<ShopWaterBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.water_listview_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    public void insert(ShopWaterBean shopWaterBean, int position) {
        list.add(position, shopWaterBean);
        notifyItemInserted(position);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public TextView typewater;
        public TextView cardwater;
        public TextView timewater;
        public TextView moneywater;
        public TextView tipwater;
        public int position;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {

                typewater = (TextView) itemView
                       .findViewById(R.id.water_type_list);
                cardwater = (TextView) itemView
                        .findViewById(R.id.water_card_list);
                timewater = (TextView) itemView
                        .findViewById(R.id.water_time_list);
                moneywater = (TextView) itemView
                        .findViewById(R.id.water_money_list);
                tipwater = (TextView) itemView
                        .findViewById(R.id.water_tip_list);


            }

        }
    }

    public ShopWaterBean getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}