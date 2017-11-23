package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.futeng.happypays.R;
import com.futeng.happypays.utils.BankBean;
import com.futeng.happypays.utils.CityBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class ListviewBankAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<BankBean.ListBean> mList;
    private Context mContext;

    public ListviewBankAdapter(Context context, List<BankBean.ListBean> list){
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
            convertView = mInflater.inflate(R.layout.renzheng_listview_item, null);
            holder.bank_name = (TextView) convertView.findViewById(R.id.item_city);
            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        holder.bank_name.setText(mList.get(position).getBankSimpleName());
        return convertView;
    }
    private class viewholder{
        TextView bank_name;
    }
}
