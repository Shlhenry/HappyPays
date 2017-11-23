package com.futeng.happypays.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.utils.CardBean;


/**
 * Created by Administrator on 2017/5/16.
 */
public class CardbangdingAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<CardBean.ListBean> mList;
    private Context mContext;

    public CardbangdingAdapter(Context context, List<CardBean.ListBean> list){
        mContext = context;
        mList = list;
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

        if (convertView==null){
            holder=new viewholder();
            convertView=mInflater.inflate(R.layout.bangka_item,null);

            holder.cardid= (TextView) convertView.findViewById(R.id.card_id);
            holder.cardname= (TextView) convertView.findViewById(R.id.card_name);
            holder.cardbank= (TextView) convertView.findViewById(R.id.card_bankname);
            holder.cardbackground= (LinearLayout) convertView.findViewById(R.id.cardbackground);

            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        //卡号*号隐藏
        String cardid=mList.get(position).getAccountNumber();
        String card=cardid.substring(4,cardid.length()-4);
        String cardnumber=cardid.replace(card," **** **** ");

        holder.cardid.setText(cardnumber);
        holder.cardname.setText(mList.get(position).getAccountName());
        holder.cardbank.setText(mList.get(position).getAbcBank());

        //不同银行选择对应背景图片
        if (mList.get(position).getAbcBank().indexOf("农业")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankabc));
        }else if(mList.get(position).getAbcBank().indexOf("工商")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankgongshang));
        }else if(mList.get(position).getAbcBank().indexOf("光大")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankguangda));
        }else if(mList.get(position).getAbcBank().indexOf("华夏")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankhuaxia));
        }else if(mList.get(position).getAbcBank().indexOf("建设")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankjianshe));
        }else if(mList.get(position).getAbcBank().indexOf("交通")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankjiaotong));
        }else if(mList.get(position).getAbcBank().indexOf("平安")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankpingan));
        }else if(mList.get(position).getAbcBank().indexOf("浦东")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankpudong));
        }else if(mList.get(position).getAbcBank().indexOf("兴业")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankxingye));
        }else if(mList.get(position).getAbcBank().indexOf("邮政")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankyouzheng));
        }else if(mList.get(position).getAbcBank().indexOf("招商")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankzhangshang));
        }else if(mList.get(position).getAbcBank().indexOf("中国")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankchina));
        }else if(mList.get(position).getAbcBank().indexOf("中信")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankzhongxin));
        }else if(mList.get(position).getAbcBank().indexOf("北京")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.beijingbank));
        }else if(mList.get(position).getAbcBank().indexOf("民生")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankmingsheng));
        }else if(mList.get(position).getAbcBank().indexOf("南京")!=-1){
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.banknanjing));
        }else{
            holder.cardbackground.setBackground(convertView.getResources().getDrawable(R.mipmap.bankall));
        }
        return convertView;
    }

    public class viewholder{
        public TextView cardid;
        public TextView cardname;
        public TextView cardbank;
        public LinearLayout cardbackground;
    }




}
