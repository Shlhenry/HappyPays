package com.futeng.happypays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.futeng.happypays.R;
import com.futeng.happypays.utils.CommendBean;
import com.futeng.happypays.utils.FailCommentBean;

/**
 * Created by Administrator on 2017/9/15.
 */
public class NoCommentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<FailCommentBean.ListBean> mList;
    private Context mContext;

    public NoCommentAdapter(Context context, List<FailCommentBean.ListBean> List) {
        mContext = context;
        mList = List;
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
            convertView = mInflater.inflate(R.layout.anotherchild_item, null);

            holder.nopass_account = (TextView) convertView.findViewById(R.id.nopass_account);

            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }
        holder.nopass_account.setText(mList.get(position).getPPhone());
        return convertView;
    }

    public class viewholder {
        public TextView nopass_account;
    }
}
