package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.futeng.happypays.R;
import com.futeng.happypays.utils.ShopWaterBean;
import com.futeng.happypays.utils.SnBean;

/**
 * Created by Administrator on 2017/5/16.
 */
public class SnbangdingAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<SnBean.ListBean> mList;
    private Context mContext;


    public SnbangdingAdapter(Context context, List<SnBean.ListBean> list){
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

        if (convertView==null){
            holder=new viewholder();
            convertView=mInflater.inflate(R.layout.activity_snitem,null);

            holder.macSn= (TextView) convertView.findViewById(R.id.sn_id);

            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }

        holder.macSn.setText(mList.get(position).getMacSn());

        return convertView;
    }

    public class viewholder{
        public TextView macSn;
    }

}
