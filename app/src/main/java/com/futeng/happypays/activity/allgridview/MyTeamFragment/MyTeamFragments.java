package com.futeng.happypays.activity.allgridview.MyTeamFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.CommentAdapter;
import com.futeng.happypays.adapter.NoCommentAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.AllCommentBean;
import com.futeng.happypays.utils.CommendBean;
import com.futeng.happypays.utils.FailCommentBean;

public class MyTeamFragments extends Fragment {
    @InjectView(R.id.parent_title)
    TextView parentTitle;
    @InjectView(R.id.arrow)
    ImageView arrow;
    @InjectView(R.id.pass_linear)
    LinearLayout passLinear;
    @InjectView(R.id.okpass_lv)
    ListView okpassLv;
    @InjectView(R.id.noparent_title)
    TextView noparentTitle;
    @InjectView(R.id.noarrow)
    ImageView noarrow;
    @InjectView(R.id.nopass_linear)
    LinearLayout nopassLinear;
    @InjectView(R.id.nopass_lv)
    ListView nopassLv;


    private CommendBean commendBean = null;
    private AllCommentBean allCommentBean=null;
    private FailCommentBean failCommentBean=null;
    private CommentAdapter commentAdapter = null;
    private NoCommentAdapter noCommentAdapter=null;

    public static  boolean a=true;
    public static  boolean b=true;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_my_team_fragments, container, false);
        ButterKnife.inject(this, v);

        //获取商户号
        String amNumber2 = SPUtils.getString(getContext(), "AmNumber");
        allcomment(amNumber2);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.pass_linear, R.id.nopass_linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pass_linear:
                Log.e("////",a+"");
                if (a){
                    String amNumber = SPUtils.getString(getContext(), "AmNumber");
                    comment(amNumber);
                    okpassLv.setVisibility(View.VISIBLE);
                    arrow.setBackgroundResource(R.mipmap.xialajiantou);
                    a=false;
                    Log.e("*****",a+"");
                }else{
                    okpassLv.setVisibility(View.GONE);
                    arrow.setBackgroundResource(R.mipmap.jiantou);
                    a=true;
                    Log.e("+++++",a+"");
                }
                break;
            case R.id.nopass_linear:

                Log.e("////",b+"");
                if (b){
                    String amNumber1 = SPUtils.getString(getContext(), "AmNumber");
                    failcomment(amNumber1);
                    nopassLv.setVisibility(View.VISIBLE);
                    noarrow.setBackgroundResource(R.mipmap.xialajiantou);
                    b=false;
                    Log.e("*****",b+"");
                }else{
                    nopassLv.setVisibility(View.GONE);
                    noarrow.setBackgroundResource(R.mipmap.jiantou);
                    b=true;
                    Log.e("+++++",b+"");
                }
                break;
        }
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
                        return;
                } else if ("00".equals(commendBean.getCode())) {
                    if (commendBean.getList() == null) {
                        return;
                    }
                    else {
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

                if (failCommentBean.getList()==null){
                    return;
                }else{
                    anotherinitView();
                }

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

                if (allCommentBean.getMap()!=null){
                    parentTitle.setText(allCommentBean.getMap().getAmState()+"");
                    noparentTitle.setText(allCommentBean.getMap().getPState()+"");
                }else {
                    Log.e("Map为空加载失败","Map为空加载失败");
                    return;
                }

            }
        });

    }

    private void initView() {
        commentAdapter = new CommentAdapter(getActivity(), commendBean.getList());
        okpassLv.setAdapter(commentAdapter);
    }
    private void anotherinitView(){
        noCommentAdapter=new NoCommentAdapter(getActivity(),failCommentBean.getList());
        nopassLv.setAdapter(noCommentAdapter);
    }
}
