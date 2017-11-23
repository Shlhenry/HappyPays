package com.futeng.happypays.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.activity.erweimashoukuan.StoreActivity;
import com.futeng.happypays.tools.DensityUtil;
import com.futeng.happypays.utils.StoreBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import static java.lang.System.load;

public class SimpleAdapter extends BaseRecyclerAdapter<SimpleAdapter.SimpleAdapterViewHolder> {
    private List<StoreBean.ListBean> list;
    private int largeCardHeight, smallCardHeight;
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public SimpleAdapter(List<StoreBean.ListBean> list, Context context) {
        this.list = list;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 100);
        this.context=context;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        StoreBean.ListBean person = list.get(position);
        holder.comName.setText(person.getComName());
        Log.e("---",person.getComExctype()+"");
        if ("2".equals(person.getComExctype()+"")){
            holder.comMoneyIntegral.setText(person.getComIntegral()+"");
            Log.e("--------","-------");
        }else{
            holder.comMoneyIntegral.setText(person.getComMoney()+"");
            Log.e("+++++++","+++++++");
        }
        holder.comCount.setText(person.getComCount()+"");
        holder.exchangeCount.setText(person.getExchangeCount()+"");

        Glide.with(context)
                .load(person.getComImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                        (R.mipmap.redbull).into(holder.comImgUrl);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);

        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    public void setData(List<StoreBean.ListBean> list) {
        this.list = list;
//        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    //注意这里使用getTag方法获取position
                    mOnItemClickListener.onItemClick(view,(int)view.getTag());
                }
            }
        });
        return vh;
    }

    public void insert(StoreBean person, int position) {
        insert(list, person.getList().get(position), position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView comName;
        public TextView comMoneyIntegral;
        public TextView comCount;
        public TextView exchangeCount;
        public ImageView comImgUrl;

        public int position;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                comName = (TextView) itemView.findViewById(R.id.comName);
                comMoneyIntegral = (TextView) itemView.findViewById(R.id.comMoneyIntegral);
                comCount = (TextView) itemView.findViewById(R.id.comCount);
                exchangeCount = (TextView) itemView.findViewById(R.id.exchangeCount);
                comImgUrl = (ImageView) itemView.findViewById(R.id.comImgUrl);
            }

        }
    }

    public StoreBean.ListBean getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

}