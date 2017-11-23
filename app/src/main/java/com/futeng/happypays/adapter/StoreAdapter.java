package com.futeng.happypays.adapter;

import android.content.Context;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.Comment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Keen on 11/13/2016.
 */

public class StoreAdapter extends CommonAdapter<Comment> {


    public StoreAdapter(Context context, int layoutId, List<Comment> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Comment s, int position) {

//        holder.setText(R.id.xxxtext, s.getContentText());


//        holder.setText(R.id.item_index_comment, s.getIndexComment());
//        holder.setText(R.id.item_action_love, s.getLoveCount());
//        holder.setText(R.id.user_name, s.getUserName());
    }
}
