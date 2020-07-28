package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.CoinsSafetySetPasswordModel;
import com.ylr.hyy.mvp.model.WXPayModel;
import com.ylr.hyy.mvp.model.ZFBPayModel;

import okhttp3.RequestBody;

public interface ChooseMemberPayContract {
    interface View extends BaseContract.BaseView{
        void wxSus(WXPayModel model);
        void zfbSus(ZFBPayModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void wx(RequestBody body);
        void zfb(RequestBody body);
    }
}
