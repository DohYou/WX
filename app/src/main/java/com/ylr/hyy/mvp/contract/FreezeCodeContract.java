package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface FreezeCodeContract {
    interface View extends BaseContract.BaseView{
        void freezeCodeSus(Base model);
        void freezeSus(Base model);

    }
    interface Presenter extends BaseContract.BasePresenter<FreezeCodeContract.View>{
        void freezeCode(RequestBody body);
        void freeze(RequestBody body);
    }
}
