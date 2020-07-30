package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface SendMomentContract {
    interface View extends BaseContract.BaseView{
        void sendMomentSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void sendMoment(RequestBody data);
    }
}
