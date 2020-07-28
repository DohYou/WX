package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.AddBankCardModel;
import com.ylr.hyy.mvp.model.ForgetCodeIsCorrectModel;

import okhttp3.RequestBody;

public interface ForgetCodeIsCorrectContract {
    interface View extends BaseContract.BaseView{
        void ForgetCodeIsCorrectSus(ForgetCodeIsCorrectModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<ForgetCodeIsCorrectContract.View>{
        void ForgetCodeIsCorrect(RequestBody data);
    }
}
