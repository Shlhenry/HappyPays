package com.futeng.happypays.activity.allgridview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import com.futeng.happypays.R;

/**
 * 分享有礼
 */
public class ShapeActivity extends Fragment {

    @InjectView(R.id.shape)
    ImageView shape;
    private View view;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.shape);
//        ButterKnife.inject(this);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shape, container, false);
        ButterKnife.inject(this,view);


        return view;
    }

    @OnClick({R.id.shape})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shape:
                showShare();
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("创乐付");
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://appurl.me/15502998");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("创乐付" + "http://appurl.me/15502998");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://appurl.me/15502998");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("创乐付");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://appurl.me/15502998");

        /*oks.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png");*/
        //oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        /*oks.setImageUrl("https://infile.cekom.com.cn/test/fileStore/portal/ecdoc/thumbnail/ios/20173/b08cb10aa52d4004b277e6ac53363e73.jpg");*/
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setImageUrl("http://120.27.138.219:8083/pic/happypays.png");
                    paramsToShare.setTitle("");
                    paramsToShare.setTitleUrl("");
                    paramsToShare.setText("");
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("创乐付");
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.mipmap.shareother);
                    paramsToShare.setImageData(imageData);
                    paramsToShare.setTitle("创乐付");
                    paramsToShare.setText("创乐付，是江苏付腾电子支付技术有限公司自主研发的一款集线上线下支付于一体的多功能收单app");
                    //paramsToShare.setUrl("http://appurl.me/15502998");
                    paramsToShare.setShareType(Platform.SHARE_IMAGE);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.mipmap.shareother);
                    paramsToShare.setImageData(imageData);
                    paramsToShare.setTitle("创乐付");
                    paramsToShare.setText("创乐付，是江苏付腾电子支付技术有限公司自主研发的一款集线上线下支付于一体的多功能收单app");
                    //paramsToShare.setUrl("http://appurl.me/15502998");
                    paramsToShare.setShareType(Platform.SHARE_IMAGE);
                }
                if ("QQ".equals(platform.getName())) {
//                    paramsToShare.setTitle("创乐付");
//                    paramsToShare.setTitleUrl("http://appurl.me/15502998");
                    paramsToShare.setImageUrl("http://120.27.138.219:8083/pic/happypays.png");
//                    paramsToShare.setText("创乐付，是江苏付腾电子支付技术有限公司自主研发的一款集线上线下支付于一体的多功能收单app");
                }
                if ("WechatFavorite".equals(platform.getName())){
                    paramsToShare.setImageUrl("http://120.27.138.219:8083/pic/happypays.png");
                    paramsToShare.setTitle("创乐付");
                    paramsToShare.setText("创乐付，是江苏付腾电子支付技术有限公司自主研发的一款集线上线下支付于一体的多功能收单app");
                    paramsToShare.setUrl("http://appurl.me/15502998");
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
            }
        });
        // 启动分享GUI
        oks.show(getActivity());
    }


}
