package com.futeng.happypays.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import com.futeng.happypays.R;
import com.futeng.happypays.utils.AllCommentBean;
import com.futeng.happypays.utils.CommendBean;

/**
 * Created by Administrator on 2017/9/14.
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mInflater;
    private List<CommendBean.ListBean> mList;
    private Context mContext;

    public MyExpandableListViewAdapter(Context context, List<CommendBean.ListBean> List){
        mContext = context;
        mList = List;

        mInflater = LayoutInflater.from(context);
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }
    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.size() == 0 ? 0 : mList.size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int groupPosition) {
        if (mList == null || mList.size() == 0)
            return null;
        return mList.get(groupPosition);
    }
    //  获得某个父项的某个子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mList == null || mList.size() == 0)
            return null;
        return mList.get(groupPosition);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder holder = null;

        if(view == null){
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.parent_item, null);
            holder.groupnum = (TextView)view.findViewById(R.id.parent_title);
            holder.arrow = (ImageView)view.findViewById(R.id.arrow);
            view.setTag(holder);
        }else{
            holder = (GroupHolder)view.getTag();
        }

        //判断是否已经打开列表
        if(isExpanded){
            holder.arrow.setBackgroundResource(R.mipmap.xialajiantou);
        }else{
            holder.arrow.setBackgroundResource(R.mipmap.jiantou);
        }


        holder.groupnum.setText(mList.size()+"");

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;

        if(view == null){
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.child_item, null);
            holder.comment_amname = (TextView)view.findViewById(R.id.comment_amname);
            holder.comment_id = (TextView)view.findViewById(R.id.comment_id);
            holder.comment_dengji = (TextView)view.findViewById(R.id.comment_dengji);
            view.setTag(holder);
        }else{
            holder = (ChildHolder)view.getTag();
        }

        holder.comment_amname.setText(mList.get(groupPosition).getAmName());
        holder.comment_id.setText(mList.get(groupPosition).getAmPersonPhone());
        holder.comment_dengji.setText(mList.get(groupPosition).getGradeName());


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class GroupHolder{
        public TextView groupnum;
        public ImageView arrow;
    }

    class ChildHolder{
        public TextView comment_amname;
        public TextView comment_id;
        public TextView comment_dengji;
    }


}
