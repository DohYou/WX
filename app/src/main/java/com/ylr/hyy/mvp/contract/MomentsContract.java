package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeDetailsModel;
import com.ylr.hyy.mvp.model.MomentsModel;

import okhttp3.RequestBody;

public interface MomentsContract {
    interface View extends BaseContract.BaseView{
        void upMomentsSus(MomentsModel momentsModel);
        void defaultBack(Base base);
        void upMeInfoSus(MeDetailsModel meDetailsModel);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void upMoments(RequestBody data);
        void reply(RequestBody body);
        void fabulous(RequestBody body);
        void upMeInfo(RequestBody body);
    }
}
