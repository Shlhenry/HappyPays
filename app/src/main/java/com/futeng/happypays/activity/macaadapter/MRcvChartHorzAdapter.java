package com.futeng.happypays.activity.macaadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



import butterknife.InjectView;
import butterknife.ButterKnife;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.kit.MTools;
import com.futeng.happypays.activity.kit.TransactionResult;

/**
 * author: MaCa
 * time  : 2016/11/10 14:38
 * note  : TODO
 */
public class MRcvChartHorzAdapter extends SimpleRecAdapter<TransactionResult.ListBean, MRcvChartHorzAdapter.ViewHolder> {

    private double maxValue = 0;
    private int type = 0;//0为金额 1为数量
    private String thisTime = "";

    public MRcvChartHorzAdapter(Context context, double maxValue, int type) {
        super(context);
        this.maxValue = maxValue;
        this.type = type;
    }


    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.rcv_transactionchart_item_layout;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        double weight = 1;
        if (thisTime.equals(data.get(position).getTranTime()))
            holder.rcvTransactionchartItemChart.setBackgroundColor(MTools.getColor(context, R.color.colorf09617));
        else
            holder.rcvTransactionchartItemChart.setBackgroundColor(MTools.getColor(context, R.color.home));
        if (type == 0) {
            //当金额小于1000直接加载金额 ,大于1000就除以10000拼接万
            if (data.get(position).getMoney()<1000){
                holder.rcvTransactionchartItemNum.setText(data.get(position).getMoney()+"");
            }else{
                holder.rcvTransactionchartItemNum.setText(MTools.getDoubleTwo(data.get(position).getMoney() / 10000) + "万");
            }
            weight = data.get(position).getMoney() / maxValue;
        } else {
            holder.rcvTransactionchartItemNum.setText(data.get(position).getCount() + "");
            weight = data.get(position).getCount() / maxValue;
        }
        holder.rcvTransactionchartItemDate.setText(data.get(position).getTranTime());
        MTools.setViewParams(context, holder.rcvTransactionchartItemChart, MTools.getPx(context, R.dimen.x35), (int) (MTools.getPx(context, R.dimen.y385) * weight) == 0 ? 1 : (int) (MTools.getPx(context, R.dimen.y385) * weight));
    }

    public void setThisTime(String thisTime) {
        this.thisTime = thisTime;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.rcv_transactionchart_item_num)
        TextView rcvTransactionchartItemNum;
        @InjectView(R.id.rcv_transactionchart_item_chart)
        View rcvTransactionchartItemChart;
        @InjectView(R.id.rcv_transactionchart_item_date)
        TextView rcvTransactionchartItemDate;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}