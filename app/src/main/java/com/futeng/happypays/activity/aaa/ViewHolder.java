package com.futeng.happypays.activity.aaa;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;

public class ViewHolder {
	private final SparseArray<View> mViews;

	private int mPosition;

	private View mConvertView;
	private onviewholderclick onclick;

	private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
		mPosition = position;
		mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}
	public static ViewHolder get(Context context, View convertView,
								 ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		}
		return (ViewHolder) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 设置文本信息
	 *
	 * @param viewId
	 *            控件的id
	 * @param text
	 *            文本值
	 * @return
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text + "");
		return this;
	}
	public CheckBox setViewCheckBox(int viewId){
		CheckBox v=getView(viewId);
		return  v;
	}
	public ImageView setImageView(int viewId){
		ImageView v=getView(viewId);
		return v;
	}
	public ViewHolder setTextPaint(int viewId, int flag) {
		TextView view = getView(viewId);
		view.getPaint().setFlags(flag);
		return this;
	}

	public ViewHolder setTextSize(int viewId, int size) {
		TextView view = getView(viewId);
		view.setTextSize(size);
		return this;
	}


	/**
	 * 设置checkbox checkable
	 *
	 * @param viewId
	 * @return
	 */
	public ViewHolder setcheckable(int viewId, final boolean b) {
		CheckBox view = getView(viewId);
		view.setChecked(b);
		return this;
	}

	public ViewHolder setonClick(int viewId, final boolean b) {
		final CheckBox view = getView(viewId);
		view.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					view.setChecked(!b);
				} else {
					view.setChecked(b);
				}
			}
		});
		return this;
	}
	/**订花效果*/
	public  ViewHolder setViewAnimationSet(int viewId,Context mContext){
		/*View view=getView(viewId);
		Animation animation= AnimationUtils.loadAnimation(mContext, R.anim.shop_entershop_recyitem);
		view.startAnimation(animation);*/
		return this;
	}
	public ViewHolder SetVisibility(int viewId, int visibility) {
		View view = getView(viewId);
		view.setVisibility(visibility);
		return this;
	}

	/** 背景 图片 */
	public ViewHolder SetBackground(int viewId, int Background) {
		View view = getView(viewId);
		view.setBackgroundResource(Background);
		return this;

	}

	/** 背景颜色 */
	public ViewHolder SetBackgroundColor(int viewId, int Background) {
		View view = getView(viewId);
		view.setBackgroundColor(Background);
		return this;

	}

	/** 设置TextView中间的线 */
	public ViewHolder getPaint(int viewId) {
		View view = getView(viewId);
		((TextView) view).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return this;

	}

	/** 获取手机屏幕的高和宽 */
	public ViewHolder SetLinearLayoutwidth(int viewid, Context context) {
		View view = getView(viewid);
		WindowManager wm = ((Activity) context).getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view
				.getLayoutParams(); // 取控件textView当前的布局参数
		linearParams.width = width / 3;// 控件的高强制
		view.setLayoutParams(linearParams);
		return this;
	}

	/**
	 * 设置点击事件
	 *
	 * @author Starspace-VR
	 *
	 */

	public ViewHolder setImageonClick(int viewId, onviewholderclick onClick) {
		onclick = onClick;
		ImageView view = getView(viewId);
		view.setClickable(true);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onclick.setonClick(v);
			}
		});
		return this;
	}

	public interface onviewholderclick {
		void setonClick(View v);
	};

	ontextviewonclick onClic;

	public ViewHolder setTextonClick(int viewId, ontextviewonclick onClick) {
		onClic = onClick;
		TextView view = getView(viewId);
		view.setClickable(true);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onClic.setontexClick(v);
			}
		});
		return this;
	}

	public ImageView setImagViewId(int viewId){
		ImageView vi=getView(viewId);
		return vi;
	}
	public interface ontextviewonclick {
		void setontexClick(View v);
	};

	public ViewHolder setSelected(int viewId, boolean b) {
		View view = getView(viewId);
		view.setSelected(b);
		return this;
	}

	public ViewHolder setChecked(int viewId, boolean b) {
		CheckBox view = getView(viewId);
		view.setChecked(b);
		return this;
	}

	/*public ViewHolder setTextHtml(int viewId, String text) {
		TextView view = getView(viewId);
		String source = SUtils.htmlEscapeCharsToString(text);
		view.setText(Html.fromHtml(source));
		return this;
	}*/

	public ViewHolder setRatingBarRating(int viewId, int b) {
		RatingBar view = getView(viewId);
		view.setRating(b);
		return this;

	}

	public ViewHolder setListViewAdapter(int viewId, CommonAdapter<T> adapter) {
		ListView view = getView(viewId);
		view.setAdapter(adapter);
		return this;
	}

	public ViewHolder setGridViewAdapter(int viewId, BaseAdapter adapter) {
		GridView view = getView(viewId);
		view.setAdapter(adapter);
		return this;
	}

	public ViewHolder setGridViewOnItem(int viewId,
										OnItemClickListener onItemClickListener) {
		GridView view = getView(viewId);
		view.setOnItemClickListener(onItemClickListener);
		return this;
	}

	public ViewHolder setonClick(int viewId,
								 OnClickListener onClickListener) {
		View view = getView(viewId);
		view.setOnClickListener(onClickListener);
		return this;

	}

	/**
	 * 设置部分文字颜色
	 *
	 * @param viewId
	 *            控件id
	 * @param text
	 *            文本值
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public ViewHolder setTextandColor(int viewId, String text, int resId,
									  int start, int end) {
		TextView view = getView(viewId);
		SpannableStringBuilder style = new SpannableStringBuilder(text);
		style.setSpan(new ForegroundColorSpan(resId), start, end,
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		view.setText(style);
		return this;
	}

	/**
	 * 设置控件的文本颜色
	 *
	 * @param viewId
	 *            控件id
	 * @param color
	 *            颜色id
	 * @return
	 */
	public ViewHolder setTextColor(int viewId, int color) {
		TextView view = getView(viewId);
		view.setTextColor(color);
		return this;
	}

	/**
	 * 设置控件的文本颜色
	 *
	 * @param viewId
	 *            控件id
	 * @param text
	 *            文本值
	 * @param color
	 *            颜色id
	 * @return
	 */
	public ViewHolder setTextColor(int viewId, String text, int color) {
		TextView view = getView(viewId);
		view.setText(text);
		view.setTextColor(color);
		return this;
	}

	/**
	 * 设置某个控件的状态，显示或者隐藏
	 *
	 * @param viewId
	 *            控件id
	 * @param Visibility
	 *            显示状态
	 * @return
	 */
	public ViewHolder setVisibility(int viewId, int Visibility) {
		View view = getView(viewId);
		view.setVisibility(Visibility);
		return this;
	}

	/**
	 * 根据图片id给ImageView控件设置图片
	 *
	 * @param viewId
	 *            控件id
	 * @param drawableId
	 *            图片id
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);
		return this;
	}

	public ViewHolder setBackgroundResource(int viewId, int drawableId) {
		View view = getView(viewId);
		view.setBackgroundResource(drawableId);
		return this;
	}

	public ViewHolder setColorBackground(int viewId, int drawableId) {
		View view = getView(viewId);
		view.setBackgroundColor(drawableId);
		return this;

	}

	private ViewHolder setTextSelected(int viewId, boolean boo) {
		View view = getView(viewId);
		view.setSelected(boo);
		return this;

	}

	/**
	 * 根据图片id给ImageView控件设置图片
	 *
	 * @param viewId
	 *            控件id
	 * @param bm
	 *            bitmap
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}

	/**
	 * 加载网络图片的方法
	 *
	 * @param viewId
	 *            ImageView控件的id
	 * @param url
	 *            图片地址
	 * @param context
	 *            上下文对象
	 *
	 *
	 *            这里面可以自己加方法
	 * @return
	 */
	public ViewHolder setImageByUrl(ImageView viewId, String url,
									Context context) {
		Glide.with(context)
				.load(url)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.dontAnimate().placeholder
				(R.mipmap.flagtongyong).into((ImageView) viewId);

		// Picasso.with(context).load(url).placeholder(R.drawable.nopic)
		// .error(R.drawable.nopic).into((ImageView) getView(viewId));
		return this;
	}

	/**
	 * 加载网络图片的方法
	 *
	 *            ImageView控件的id
	 * @param url
	 *            图片地址
	 *            图片弧度
	 *            上下文对象
	 * @return
	 */
	public ViewHolder setImageByUrlRoundaa(ImageView Image, String url) {
//		x.image().bind(Image, url);
		// Picasso.with(context).load(url).placeholder(R.drawable.ic_launcher)
		// .error(R.drawable.ic_launcher)
		// .transform(new RoundTransformation(radiaus))
		// .into((ImageView) getView(viewId));
		return this;
	}

	public ViewHolder setImageByUrlRound(int viewId, String url, Context context) {

		// Picasso.with(context).load(url).placeholder(R.drawable.none)
		// .error(R.drawable.none).into((ImageView) getView(viewId));
		return this;
	}

	/**
	 * 加载固定大小的网络图片
	 *
	 * @param viewId
	 *            ImageView控件的id
	 * @param url
	 *            图片地址
	 * @param targetWidth
	 *            图片的宽度
	 * @param targetHeight
	 *            图片的高度
	 * @param context
	 *            上下文对象
	 * @return
	 */
	public ViewHolder setImageByUrl(final int viewId, String url,
									int targetWidth, int targetHeight, Context context) {
		// Picasso.with(context).load(url).placeholder(R.drawable.ic_launcher)
		// .error(R.drawable.ic_launcher)
		// .resize(targetWidth, targetHeight).centerCrop()
		// .into((ImageView) getView(viewId));
		return this;
	}

	/**
	 * 加载固定大小的网络图片
	 *
	 * @param viewId
	 *            ImageView控件的id
	 * @param url
	 *            图片地址
	 * @param targetWidth
	 *            图片的宽度
	 * @param targetHeight
	 *            图片的高度
	 * @param radiaus
	 *            圆角弧度
	 * @param context
	 *            上下文对象
	 * @return
	 */
	public ViewHolder setImageByUrlRound(final int viewId, String url,
										 int targetWidth, int targetHeight, int radiaus, Context context) {
		/*Picasso.with(context).load(url).placeholder(R.drawable.ic_launcher)
				.error(R.drawable.ic_launcher).resize(60, 30).centerCrop()
				.transform(new RoundTransformation(radiaus))
				.into((ImageView) getView(viewId));*/
		return this;
	}

	public ViewHolder setImageByUrlRounda(final int viewId, String url,
										  int targetWidth, int targetHeight, int radiaus, Context context) {
	/*	Picasso.with(context).load(url).placeholder(R.drawable.ic_launcher)
				.error(R.drawable.ic_launcher)
				.resize(targetWidth, targetHeight).centerCrop()
				.transform(new RoundTransformation(radiaus))
				.into((ImageView) getView(viewId));*/
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

	public void setImageByUrlRound(View findViewById, String string, int i,
								   FragmentActivity activity) {
		// TODO Auto-generated method stub

	}
}