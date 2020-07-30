package com.ylr.hyy;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.tencent.bugly.Bugly;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

public class MVPApplication extends Application {
    public static Context mContext;
    public static final int SDK_APP_ID = 1400381663; // 腾讯im SDKAppID
    public static final String HttpType = "application/json; charset=utf-8";
    public static final String WXAPP_ID = "wx62c5c547a24f12d2";//微信支付
    public static final String IM_SDK = "TXIM";//区分腾讯im日志
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //如果不需要阿里热修复  注释这行   并且 在AndroidManifest切换Application！
//        SophixManager.getInstance().queryAndLoadNewPatch();
        //bugly初始化
//        CrashReport.initCrashReport(getApplicationContext(),"e425870bea",false);
        //腾讯应用升级初始化   如果你只需要使用bugly那么注释这句话并且在build.gradle修改依赖，如果你需要使用应用升级功能，注释bugly初始化代码，使用这行代码统一初始化
        //这个是APP更新升级   阿里是热修复  所以它们之间不会冲突
        Bugly.init(getApplicationContext(), "e425870bea", false);
        //工具类
        Utils.init(this);
        SPUtils.getInstance().put("tokenExist",false);

        //初始化腾讯im
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new TIMSdkConfig(SDK_APP_ID));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());

        TUIKit.init(this, SDK_APP_ID, configs);

        //夜间模式
        SPUtils.getInstance().put("isNight",false);
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
