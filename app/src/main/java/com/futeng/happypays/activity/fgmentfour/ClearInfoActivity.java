package com.futeng.happypays.activity.fgmentfour;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.CropHandler;
import com.futeng.happypays.tools.CropParams;
import com.futeng.happypays.tools.ImageLoaderUtil;
import com.futeng.happypays.tools.PhotoCropUtil;
import com.futeng.happypays.activity.renzheng.listview.BankListActivity;
import com.futeng.happypays.activity.renzheng.listview.CityActivity;
import com.futeng.happypays.activity.renzheng.listview.ProvinceListActivity;
import com.futeng.happypays.activity.webview.FindBankNumActivity;
import com.futeng.happypays.constant.Constant;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.FileUtil;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.UpCardInfoBean;
/*
*修改结算卡信息
 */
public class ClearInfoActivity extends Activity {

    @InjectView(R.id.rz_js_back)
    ImageView rzJsBack;
    @InjectView(R.id.jiesuan_name)
    EditText jiesuanName;
    @InjectView(R.id.jiesuan_bankcard)
    EditText jiesuanBankcard;
    @InjectView(R.id.allbank)
    TextView allbank;
    @InjectView(R.id.jiesuan_province)
    TextView jiesuanProvince;
    @InjectView(R.id.jiesuan_city)
    TextView jiesuanCity;
    @InjectView(R.id.jiesuan_otherbank)
    EditText jiesuanOtherbank;
    @InjectView(R.id.jiesuan_lianhanghao)
    EditText jiesuanLianhanghao;
    @InjectView(R.id.findbanknum)
    TextView findbanknum;
    @InjectView(R.id.upphoto)
    ImageView upphoto;
    @InjectView(R.id.up_js_next)
    Button upJsNext;

    private int ProvinceId = 0;//省份id
    private int CityId = 0;//城市id

    private int Bankid = 0;//银行id

    String SD_CARD_TEMP_DIRone;
    public static final int NONE = 0;
    public static final int PHOTOHRAPH = 1;// 拍照
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果
    public static final String IMAGE_UNSPECIFIED = "image/*";
    private final String TEMP_NAME = "temp.jpg";

    int selete = 0;
    Bitmap photo;

    String imageStr1;
    CropParams mCropParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_info);
        ButterKnife.inject(this);

        SD_CARD_TEMP_DIRone = Environment.getExternalStorageDirectory()
                + File.separator + "uptmpone.jpg";//设定照相后保存的文件名，类似于缓存文件

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_OK) {
            switch (resultCode) {
                case Constant.ProvinceRESULT_OK:
                    ProvinceId = data.getIntExtra(Constant.IN_City_ID, 0);
                    jiesuanProvince.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.CityRESULT_OK:
                    CityId = data.getIntExtra(Constant.IN_City_ID, 0);
                    jiesuanCity.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.BankRESULT_OK:
                    Bankid = data.getIntExtra(Constant.IN_BANK_ID, 0);
                    allbank.setText(data.getStringExtra(Constant.IN_BANK_NAME));
                    break;
            }
        }
        switch (requestCode) {
            case PhotoCropUtil.REQUEST_CAMERA:
            case PhotoCropUtil.REQUEST_PICK:
                PhotoCropUtil.handleResult(new CropHandler() {
                    @Override
                    public void onPhotoCropped(Uri uri) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, upphoto, null);

                        imageStr1= FileUtil.path2StrByBase64(Uri.parse(path).getPath());

                        Log.e("******",imageStr1);
                    }

                    @Override
                    public void onCompressed(Uri uri) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, upphoto, null);
                        imageStr1= FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("******",imageStr1);
                    }
                    @Override
                    public void onCancel() {
                        MyApp.getInstance().showToast("取消");
                    }
                    @Override
                    public void onFailed(String message) {
                        MyApp.getInstance().showToast("失败");
                    }
                    @Override
                    public void handleIntent(Intent intent, int requestCode) {
                        startActivityForResult(intent, requestCode);
                    }
                    @Override
                    public CropParams getCropParams() {
                        return mCropParams;
                    }
                }, requestCode, resultCode, data);
                break;
        }
    }
    private void Baoc() {
        //保存结算户名
        SPUtils.put(ClearInfoActivity.this, "jiesuanName", jiesuanName.getText().toString());
        //保存银行卡号
        SPUtils.put(ClearInfoActivity.this, "jiesuanBankcard", jiesuanBankcard.getText().toString());
        //保存联行号
        SPUtils.put(ClearInfoActivity.this, "jiesuanLianhanghao", jiesuanLianhanghao.getText().toString());
        //保存开户支行
        SPUtils.put(ClearInfoActivity.this, "jiesuanOtherbank", jiesuanOtherbank.getText().toString());
        //保存省id
        SPUtils.put(ClearInfoActivity.this, "Province", ProvinceId + "");
        //保存市id
        SPUtils.put(ClearInfoActivity.this, "City", CityId + "");
        //保存银行id
        SPUtils.put(ClearInfoActivity.this, "Bankid", Bankid + "");

        Log.e("++++++++++++", SPUtils.getString(getApplicationContext(), "Province"));
        Log.e("++++++++++++", SPUtils.getString(getApplicationContext(), "City"));
        Log.e("+++++++++++++", SPUtils.getString(getApplicationContext(), "Bankid"));
    }

    @OnClick({R.id.rz_js_back, R.id.jiesuan_name, R.id.jiesuan_bankcard,R.id.allbank, R.id.jiesuan_province, R.id.jiesuan_city, R.id.jiesuan_otherbank, R.id.jiesuan_lianhanghao, R.id.findbanknum, R.id.upphoto, R.id.up_js_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rz_js_back:
                finish();
                break;
            case R.id.jiesuan_name:
                break;
            case R.id.jiesuan_bankcard:
                break;
            case R.id.allbank:
                Intent intent2 = new Intent(ClearInfoActivity.this, BankListActivity.class);
                intent2.putExtra(Constant.IN_BANK_ID, Bankid);
                startActivityForResult(intent2, Constant.REQUEST_OK);
                break;
            case R.id.jiesuan_province:
                Intent intent = new Intent(ClearInfoActivity.this, ProvinceListActivity.class);
                startActivityForResult(intent, Constant.REQUEST_OK);
                break;
            case R.id.jiesuan_city:
                if (ProvinceId == 0) {
                    Toast.makeText(this, "请先选择省份", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent1 = new Intent(ClearInfoActivity.this, CityActivity.class);
                intent1.putExtra(Constant.IN_ID, ProvinceId);
                startActivityForResult(intent1, Constant.REQUEST_OK);
                break;
            case R.id.jiesuan_otherbank:
                break;
            case R.id.jiesuan_lianhanghao:
                break;
            case R.id.findbanknum:
                startActivity(new Intent(this, FindBankNumActivity.class));
                break;
            case R.id.upphoto:
                takePhoto(false);
                break;
            case R.id.up_js_next:
                String amNumber=SPUtils.getString(this,"AmNumber");
                String accountName=jiesuanName.getText().toString();
                String accountNumber=jiesuanBankcard.getText().toString();
                String bankBranchName=jiesuanOtherbank.getText().toString();
                String bankBranchNumber=jiesuanLianhanghao.getText().toString();
                String bank=Bankid+"";
                String city=CityId+"";
                String idcardandbankcard=imageStr1;
                ModifyBankServlet(amNumber,accountName,accountNumber,bank,city,bankBranchName,bankBranchNumber,idcardandbankcard);
                break;
        }
    }

    private void ModifyBankServlet(String amNumber,String accountName,String accountNumber,String bank,String city,String bankBranchName,String bankBranchNumber,String idcardandbankcard){

        Map<String,Object> map=new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("accountName",accountName);
        map.put("accountNumber",accountNumber);
        map.put("bank",bank);
        map.put("city",city);
        map.put("bankBranchName",bankBranchName);
        map.put("bankBranchNumber",bankBranchNumber);
        map.put("idcardandbankcard",idcardandbankcard);

        Log.e("----",map+"");

        XUtil.Post(Config.ModifyBankServlet_URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                UpCardInfoBean upCardInfoBean= JSON.parseObject(result,UpCardInfoBean.class);
                if ("00".equals(upCardInfoBean.getCode())){
                    MyApp.getInstance().showToast(upCardInfoBean.getSuccessMessage());
                    finish();
                }else if ("99".equals(upCardInfoBean.getCode())){
                    MyApp.getInstance().showToast(upCardInfoBean.getFailMessage());
                    finish();
                }
            }
        });
    }


    private void takePhoto(boolean isCrop) {
        mCropParams = new CropParams(this);
        mCropParams.enable = isCrop;
        mCropParams.compress = true;
        mCropParams.refreshUri();
        Intent intent = PhotoCropUtil.buildCameraIntent(mCropParams);
        startActivityForResult(intent, PhotoCropUtil.REQUEST_CAMERA);
    }
    //相册
    private void galleryPhoto(boolean isCrop) {
        mCropParams = new CropParams(this);
        mCropParams.enable = isCrop;
        mCropParams.compress = true;

        mCropParams.refreshUri();
        Intent intent = PhotoCropUtil.buildGalleryIntent(mCropParams);
        startActivityForResult(intent, PhotoCropUtil.REQUEST_PICK);
    }

}
