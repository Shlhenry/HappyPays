package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.ShopWaterBean;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
public class ShopwaterAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ShopWaterBean.ListBean> mList;
    private Context mContext;

    public ShopwaterAdapter(Context context, List<ShopWaterBean.ListBean> list){
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
            convertView = mInflater.inflate(R.layout.water_list_item, null);

            holder.typewater = (TextView) convertView.findViewById(R.id.water_type_list);
//            holder.cardwater = (TextView) convertView.findViewById(R.id.water_card_list);
            holder.timewater = (TextView) convertView.findViewById(R.id.water_time_list);
            holder.moneywater = (TextView) convertView.findViewById(R.id.water_money_list);
            holder.tipwater = (TextView) convertView.findViewById(R.id.water_tip_list);
            holder.waterimg= (ImageView) convertView.findViewById(R.id.water_img);
            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        holder.typewater.setText(mList.get(position).getMccName());
//        holder.cardwater.setText(mList.get(position).getTran2());
        holder.timewater.setText(mList.get(position).getTranTime());
        holder.moneywater.setText(mList.get(position).getTranMoney()+"");
        holder.tipwater.setText(mList.get(position).getTranFee()+"");
        if (mList.get(position).getMccName().indexOf("无卡支付")!=-1){
            holder.waterimg.setImageDrawable(convertView.getResources().getDrawable(R.mipmap.smallunionpay));
        }else if (mList.get(position).getMccName().indexOf("微信")!=-1){
            holder.waterimg.setImageDrawable(convertView.getResources().getDrawable(R.mipmap.smallwechat));
        }else if (mList.get(position).getMccName().indexOf("无卡快捷")!=-1){
            holder.waterimg.setImageDrawable(convertView.getResources().getDrawable(R.mipmap.smallnocard));
        }
        return convertView;
    }
    private class viewholder{
        public TextView typewater;
//        public TextView cardwater;
        public TextView timewater;
        public TextView moneywater;
        public TextView tipwater;
        public ImageView waterimg;
    }
}
