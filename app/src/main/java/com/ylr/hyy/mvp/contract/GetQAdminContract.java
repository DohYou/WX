package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.DelQAdminModel;
import com.ylr.hyy.mvp.model.GetQAdminModel;

import okhttp3.RequestBody;

public interface GetQAdminContract {
    interface View extends BaseContract.BaseView{
        void getQAdminSus(GetQAdminModel model);
        void delQAdminSus(DelQAdminModel model);

    }
    interface Presenter extends BaseContract.BasePresenter<GetQAdminContract.View>{
        void getQAdmin(RequestBody data);
        void delQAdmin(RequestBody data);
    }
}
