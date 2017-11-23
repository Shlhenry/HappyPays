package com.futeng.happypays.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.futeng.happypays.tools.PinYinUtil;

@SuppressWarnings("unused")
public class Girl implements Comparable<Girl> {

    private String mName;//美元
    private String mPinyin;
    private String imgUrl;//国旗图片的url
    private double huilvValue;//100人民币->外币
    private String huilv;//国家的英文简称

    public double getHuilvValue() {
        return huilvValue;
    }

    public void setHuilvValue(double huilvValue) {
        this.huilvValue = huilvValue;
    }

    public String getHuilv() {
        return huilv;
    }

    public void setHuilv(String huilv) {
        this.huilv = huilv;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private Integer index;

    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }

    public Girl(String name,String imagUlr,Integer inde,String Huilv,double huilvvalue) {
        mName = name;
        index = inde;
        imgUrl=imagUlr;
        huilv=Huilv;
        huilvValue=huilvvalue;

        mPinyin = PinYinUtil.toPinyin(mName);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPinyin() {
        return mPinyin;
    }

    public void setPinyin(String pinyin) {
        mPinyin = pinyin;
    }

    @Override
    public int compareTo(@NonNull Girl another) {
        String name = another.getName();
        String pinyin = another.getPinyin();
        if (TextUtils.isEmpty(mPinyin)) {
            return mName.compareToIgnoreCase(TextUtils.isEmpty(pinyin) ? name : pinyin);
        } else {
            return mPinyin.compareToIgnoreCase(TextUtils.isEmpty(pinyin) ? name : pinyin);
        }
    }
}
