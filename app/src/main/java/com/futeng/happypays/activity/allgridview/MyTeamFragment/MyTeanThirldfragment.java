package com.futeng.happypays.activity.allgridview.MyTeamFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.CommentAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.CommendBean;
/***
 * 我的团队---我推荐的人
 */
public class MyTeanThirldfragment extends Fragment {

    @InjectView(R.id.myteam_peoplenum)
    TextView myteamPeoplenum;
    @InjectView(R.id.myteamthird_lv_list)
    ListView myteamthirdLvList;
    @InjectView(R.id.comment_num)
    LinearLayout commentNum;
    @InjectView(R.id.commentnos)
    LinearLayout commentnos;

    private CommendBean commendBean = null;
    private CommentAdapter commentAdapter = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_my_tean_thirld, container, false);

        //获取商户号
        String amNumber = SPUtils.getString(getContext(), "AmNumber");
        comment(amNumber);

        ButterKnife.inject(this, v);
        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void initView() {
        commentAdapter = new CommentAdapter(getActivity(), commendBean.getList());
        myteamthirdLvList.setAdapter(commentAdapter);
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
                        commentNum.setVisibility(View.VISIBLE);
                        myteamthirdLvList.setVisibility(View.VISIBLE);
                        myteamPeoplenum.setText(commendBean.getList().size() + "");
                        initView();
                    }
                }
            }
        });
    }

}
