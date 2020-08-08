package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.CreateGroupModel;
import com.ylr.hyy.mvp.model.LabelModel;

import okhttp3.RequestBody;

public interface DelAndLeaveContract {
    interface View extends BaseContract.BaseView{
        void delAndLeaveSus(Base base);
    }

    interface Presenter extends BaseContract.BasePresenter<DelAndLeaveContract.View>{
        void delAndLeave(RequestBody body);
    }
}
