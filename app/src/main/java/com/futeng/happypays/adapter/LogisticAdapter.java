package com.futeng.happypays.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.futeng.happypays.R;
import com.futeng.happypays.utils.LogicticBean;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LogisticAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<LogicticBean.ResultBeanX.ResultBean.ListBean>mList;

    public LogisticAdapter(Context context,List<LogicticBean.ResultBeanX.ResultBean.ListBean>list){
        mContext=context;
        mList=list;
        mInflater= LayoutInflater.from(context);
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
        viewholder   holder = new viewholder();
            convertView = mInflater.inflate(R.layout.item_logistic, null);
            holder.logistic_time = (TextView) convertView.findViewById(R.id.logistic_time);
            holder.logistic_status = (TextView) convertView.findViewById(R.id.logistic_status);
            holder.logistic_up = (TextView) convertView.findViewById(R.id.logistic_up);
            holder.logistic_img = (TextView) convertView.findViewById(R.id.logistic_img);
            holder.logistic_down = (TextView) convertView.findViewById(R.id.logistic_down);


        holder.logistic_time.setText(mList.get(position).getTime());
        holder.logistic_status.setText(mList.get(position).getStatus());

        if (position==0){
            holder.logistic_up.setBackgroundColor(Color.WHITE);
            holder.logistic_img.setBackgroundResource(R.drawable.yuanorange);
        }
        if (mList.size()-1==position){
            holder.logistic_down.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }

    private class viewholder{
        public TextView logistic_time;
        public TextView logistic_status;
        public TextView logistic_up;
        public TextView logistic_img;
        public TextView logistic_down;
    }
}
