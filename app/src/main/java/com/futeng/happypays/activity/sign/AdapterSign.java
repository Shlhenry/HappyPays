package com.futeng.happypays.activity.sign;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.futeng.happypays.R;


/**
 * Created by lane on 2017/9/25.
 * Email :cocoinut@163.com
 * Instructions :一句话说明当前类
 */

public class AdapterSign extends RecyclerView.Adapter {
    private Context context;
    private List<Integer> list;

    private int index;
    private LayoutInflater mLayoutInflater;
    //点前年月
    private int year, month, day;
    int SIZE;

    public AdapterSign(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
        this.list = list;
        init();
        getDate();
        mLayoutInflater = LayoutInflater.from(context);
    }

    private void init() {
        //获取当前的年
        SimpleDateFormat year_formatter = new SimpleDateFormat("yyyy");
        Date year_curDate = new Date(System.currentTimeMillis());
        //获取当前的月
        SimpleDateFormat month_formatter = new SimpleDateFormat("MM");
        Date month_curDate1 = new Date(System.currentTimeMillis());

        //获取当前的日
        SimpleDateFormat day_formatter = new SimpleDateFormat("dd");
        Date day_curDate1 = new Date(System.currentTimeMillis());

        year = Integer.parseInt(year_formatter.format(year_curDate));
        month = Integer.parseInt(month_formatter.format(month_curDate1));
        day = Integer.parseInt(day_formatter.format(day_curDate1));

        //根据指定年月获取月的天数
        SIZE = TestGetWeek.getDaysByYearMonth(year, month);
    }

    private void getDate() {
        //得到每个月份一号是星期几
        String date = TestGetWeek.getDayOfWeekByDate(year + "-" + month + "-01");
        if (date.equals("周一")) {
            index = 0;
        } else if (date.equals("周二")) {
            index = 1;
        } else if (date.equals("周三")) {
            index = 2;
        } else if (date.equals("周四")) {
            index = 3;
        } else if (date.equals("周五")) {
            index = 4;
        } else if (date.equals("周六")) {
            index = 5;
        } else if (date.equals("周日")) {
            index = 6;
        }
    }


    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new HolderTwo(mLayoutInflater.inflate(R.layout.item_sign, parent, false));
        } else {
            return new Holder(mLayoutInflater.inflate(R.layout.item_sign, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HolderTwo) {
            ((HolderTwo) holder).two_item_li.setVisibility(View.INVISIBLE);
        } else if (holder instanceof Holder) {
            if (position - index > SIZE) {
                //  ((TwoHolder) holder).two_item_li.setVisibility(View.INVISIBLE);
            } else {
                ((Holder) holder).two_item_li.setVisibility(View.VISIBLE);
                int a = position - index + 1;
                if (a < 10) {
                    ((Holder) holder).tv_date.setText("0" + a + "");
                } else {
                    ((Holder) holder).tv_date.setText(a + "");
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == a) {
                        ((Holder) holder).iv_gou.setImageResource(R.mipmap.duihao);
                        ((Holder) holder).tv_date.setTextColor(Color.parseColor("#333333"));
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return SIZE + index;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < index)
            return ITEM_TYPE.ITEM1.ordinal();

        else
            return ITEM_TYPE.ITEM2.ordinal();
    }

    public class HolderTwo extends RecyclerView.ViewHolder {
        LinearLayout two_item_li;

        public HolderTwo(View itemView) {
            super(itemView);
            two_item_li = (LinearLayout) itemView.findViewById(R.id.item_li);
        }
    }

    //item1 的ViewHolder
    public class Holder extends RecyclerView.ViewHolder {
        LinearLayout two_item_li;
        TextView tv_date;
        ImageView iv_gou;

        public Holder(View itemView) {
            super(itemView);
            two_item_li = (LinearLayout) itemView.findViewById(R.id.item_li);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_gou = (ImageView) itemView.findViewById(R.id.iv_gou);
        }
    }

}
