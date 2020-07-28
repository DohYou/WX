package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface UnfreezeContract {
    interface View extends BaseContract.BaseView{
        void unfreezeSus(Base model);

    }
    interface Presenter extends BaseContract.BasePresenter<UnfreezeContract.View>{
        void unfreeze(RequestBody body);
    }
}
