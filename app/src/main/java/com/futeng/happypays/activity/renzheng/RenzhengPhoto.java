package com.futeng.happypays.activity.renzheng;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.CropHandler;
import com.futeng.happypays.tools.CropParams;
import com.futeng.happypays.tools.ImageLoaderUtil;
import com.futeng.happypays.tools.PhotoCropUtil;
import com.futeng.happypays.tools.FileUtil;
import com.futeng.happypays.tools.SPUtils;

/**
 * Created by Administrator on 2017/9/7.
 */
public class RenzhengPhoto extends FragmentActivity {
    @InjectView(R.id.rz_photo_back)
    ImageView rzPhotoBack;
    @InjectView(R.id.rz_photo_one)
    ImageView rzPhotoOne;
    @InjectView(R.id.rz_photo_two)
    ImageView rzPhotoTwo;
    @InjectView(R.id.rz_photo_four)
    ImageView rzPhotoFour;
    @InjectView(R.id.rz_photo_five)
    ImageView rzPhotoFive;
    @InjectView(R.id.rz_phto_next)
    Button rzPhtoNext;

    CropParams mCropParams;

    String imageStr1;
    String imageStr2;
    String imageStr4;
    String imageStr5;

    private int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renzheng_photos);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.rz_photo_back, R.id.rz_photo_one, R.id.rz_photo_two, R.id.rz_photo_four, R.id.rz_photo_five, R.id.rz_phto_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rz_photo_back:
                finish();
                break;
            case R.id.rz_photo_one:
                takePhoto(false);
                i=1;
                break;
            case R.id.rz_photo_two:
                takePhoto(false);
                i=2;
                break;
            case R.id.rz_photo_four:
                takePhoto(false);
                i=4;
                break;
            case R.id.rz_photo_five:
                takePhoto(false);
                i=5;
                break;
            case R.id.rz_phto_next:
                SPUtils.put(RenzhengPhoto.this, "imageStr1", imageStr1);
                SPUtils.put(RenzhengPhoto.this, "imageStr2", imageStr2);
                SPUtils.put(RenzhengPhoto.this, "imageStr4", imageStr4);
                SPUtils.put(RenzhengPhoto.this, "imageStr5", imageStr5);

                if (imageStr1==null){
                    Toast.makeText(RenzhengPhoto.this, "身份证正面照不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(imageStr2==null){
                    Toast.makeText(RenzhengPhoto.this, "身份证背面照不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(imageStr4==null){
                    Toast.makeText(RenzhengPhoto.this, "银行卡背面照不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(imageStr5==null){
                    Toast.makeText(RenzhengPhoto.this, "手持身份证银行卡不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    startActivity(new Intent(this, RenzhengZiliaoqueren.class));
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PhotoCropUtil.REQUEST_CAMERA:
            PhotoCropUtil.handleResult(new CropHandler() {
                @Override
                public void onPhotoCropped(Uri uri) {
                    if (i==1) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoOne, null);
                        imageStr1 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("******", imageStr1);
                    } else if (i==2) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoTwo, null);
                        imageStr2 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("******", imageStr2);
                    } else if (i==4) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoFour, null);
                        imageStr4 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("******", imageStr4);
                    } else if (i==5) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoFive, null);
                        imageStr5 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("******", imageStr5);
                    }
                }

                @Override
                public void onCompressed(Uri uri) {
                    if (i==1) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoOne, null);
                        imageStr1 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("***11111111***", imageStr1);
                    } else if (i==2) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoTwo, null);
                        imageStr2 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("***22222222***", imageStr2);
                    } else if (i==4) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoFour, null);
                        imageStr4 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("***444444***", imageStr4);
                    } else if (i==5) {
                        String path = uri.toString();
                        ImageLoaderUtil.loadImage(path, rzPhotoFive, null);
                        imageStr5 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                        Log.e("***555555***", imageStr5);
                    }
                }

                @Override
                public void onCancel() {
                }

                @Override
                public void onFailed(String message) {
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
        }
    }

    private void takePhoto(boolean isCrop) {
        mCropParams = new CropParams(this);
        mCropParams.enable = isCrop;
        mCropParams.compress = true;
        mCropParams.refreshUri();
        Intent intent = PhotoCropUtil.buildCameraIntent(mCropParams);
        startActivityForResult(intent, PhotoCropUtil.REQUEST_CAMERA);
    }


}
