package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.ForgetCodeChangeModel;
import com.ylr.hyy.mvp.model.ForgetCodeIsCorrectModel;

import okhttp3.RequestBody;

public interface ForgetCodeChangeContract {
    interface View extends BaseContract.BaseView{
        void forgetCodeChangeSus(ForgetCodeChangeModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<ForgetCodeChangeContract.View>{
        void forgetCodeChange(RequestBody data);
    }
}
