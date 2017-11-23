package com.futeng.happypays.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.SelectBankcardBean;

/**
 * Created by Administrator on 2017/9/22.
 */
public class SelectBankcardAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<SelectBankcardBean.ListBean> mList;
    private Context mContext;

    private int temp = -1;
    private SparseBooleanArray mSelectArray;//储存boolean类型pair(key,value)
    private boolean boo=true;
    private  int  CheckedPosition;

    public SelectBankcardAdapter(Context context, List<SelectBankcardBean.ListBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
        mSelectArray=new SparseBooleanArray();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final viewholder holder;
        if (convertView == null) {
            holder = new viewholder();
            convertView = mInflater.inflate(R.layout.selectbank_item, null);
            holder.selectbank_img = (ImageView) convertView.findViewById(R.id.selectbank_img);
            holder.selectbank_name = (TextView) convertView.findViewById(R.id.selectbank_name);
            holder.quan_default= (CheckBox) convertView.findViewById(R.id.quan_default);
            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }

        Glide.with(convertView.getContext())
                .load(mList.get(position).getAbcPicUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.flagtongyong).into(holder.selectbank_img);


        holder.selectbank_name.setText(mList.get(position).getAbcRemark());

        if (mList.get(position).getAbcDefaultNum()==1&&boo==true){
            holder.quan_default.setChecked(true);
        }else {
            holder.quan_default.setChecked(mSelectArray.get(position, false));
        }

        holder.quan_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setHttp(AddData.get(position).getAddress_id());
                updefaultbankcard(mList.get(position).getAccountNumber());
                boo=false;
                mSelectArray.clear();
                CheckedPosition=position;
                mSelectArray.put(position,holder.quan_default.isChecked());
                notifyDataSetChanged();
            }
        });


        return convertView;
    }


    private void updefaultbankcard(String cardnum){
        Map<String,Object> map=new HashMap<>();
        map.put("mode",4);
        map.put("amNumber","883000000000105");
        map.put("accountNumber",cardnum);
        XUtil.Post(Config.AMBandCreditServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
            }
        });
    }



    public static class viewholder {
         ImageView selectbank_img;
         TextView selectbank_name;
         CheckBox quan_default;
    }


}