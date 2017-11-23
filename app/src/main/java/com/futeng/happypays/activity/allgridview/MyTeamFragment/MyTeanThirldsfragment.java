package com.futeng.happypays.activity.allgridview.MyTeamFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.MyExpandableListViewAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.AllCommentBean;
import com.futeng.happypays.utils.CommendBean;
import com.futeng.happypays.utils.FailCommentBean;

/**
 * Created by Administrator on 2017/9/14.
 */
public class MyTeanThirldsfragment extends Fragment {

    @InjectView(R.id.expandablelistview)
    ExpandableListView expandablelistview;
    @InjectView(R.id.commentnos)
    LinearLayout commentnos;

    private CommendBean commendBean = null;
    private AllCommentBean allCommentBean=null;
    private FailCommentBean failCommentBean=null;
    private MyExpandableListViewAdapter commentAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expandable_layout, container, false);

        //获取商户号
        String amNumber = SPUtils.getString(getContext(), "AmNumber");
        comment(amNumber);
        failcomment(amNumber);
        allcomment(amNumber);
        ButterKnife.inject(this, v);
        return v;
    }

    private void initView() {
        commentAdapter = new MyExpandableListViewAdapter(getActivity(), commendBean.getList());
        expandablelistview.setAdapter(commentAdapter);
    }



    private void comment(String amNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("mode", 3);

        XUtil.Post(Config.TeamServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                commendBean = JSON.parseObject(result, CommendBean.class);
                if ("99".equals(commendBean.getCode())) {
                    commentnos.setVisibility(View.VISIBLE);
                } else if ("00".equals(commendBean.getCode())) {
                    if (commendBean.getList() == null) {
                        commentnos.setVisibility(View.VISIBLE);
                        return;
                    } else {
                        expandablelistview.setVisibility(View.VISIBLE);
                        initView();
                    }
                }
            }
        });
    }

    private void failcomment(String amNumber){
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("mode", 4);

        XUtil.Post(Config.TeamServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                failCommentBean=JSON.parseObject(result,FailCommentBean.class);

            }
        });

    }

    private void allcomment(String amNumber){
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("mode", 23);

        XUtil.Post(Config.TeamServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                allCommentBean=JSON.parseObject(result,AllCommentBean.class);

            }
        });

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
