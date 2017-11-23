package com.futeng.happypays.activity.macaadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import butterknife.InjectView;
import butterknife.ButterKnife;
import com.futeng.happypays.R;

/**
 * author: MaCa
 * time  : 2016/11/10 14:38
 * note  : TODO
 */
public class MRcvChartLeftAdapter extends SimpleRecAdapter<String, MRcvChartLeftAdapter.ViewHolder> {


    public MRcvChartLeftAdapter(Context context) {
        super(context);
    }


    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.rcv_transactionchart_left_item_layout;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.rcvTransactionchartItemLeftNum.setText(data.get(position) + "");
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.rcv_transactionchart_item_left_num)
        TextView rcvTransactionchartItemLeftNum;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}