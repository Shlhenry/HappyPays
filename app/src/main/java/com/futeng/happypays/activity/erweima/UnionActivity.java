package com.futeng.happypays.activity.erweima;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.net.URLDecoder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;
/***
 * 二维码收款---银联T1
 */
public class UnionActivity extends Activity {

    @InjectView(R.id.union_back)
    ImageView unionBack;
    @InjectView(R.id.image_content)
    ImageView imageContent;

    public Bitmap mBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union);
        ButterKnife.inject(this);

        String PsUrl= SPUtils.getString(UnionActivity.this, "PsUrl");

        try {
            PsUrl = URLDecoder.decode(PsUrl,"UTF-8");
        }catch (Exception e){
        }
        Log.e("ssssssssss:",PsUrl+"");
        //加载二维码图片
        mBitmap = CodeUtils.createImage(PsUrl, 500, 500, null);
        imageContent.setImageBitmap(mBitmap);

    }

    @OnClick(R.id.union_back)
    public void onClick() {
        finish();
    }
}
