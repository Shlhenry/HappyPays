package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.WaterBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
public class ChangeMoneyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<WaterBean.ListBean> mList;
    private Context mContext;

    public ChangeMoneyAdapter(Context context, List<WaterBean.ListBean> list){
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
            convertView = mInflater.inflate(R.layout.moneychange_item, null);

            holder.awtName = (TextView) convertView.findViewById(R.id.moneychange_awtName);
            holder.mccName = (TextView) convertView.findViewById(R.id.moneychange_mccName);
            holder.wamActMoney = (TextView) convertView.findViewById(R.id.moneychange_wamActMoney);
            holder.wamDate = (TextView) convertView.findViewById(R.id.moneychange_wamDate);
            holder.wamBeforeMoney = (TextView) convertView.findViewById(R.id.moneychange_wamBeforeMoney);
            holder.wamAfterMoney = (TextView) convertView.findViewById(R.id.moneychange_wamAfterMoney);

            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        holder.awtName.setText(mList.get(position).getAwtName());
        holder.mccName.setText(mList.get(position).getMccName());
        holder.wamActMoney.setText(mList.get(position).getWamActMoney()+"");
        holder.wamDate.setText(mList.get(position).getWamDate());
        holder.wamBeforeMoney.setText(mList.get(position).getWamBeforeMoney()+"");
        holder.wamAfterMoney.setText(mList.get(position).getWamAfterMoney()+"");

        return convertView;
    }
    private class viewholder{
        public TextView awtName;
        public TextView mccName;
        public TextView wamActMoney;
        public TextView wamDate;
        public TextView wamBeforeMoney;
        public TextView wamAfterMoney;
    }
}
