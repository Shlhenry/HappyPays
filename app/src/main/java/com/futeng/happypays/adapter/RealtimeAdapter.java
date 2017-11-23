package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.RealtimeBean;
import com.futeng.happypays.utils.ShopWaterBean;

/**
 * Created by Administrator on 2017/9/21.
 */
public class RealtimeAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<RealtimeBean.ListBean> mList;
    private Context mContext;

    public RealtimeAdapter(Context context, List<RealtimeBean.ListBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList == null || mList.size() == 0)
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder;
        if (convertView == null) {
            holder = new viewholder();
            convertView = mInflater.inflate(R.layout.realtime_item, null);
            holder.country_img = (ImageView) convertView.findViewById(R.id.country_img);
            holder.country_name = (TextView) convertView.findViewById(R.id.country_name);
            holder.country_exchange = (TextView) convertView.findViewById(R.id.country_exchange);
            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }

        Glide.with(convertView.getContext())
                .load(mList.get(position).getCurImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.flagtongyong).into(holder.country_img);

        holder.country_name.setText(mList.get(position).getCurName());
        holder.country_exchange.setText(mList.get(position).getExcValue()+" "+mList.get(position).getCurCode()+" =100 CNY");

        return convertView;
    }

    private class viewholder {
        private ImageView country_img;
        private TextView country_name;
        private TextView country_exchange;
    }
}