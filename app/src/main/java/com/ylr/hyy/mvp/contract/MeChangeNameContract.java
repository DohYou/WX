package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeChangeNameModel;

import okhttp3.RequestBody;

public interface MeChangeNameContract {
    interface View extends BaseContract.BaseView{
        void changeUserNameSus(MeChangeNameModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void changeUserName(RequestBody data);
    }
}
