package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MomentsModel;

import okhttp3.RequestBody;

public interface MomentsContract {
    interface View extends BaseContract.BaseView{
        void upMomentsSus(MomentsModel momentsModel);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void upMoments(RequestBody data);
    }
}
