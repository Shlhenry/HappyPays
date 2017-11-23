package com.futeng.happypays.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.futeng.happypays.R;

/**
 * Created by cen on 2016/12/14.
 */
public class WritePadDialog extends Dialog {
    private Context mContext;
    private WriteDialogListener mWriteDialogListener;
    private PaintView mPaintView;
    private FrameLayout mFrameLayout;
    private Button mBtnOK, mBtnClear, mBtnCancel;

    public WritePadDialog(Context context,
                          WriteDialogListener writeDialogListener) {
        super(context);
        this.mContext = context;
        this.mWriteDialogListener = writeDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //无标题
        setContentView(R.layout.write_pad);

        mFrameLayout = (FrameLayout) findViewById(R.id.table_view);

        // 获取屏幕尺寸
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int screenWidth = mDisplayMetrics.widthPixels;
        int screenHeight = mDisplayMetrics.heightPixels;
        Log.v("bill","screenWidth="+screenWidth+" screenHeight="+screenHeight);
        mPaintView = new PaintView(mContext, screenWidth, screenHeight);
        mFrameLayout.addView(mPaintView);
        mPaintView.requestFocus();

        mBtnOK = (Button) findViewById(R.id.button_OK);
        mBtnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mPaintView.getPath().isEmpty()) {
                    Toast.makeText(mContext, "请签名", Toast.LENGTH_SHORT).show();
                    return;
                }
                mWriteDialogListener.onPaintDone(mPaintView.getPaintBitmap());
                dismiss();
            }
        });

        mBtnClear = (Button) findViewById(R.id.button_redraw);
        mBtnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPaintView.clear();
            }
        });
    }
}
