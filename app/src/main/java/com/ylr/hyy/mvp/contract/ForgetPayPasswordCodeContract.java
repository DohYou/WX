package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.ForgetCodeIsCorrectModel;
import com.ylr.hyy.mvp.model.ForgetPayPasswordCodeModel;

import okhttp3.RequestBody;

public interface ForgetPayPasswordCodeContract {
    interface View extends BaseContract.BaseView{
        void forgetPayPasswordCodeSus(ForgetPayPasswordCodeModel model);
        void ForgetCodeIsCorrectSus(ForgetCodeIsCorrectModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<ForgetPayPasswordCodeContract.View>{
        void forgetPayPasswordCode(RequestBody data);
        void ForgetCodeIsCorrect(RequestBody data);
    }
}
