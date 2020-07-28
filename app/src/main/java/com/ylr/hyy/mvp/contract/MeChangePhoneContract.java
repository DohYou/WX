package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeChangePhoneModel;

import okhttp3.RequestBody;

public interface MeChangePhoneContract {
    interface View extends BaseContract.BaseView{
        void changeUserPhoneSus(MeChangePhoneModel model);
        void getCodeSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void changeUserPhone(RequestBody data);
        void getCode(RequestBody data);
    }
}
