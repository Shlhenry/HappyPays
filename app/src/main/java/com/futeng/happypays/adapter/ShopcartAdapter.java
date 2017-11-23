//package com.futeng.happypays.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.support.v7.app.AlertDialog;
//import android.text.Spannable;
//import android.text.SpannableString;
//import android.text.style.StrikethroughSpan;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.liuyanping.yimiaogou.R;
//import com.example.liuyanping.yimiaogou.bean.BeanCartContent;
//import com.example.liuyanping.yimiaogou.model.CommonCinfigs;
//import com.example.liuyanping.yimiaogou.utils.ImageUtils;
//import com.example.liuyanping.yimiaogou.xhomeactivity.Commodity_content;
//import com.example.liuyanping.yimiaogou.xpersonactivity.Shop_Entershop_FragmentActivity;
//
//import java.util.List;
//
//import static com.example.liuyanping.yimiaogou.xpersonactivity.MyApplication.mContext;
//
///**
// * 购物车数据适配器
// */
//public class ShopcartAdapter extends BaseExpandableListAdapter {
//    private List<BeanCartContent.Data> groups;
//    private Context context;
//    private CheckInterface checkInterface;
//    private ModifyCountInterface modifyCountInterface;
//    private int flag = 0;
//    private GroupEdtorListener mListener;
//    private AlertDialog alertDialog;
//    private Activity activity;
//
//    public GroupEdtorListener getmListener() {
//        return mListener;
//    }
//
//
//    public void setmListener(GroupEdtorListener mListener) {
//        this.mListener = mListener;
//    }
//
//    /**
//     * 构造函数
//     *
//     * @param groups  组元素列表
//     * @param
//     * @param context
//     */
//    public ShopcartAdapter(List<BeanCartContent.Data> groups,
//                           Context context, Activity ac) {
//        this.groups = groups;
//        this.context = context;
//        this.activity = ac;
//    }
//
//    public void setCheckInterface(CheckInterface checkInterface) {
//        this.checkInterface = checkInterface;
//    }
//
//    public void setModifyCountInterface(
//            ModifyCountInterface modifyCountInterface) {
//        this.modifyCountInterface = modifyCountInterface;
//    }
//
//    //父级个数
//    @Override
//    public int getGroupCount() {
//        if (groups == null) {
//            return 0;
//        } else {
//
//            return groups.size();
//        }
//    }
//
//    //子类个数
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        ;
//        return groups.get(groupPosition).getGoods().size();
//    }
//
//    //返回父类列表的对象
//    @Override
//    public Object getGroup(int groupPosition) {
//        return groups.get(groupPosition);
//    }
//
//    //返回子类列表的对象
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//
//        return groups.get(groupPosition).getGoods().get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getGroupView(final int groupPosition, boolean isExpanded,
//                             View convertView, ViewGroup parent) {
//        final GroupViewHolder gholder;
//        if (convertView == null) {
//            gholder = new GroupViewHolder();
//            convertView = View.inflate(context, R.layout.item_shopcart_group,
//                    null);
//            gholder.cb_check = (CheckBox) convertView
//                    .findViewById(R.id.determine_chekbox);
//            gholder.tv_group_name = (TextView) convertView
//                    .findViewById(R.id.tv_source_name);
//            gholder.store_edtor = (Button) convertView
//                    .findViewById(R.id.tv_store_edtor);
//            gholder.tv_store_name_lin = (LinearLayout) convertView.findViewById(R.id.tv_store_name_lin);
//            convertView.setTag(gholder);
//        } else {
//            gholder = (GroupViewHolder) convertView.getTag();
//        }
//        gholder.tv_group_name.setText(groups.get(groupPosition).getShop_name());
//        gholder.tv_store_name_lin.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, Shop_Entershop_FragmentActivity.class);
//                intent.putExtra("shop_id", groups.get(groupPosition).getShop_id());
//                activity.startActivity(intent);
//            }
//        });
//        if (groups != null) {
//            gholder.tv_group_name.setText(groups.get(groupPosition).getShop_name());
//            gholder.cb_check.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v)
//
//                {
//                    groups.get(groupPosition).setChoosed(((CheckBox) v).isChecked());
//                    checkInterface.checkGroup(groupPosition,
//                            ((CheckBox) v).isChecked());// 暴露组选接口
//                }
//            });
//            gholder.cb_check.setChecked(groups.get(groupPosition).isChoosed());
//            gholder.store_edtor.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.groupEdit(groupPosition);
//                    if (flag == 0) {
//                        groups.get(groupPosition).setIsEdtor(true);
//                        gholder.store_edtor.setText("完成");
//                    } else if (flag == 1) {
//                        groups.get(groupPosition).setIsEdtor(false);
//                        gholder.store_edtor.setText("编辑");
//                    }
//                    flag = (flag + 1) % 2;// 其余得到循环执行上面2个不同的功能
//                }
//            });
//        } else {
//            groups.remove(groupPosition);
//        }
//
//        return convertView;
//    }
//
//    @Override
//    public View getChildView(final int groupPosition, final int childPosition,
//                             final boolean isLastChild, View convertView, final ViewGroup parent) {
//        final ChildViewHolder cholder;
//        if (convertView == null) {
//            cholder = new ChildViewHolder();
//            convertView = View.inflate(context, R.layout.item_shopcart_product,
//                    null);
//            cholder.cb_check = (CheckBox) convertView
//                    .findViewById(R.id.check_box);
//            cholder.tv_product_desc = (TextView) convertView
//                    .findViewById(R.id.tv_intro);
//            cholder.tv_price = (TextView) convertView
//                    .findViewById(R.id.tv_price);
//            cholder.iv_increase = (TextView) convertView
//                    .findViewById(R.id.tv_add);
//            cholder.iv_decrease = (TextView) convertView
//                    .findViewById(R.id.tv_reduce);
//            cholder.tv_count = (TextView) convertView.findViewById(R.id.tv_num);
//            cholder.rl_no_edtor = (LinearLayout) convertView
//                    .findViewById(R.id.rl_no_edtor);
//            cholder.tv_color_size = (TextView) convertView
//                    .findViewById(R.id.tv_color_size);
//            cholder.tv_discount_price = (TextView) convertView
//                    .findViewById(R.id.tv_discount_price);
//            cholder.tv_buy_num = (TextView) convertView
//                    .findViewById(R.id.tv_buy_num);
//            cholder.ll_edtor = (LinearLayout) convertView
//                    .findViewById(R.id.ll_edtor);
//            cholder.tv_colorsize = (TextView) convertView
//                    .findViewById(R.id.tv_colorsize);
//            cholder.tv_goods_delete = (TextView) convertView
//                    .findViewById(R.id.tv_goods_delete);
//            cholder.iv_adapter_list_pic = (ImageView) convertView
//                    .findViewById(R.id.iv_adapter_list_pic);
//            cholder.shopcart_product_lin = (LinearLayout) convertView.findViewById(R.id.shopcart_product_lin);
//            convertView.setTag(cholder);
//        } else {
//            cholder = (ChildViewHolder) convertView.getTag();
//        }
//        if (groups.get(groupPosition).isEdtor() == true) {
//            cholder.ll_edtor.setVisibility(View.VISIBLE);
//            cholder.rl_no_edtor.setVisibility(View.GONE);
//        } else {
//            cholder.ll_edtor.setVisibility(View.GONE);
//            cholder.rl_no_edtor.setVisibility(View.VISIBLE);
//        }
//
//        if (groups.get(groupPosition).getGoods() != null) {
//            cholder.tv_product_desc.setText(groups.get(groupPosition).getGoods().get(childPosition).getAccesno());
//            cholder.tv_price.setText("￥" + groups.get(groupPosition).getGoods().get(childPosition).getShop_price() + "");
//            cholder.tv_count.setText(groups.get(groupPosition).getGoods().get(childPosition).getAccess_number() + "");
//            ImageUtils.load(mContext, CommonCinfigs.Pic + groups.get(groupPosition).getGoods().get(childPosition).getBannermainpics(),
//                    cholder.iv_adapter_list_pic);
//            cholder.tv_color_size.setText("规格:"+groups.get(groupPosition).getGoods().get(childPosition).getSpec_name());
//            cholder.tv_colorsize.setText("规格:"+groups.get(groupPosition).getGoods().get(childPosition).getSpec_name());
//
//
//            SpannableString spanString = new SpannableString("￥"
//                    + groups.get(groupPosition).getGoods().get(childPosition).getMarket_price());
//            StrikethroughSpan span = new StrikethroughSpan();
//            spanString.setSpan(span, 0,
//                    groups.get(groupPosition).getGoods().get(childPosition).getMarket_price().length() + 1,
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            if (cholder.tv_discount_price.getText().toString().length() > 0) {
//                cholder.tv_discount_price.setText("");
//            }
//            cholder.tv_discount_price.append(spanString);
//            cholder.tv_buy_num.setText("x" + Integer.valueOf(groups.get(groupPosition).getGoods().get(childPosition).getAccess_number()).intValue());
//            cholder.cb_check.setChecked(groups.get(groupPosition).getGoods().get(childPosition).isChoosed());
//            cholder.cb_check.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    groups.get(groupPosition).getGoods().get(childPosition).setChoosed(((CheckBox) v).isChecked());
//                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
//                    checkInterface.checkChild(groupPosition, childPosition,
//                            ((CheckBox) v).isChecked());// 暴露子选接口
//                }
//            });
//            cholder.iv_increase.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    modifyCountInterface.doIncrease(groupPosition,
//                            childPosition, cholder.tv_count,
//                            cholder.cb_check.isChecked());// 暴露增加接口
//                }
//            });
//            cholder.iv_decrease.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    modifyCountInterface.doDecrease(groupPosition,
//                            childPosition, cholder.tv_count,
//                            cholder.cb_check.isChecked());// 暴露删减接口
//                }
//            });
//            // 删除 购物车
//            cholder.tv_goods_delete.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog alert = new AlertDialog.Builder(context)
//                            .create();
//                    alert.setTitle("操作提示");
//                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
//                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog,
//                                                    int which) {
//                                    return;
//                                }
//                            });
//                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog,
//                                                    int which) {
//                                    modifyCountInterface.childDelete(
//                                            groupPosition, childPosition);
//
//                                }
//                            });
//                    alert.show();
//
//                }
//            });
//        }
//        cholder.shopcart_product_lin.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, Commodity_content.class);
//                intent.putExtra("shop_id", groups.get(groupPosition).getGoods().get(childPosition).getId());
//                activity.startActivity(intent);
//            }
//        });
//        return convertView;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return false;
//    }
//
//    /**
//     * 组元素绑定器
//     */
//    private class GroupViewHolder {
//        CheckBox cb_check;
//        TextView tv_group_name;
//        Button store_edtor;
//        LinearLayout tv_store_name_lin;
//    }
//
//    /**
//     * 子元素绑定器
//     */
//    private class ChildViewHolder {
//        CheckBox cb_check;
//        ImageView iv_adapter_list_pic;
//        TextView tv_product_name;
//        TextView tv_product_desc;
//        TextView tv_price;
//        TextView iv_increase;
//        TextView tv_count;
//        TextView iv_decrease;
//        LinearLayout rl_no_edtor;
//        TextView tv_color_size;
//        TextView tv_discount_price;
//        TextView tv_buy_num;
//        LinearLayout ll_edtor;
//        TextView tv_colorsize;
//        TextView tv_goods_delete;
//        LinearLayout shopcart_product_lin;
//    }
//
//    /**
//     * 复选框接口
//     */
//    public interface CheckInterface {
//        /**
//         * 组选框状态改变触发的事件
//         *
//         * @param groupPosition 组元素位置
//         * @param isChecked     组元素选中与否
//         */
//        public void checkGroup(int groupPosition, boolean isChecked);
//
//        /**
//         * 子选框状态改变时触发的事件
//         *
//         * @param groupPosition 组元素位置
//         * @param childPosition 子元素位置
//         * @param isChecked     子元素选中与否
//         */
//        public void checkChild(int groupPosition, int childPosition,
//                               boolean isChecked);
//    }
//
//    /**
//     * 改变数量的接口
//     */
//    public interface ModifyCountInterface {
//        /**
//         * 增加操作
//         *
//         * @param groupPosition 组元素位置
//         * @param childPosition 子元素位置
//         * @param showCountView 用于展示变化后数量的View
//         * @param isChecked     子元素选中与否
//         */
//        public void doIncrease(int groupPosition, int childPosition,
//                               View showCountView, boolean isChecked);
//
//        /**
//         * 删减操作
//         *
//         * @param groupPosition 组元素位置
//         * @param childPosition 子元素位置
//         * @param showCountView 用于展示变化后数量的View
//         * @param isChecked     子元素选中与否
//         */
//        public void doDecrease(int groupPosition, int childPosition,
//                               View showCountView, boolean isChecked);
//
//        /**
//         * 删除子item
//         *
//         * @param groupPosition
//         * @param childPosition
//         */
//        public void childDelete(int groupPosition, int childPosition);
//    }
//
//    /**
//     * 监听编辑状态
//     */
//    public interface GroupEdtorListener {
//        public void groupEdit(int groupPosition);
//    }
//
//
//}
