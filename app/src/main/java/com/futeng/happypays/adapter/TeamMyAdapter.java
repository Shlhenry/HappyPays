package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.futeng.happypays.R;
import com.futeng.happypays.utils.TeamMyBean;

/**
 * Created by Administrator on 2017/6/13.
 */
public class TeamMyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<TeamMyBean.ListBean>mList;
    private Context mContext;

    public TeamMyAdapter(Context context,List<TeamMyBean.ListBean>List){
            mContext=context;
            mList=List;
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
        if (convertView==null){
            holder=new viewholder();
            convertView=mInflater.inflate(R.layout.activity_myteamfirst_item,null);

            holder.mccName= (TextView) convertView.findViewById(R.id.mccName);
            holder.mccRate= (TextView) convertView.findViewById(R.id.mccRate);

            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        holder.mccName.setText(mList.get(position).getMccName());
        holder.mccRate.setText(mList.get(position).getMccRate()+"");
        return convertView;
    }
    public class viewholder{
        public TextView mccName;
        public TextView mccRate;
    }
}
