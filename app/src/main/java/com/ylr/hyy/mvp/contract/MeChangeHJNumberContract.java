package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeChangeHJNumberModel;

import okhttp3.RequestBody;

public interface MeChangeHJNumberContract {
    interface View extends BaseContract.BaseView{
        void changeUserHJNumberSus(MeChangeHJNumberModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void changeUserHJNumber(RequestBody data);
    }
}
