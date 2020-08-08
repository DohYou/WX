package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface TransferFounderContract {
    interface View extends BaseContract.BaseView{
        void transferFounderSus(Base base);
        void openOrCloseVerifySus(Base base);
        void openOrCloseProhibitSus(Base base);
        void openOrCloseProtectSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<TransferFounderContract.View>{
        void transferFounder(RequestBody data);
        void openOrCloseVerify(RequestBody data);
        void openOrCloseProhibit(RequestBody data);
        void openOrCloseProtect(RequestBody data);

    }
}
