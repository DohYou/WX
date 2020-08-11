package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.UpGroupDetailsModel;

import okhttp3.RequestBody;

public interface UpGroupDetailsContract {
    interface View extends BaseContract.BaseView{
        void upGroupDetailsSus(UpGroupDetailsModel model);
    }

    interface Presenter extends BaseContract.BasePresenter<UpGroupDetailsContract.View>{
        void upGroupDetails(RequestBody body);
    }
}
