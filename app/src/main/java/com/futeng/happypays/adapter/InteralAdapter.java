package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.WaterBean;

/**
 * Created by Administrator on 2017/7/19.
 */
public class InteralAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<WaterBean.ListBean> mList;
    private Context mContext;

    public InteralAdapter(Context context, List<WaterBean.ListBean> list){
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
            convertView = mInflater.inflate(R.layout.interal_item, null);

            holder.awtName = (TextView) convertView.findViewById(R.id.moneychange_awtName);
            holder.mccName = (TextView) convertView.findViewById(R.id.moneychange_mccName);
            holder.wamActMoney = (TextView) convertView.findViewById(R.id.moneychange_wamActMoney);
            holder.wamDate = (TextView) convertView.findViewById(R.id.moneychange_wamDate);
            holder.wamBeforeMoney = (TextView) convertView.findViewById(R.id.moneychange_wamBeforeMoney);
            holder.wamAfterMoney = (TextView) convertView.findViewById(R.id.moneychange_wamAfterMoney);

            holder.item_change=(TextView) convertView.findViewById(R.id.item_change);
            holder.item_beforechange=(TextView) convertView.findViewById(R.id.item_beforechange);
            holder.item_afterchange=(TextView) convertView.findViewById(R.id.item_afterchange);


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
        if ("红包".equals(mList.get(position).getAwtName())||"分润".equals(mList.get(position).getAwtName())){
            holder.item_change.setText("变动金额");
            holder.item_beforechange.setText("变动前金额");
            holder.item_afterchange.setText("变动后金额");
        }

        return convertView;
    }
    private class viewholder{
        public TextView awtName;
        public TextView mccName;
        public TextView wamActMoney;
        public TextView wamDate;
        public TextView wamBeforeMoney;
        public TextView wamAfterMoney;
        public TextView item_change;
        public TextView item_beforechange;
        public TextView item_afterchange;
    }
}
