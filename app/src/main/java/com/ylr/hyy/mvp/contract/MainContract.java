package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.CoinsSafetySetPasswordModel;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;

import okhttp3.RequestBody;

public interface MainContract {
    interface View extends BaseContract.BaseView{
        void getIsVoucher(MeAuditSuccessModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void isVoucher(RequestBody data);
    }
}
