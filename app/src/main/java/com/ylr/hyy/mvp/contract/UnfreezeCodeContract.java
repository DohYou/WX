package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface UnfreezeCodeContract {
    interface View extends BaseContract.BaseView{
        void unFreezeCodeSus(Base model);
        void unFreezeSus(Base model);

    }
    interface Presenter extends BaseContract.BasePresenter<UnfreezeCodeContract.View>{
        void unFreezeCode(RequestBody body);
        void unFreeze(RequestBody body);
    }
}
