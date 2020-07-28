package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface FreezeContract {
    interface View extends BaseContract.BaseView{
        void freezeSus(Base model);

    }
    interface Presenter extends BaseContract.BasePresenter<FreezeContract.View>{
        void freeze(RequestBody body);
    }
}
