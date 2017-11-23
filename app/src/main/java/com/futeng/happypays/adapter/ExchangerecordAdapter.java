package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.ChangerecordBean;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ExchangerecordAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ChangerecordBean.ListBean>mList;
    private Context mContext;

    public ExchangerecordAdapter(Context context, List<ChangerecordBean.ListBean> list){
        mContext=context;
        mList=list;
        mInflater=LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.item_getstore, null);

            holder.getstore_item_comname = (TextView) convertView.findViewById(R.id.getstore_item_comname);
            holder.getstore_item_exrccount = (TextView) convertView.findViewById(R.id.getstore_item_exrccount);
            holder.getstore_item_oldintegral = (TextView) convertView.findViewById(R.id.getstore_item_oldintegral);
            holder.getstore_item_exrcOrderNo = (TextView) convertView.findViewById(R.id.getstore_item_exrcOrderNo);
            holder.getstore_item_exrctime = (TextView) convertView.findViewById(R.id.getstore_item_exrctime);
            holder.getstore_item_paoState = (TextView) convertView.findViewById(R.id.getstore_item_paoState);

            holder.getstore_item_img= (ImageView) convertView.findViewById(R.id.getstore_item_img);
            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        holder.getstore_item_comname.setText(mList.get(position).getComName());
        holder.getstore_item_exrccount.setText(mList.get(position).getExrcCount()+"");
        holder.getstore_item_oldintegral.setText(mList.get(position).getOldIntegral()+"");
        holder.getstore_item_exrcOrderNo.setText(mList.get(position).getExrcOrderNo()+"");
        holder.getstore_item_exrctime.setText(mList.get(position).getExrcTime()+"");
        holder.getstore_item_paoState.setText(mList.get(position).getPaoState());

        Glide.with(convertView.getContext())
                .load(mList.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.redbull).into(holder.getstore_item_img);

        return convertView;
    }

    private class viewholder{
        public TextView getstore_item_comname;
        public TextView getstore_item_exrccount;
        public TextView getstore_item_oldintegral;
        public TextView getstore_item_exrcOrderNo;
        public TextView getstore_item_exrctime;
        public TextView getstore_item_paoState;
        public ImageView getstore_item_img;
    }


}
