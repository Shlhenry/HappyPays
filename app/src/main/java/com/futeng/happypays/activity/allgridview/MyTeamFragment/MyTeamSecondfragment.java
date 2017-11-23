package com.futeng.happypays.activity.allgridview.MyTeamFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.MyCommondBean;
/***
 * 我的团队---我的推荐人
 */
public class MyTeamSecondfragment extends Fragment {

    @InjectView(R.id.team_second_amname)
    TextView teamSecondAmname;
    @InjectView(R.id.team_second_amPersonPhone)
    TextView teamSecondAmPersonPhone;
    @InjectView(R.id.team_second_gradeName)
    TextView teamSecondGradeName;
    @InjectView(R.id.myteamsecond)
    LinearLayout myteamsecond;
    @InjectView(R.id.commentno)
    LinearLayout commentno;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_my_team_second, container, false);

        String AmNumber = SPUtils.getString(getActivity(), "AmNumber");
        myrecommend(AmNumber);


        ButterKnife.inject(this, v);
        return v;
    }


    private void myrecommend(String amNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("mode", 2);
        XUtil.Post(Config.TeamServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                MyCommondBean myCommondBean = JSON.parseObject(result, MyCommondBean.class);

                if (myCommondBean.getMap() != null) {
                    myteamsecond.setVisibility(View.VISIBLE);
                    teamSecondAmname.setText(myCommondBean.getMap().getAmName());
                    teamSecondAmPersonPhone.setText(myCommondBean.getMap().getAmPersonPhone());
                    teamSecondGradeName.setText(myCommondBean.getMap().getGradeName());
                } else {
                    commentno.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
