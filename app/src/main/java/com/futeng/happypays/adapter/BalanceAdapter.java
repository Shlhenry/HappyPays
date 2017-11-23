package com.futeng.happypays.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.BalanceBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class BalanceAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<BalanceBean.ListBean> mList;
    private Context mContext;

    public BalanceAdapter(Context context, List<BalanceBean.ListBean> list){
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
            convertView = mInflater.inflate(R.layout.balance_list_item, null);

            holder.exaActmoney = (TextView) convertView.findViewById(R.id.balance_money);
            holder.exaMarkName = (TextView) convertView.findViewById(R.id.balance_type);
            holder.exaTimeTwo = (TextView) convertView.findViewById(R.id.balance_time);

            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }

        holder.exaActmoney.setText(mList.get(position).getExaActmoney()+"");
        holder.exaMarkName.setText(mList.get(position).getExaMarkName()+"");
        holder.exaTimeTwo.setText(mList.get(position).getExaTimeTwo()+"");

        return convertView;
    }
    private class viewholder{
        public TextView exaActmoney;
        public TextView exaMarkName;
        public TextView exaTimeTwo;
    }
}
