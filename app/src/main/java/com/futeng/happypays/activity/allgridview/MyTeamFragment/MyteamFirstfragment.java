package com.futeng.happypays.activity.allgridview.MyTeamFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.TeamMyAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.TeamMyBean;
/***
 * 我的团队---我
 */
public class MyteamFirstfragment extends Fragment{

    @InjectView(R.id.myteam_amname)
    TextView myteamAmname;
    @InjectView(R.id.myteamid)
    TextView myteamid;
    @InjectView(R.id.dengjiname)
    TextView dengjiname;
    @InjectView(R.id.myteam_lv_first)
    ListView myteamLvFirst;

    private TeamMyBean teamMyBean=null;
    private TeamMyAdapter teamMyAdapter=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_myteamfirst, container, false);
        ButterKnife.inject(this, v);
        //获取商户号
        String amNumber = SPUtils.getString(getContext(), "AmNumber");
        myteammyinfo(amNumber);
        return v;
    }

    private void initView() {
        teamMyAdapter = new TeamMyAdapter(getActivity(),teamMyBean.getList());
        myteamLvFirst.setAdapter(teamMyAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    private void myteammyinfo(String amNumber){
        Map<String,Object>map=new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("mode",1);

        XUtil.Post(Config.TeamServlet_URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                teamMyBean= JSON.parseObject(result,TeamMyBean.class);

                if ("99".equals(teamMyBean.getCode())){
                    MyApp.getInstance().showToast(teamMyBean.getFailMessage());
                }else if("00".equals(teamMyBean.getCode())){
                    if (teamMyBean.getList()==null){
                        return;
                    }else {
                        dengjiname.setText(SPUtils.getString(getContext(), "gradeName"));
                        myteamAmname.setText(SPUtils.getString(getContext(), "amName"));
                        myteamid.setText(SPUtils.getString(getContext(), "phone"));
                        Log.e("listSize",teamMyBean.getList().size()+"");
                        initView();
                    }
                }

            }
        });
    }

}
