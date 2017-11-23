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

/**
 * Created by Administrator on 2017/6/20.
 */
public class CommentAdapter<C> extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<CommendBean.ListBean> mList;
    private Context mContext;

    public CommentAdapter(Context context, List<CommendBean.ListBean> List) {
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
            convertView = mInflater.inflate(R.layout.child_item, null);

            holder.comment_amname = (TextView) convertView.findViewById(R.id.comment_amname);
            holder.comment_id = (TextView) convertView.findViewById(R.id.comment_id);
            holder.comment_dengji = (TextView) convertView.findViewById(R.id.comment_dengji);

            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }
        holder.comment_amname.setText(mList.get(position).getAmName());
        holder.comment_id.setText(mList.get(position).getAmPersonPhone());
        holder.comment_dengji.setText(mList.get(position).getGradeName());
        return convertView;
    }

    public class viewholder {
        public TextView comment_amname;
        public TextView comment_id;
        public TextView comment_dengji;

    }
}