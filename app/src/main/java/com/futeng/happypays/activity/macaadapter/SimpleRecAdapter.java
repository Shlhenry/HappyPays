package com.futeng.happypays.activity.macaadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public abstract class SimpleRecAdapter<T, F extends RecyclerView.ViewHolder> extends RecyclerAdapter<T, F> {

    public SimpleRecAdapter(Context context) {
        super(context);
    }

    @Override
    public F onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return newViewHolder(view);
    }

    public abstract F newViewHolder(View itemView);

    public abstract int getLayoutId();

    /**
     * 设置数据源
     *
     * @param data
     */
    public void setData(T data) {
        this.data.clear();
        if (data != null) {
            if (this.data == null) {
                this.data = new ArrayList<T>();
            }
            this.data.add(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public void addElement(T element) {
        int preSize = this.data.size();
        super.addElement(element);
        notifyItemRangeChanged(preSize, 1);
    }

    @Override
    public void addElement(int position, T element) {
        int preSize = this.data.size();
        super.addElement(position, element);
        notifyItemRangeChanged(preSize, 1);
    }
}
