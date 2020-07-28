package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeDetailsModel;

import okhttp3.RequestBody;

public interface MeDetailsContract {
    interface View extends BaseContract.BaseView{
        void upMeInfoSus(MeDetailsModel model);
        void commitAddressSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void upMeInfo(RequestBody body);
        void commitAddress(RequestBody body);
    }
}
