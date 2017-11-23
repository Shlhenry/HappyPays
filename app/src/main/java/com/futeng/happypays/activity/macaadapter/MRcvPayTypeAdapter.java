package com.futeng.happypays.activity.macaadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import butterknife.InjectView;
import butterknife.ButterKnife;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.kit.TransactionResult;

/**
 * author: MaCa
 * time  : 2016/11/10 14:38
 * note  : TODO
 */
public class MRcvPayTypeAdapter extends SimpleRecAdapter<TransactionResult.MapBean.PayTypeBean, MRcvPayTypeAdapter.ViewHolder> {


    public MRcvPayTypeAdapter(Context context) {
        super(context);
    }


    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.rcv_paytype_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        45对应---微信T1 ;     46对应---微信D0 ;   47对应---支付宝T1 ;  48对应---支付宝D0  ;
//        81对应---2W无卡支付T1;     82对应---无卡快捷D0 ;
//        83对应---无卡快捷T1 ;        84对应---5W无卡支付T1
        switch (data.get(position).getMcc()) {
            case 45:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallwechat);
                break;
            case 46:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallwechat);
                break;
            case 47:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallalipay);
                break;
            case 48:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallalipay);
                break;
            case 81:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallnocard);
                break;
            case 82:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallnocard);
                break;
            case 83:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallunionpay);
                break;
            case 84:
                holder.rcvPaytypeItemIcon.setImageResource(R.mipmap.smallunionpay);
                break;
        }
        holder.rcvPaytypeItemName.setText(data.get(position).getMccName()+"");
        holder.rcvPaytypeItemMoney.setText(data.get(position).getMoney()+"");
        holder.rcvPaytypeItemNum.setText(((int)data.get(position).getCount())+"");
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.rcv_paytype_item_icon)
        ImageView rcvPaytypeItemIcon;
        @InjectView(R.id.rcv_paytype_item_name)
        TextView rcvPaytypeItemName;
        @InjectView(R.id.rcv_paytype_item_num)
        TextView rcvPaytypeItemNum;
        @InjectView(R.id.rcv_paytype_item_money)
        TextView rcvPaytypeItemMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}