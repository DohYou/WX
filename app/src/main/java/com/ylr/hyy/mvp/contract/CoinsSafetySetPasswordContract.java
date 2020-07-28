package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.CoinsSafetySetPasswordModel;

import okhttp3.RequestBody;

public interface CoinsSafetySetPasswordContract {
    interface View extends BaseContract.BaseView{
        void coinsSafetySetPasswordSus(CoinsSafetySetPasswordModel model);
        void changePassSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<CoinsSafetySetPasswordContract.View>{
        void coinsSafetySetPassword(RequestBody data);
        void changePass(RequestBody data);
        void findPass(RequestBody data);
    }
}
