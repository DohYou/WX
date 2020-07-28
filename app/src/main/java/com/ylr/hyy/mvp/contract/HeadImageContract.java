package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;


import okhttp3.RequestBody;

public interface HeadImageContract {
    interface View extends BaseContract.BaseView{
        void changeUserHeaderSus(MeChangeHeaderModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void changeUserHeader(RequestBody data);
    }
}
