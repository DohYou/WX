package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeChangeSexModel;

import okhttp3.RequestBody;

public interface MeChangeSexContract {
    interface View extends BaseContract.BaseView{
        void changeUserSexSus(MeChangeSexModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void changeUserSex(RequestBody data);
}
}
