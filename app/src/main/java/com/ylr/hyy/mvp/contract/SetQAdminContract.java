package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface SetQAdminContract {
    interface View extends BaseContract.BaseView{
        void setQAdminSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<SetQAdminContract.View>{
        void setQAdmin(RequestBody data);
    }
}
