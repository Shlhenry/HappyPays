package com.futeng.happypays.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.futeng.happypays.tools.PinYinUtil;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/11/17.
 */

public class LosisticNameBean implements Comparable<LosisticNameBean> {

    private String number ;
    private String letter ;
    private String name ;
    private String tel ;
    private String type ;

    public String getmPinyin() {
        return mPinyin;
    }

    public void setmPinyin(String mPinyin) {
        this.mPinyin = mPinyin;
    }

    private String mPinyin;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public LosisticNameBean(String mnumber,String mletter,String mname,String mtel,String mtype) {
        number = mnumber;
        letter = mletter;
        name=mname;
        tel=mtel;
        type=mtype;

        mPinyin = PinYinUtil.toPinyin(mname);
    }

    @Override
    public int compareTo(@NonNull LosisticNameBean losisticNameBean) {
        String names = losisticNameBean.getName();
        String pinyin = losisticNameBean.getmPinyin();
        if (TextUtils.isEmpty(mPinyin)) {
            return name.compareToIgnoreCase(TextUtils.isEmpty(pinyin) ? names : pinyin);
        } else {
            return mPinyin.compareToIgnoreCase(TextUtils.isEmpty(pinyin) ? names : pinyin);
        }
    }
}
