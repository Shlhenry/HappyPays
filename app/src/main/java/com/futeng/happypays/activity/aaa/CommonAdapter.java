package com.futeng.happypays.activity.aaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;


/**
 * 共的Adapter
 *
 * @author Only You
 * @date 2016年5月4日
 * @version 1.0
 * @param <T>
 *            泛型
 */
public abstract class CommonAdapter<T> extends BaseAdapter{
	protected LayoutInflater mInflater;

	protected Context mContext;

	protected List<T> mDatas;

	protected final int mItemLayoutId;

	private int mCurPosition = 0;

	public int getCurPosition() {
		return mCurPosition;
	}

	public void setCurPosition(int mCurPosition) {
		this.mCurPosition = mCurPosition;
	}

	/**
	 * @param context
	 *            上下文对象
	 * @param Data
	 *            数据源 为List类型
	 * @param itemLayoutId
	 *            子布局视图
	 */
	public CommonAdapter(Context context, List<T> Data, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = Data;
		this.mItemLayoutId = itemLayoutId;
	}


	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		convert(viewHolder, getItem(position), position);
		return viewHolder.getConvertView();
	}

	/**
	 * @param mHolder
	 *            视图模板
	 * @param item
	 *            子布局数据
	 * @param position
	 *            当前下标
	 */
	public abstract void convert(ViewHolder mHolder, T item, int position);

	private ViewHolder getViewHolder(int position, View convertView,
									 ViewGroup parent) {

		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position);
	}
}
