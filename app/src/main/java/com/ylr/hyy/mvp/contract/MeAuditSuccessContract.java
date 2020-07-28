package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;

import okhttp3.RequestBody;

public interface MeAuditSuccessContract {
    interface View extends BaseContract.BaseView{
        void meAuditSuccessSus(MeAuditSuccessModel model);
        void getMyBankCardSus(GetMyBankCardModel model);

    }
    interface Presenter extends BaseContract.BasePresenter<MeAuditSuccessContract.View>{
        void meAuditSuccess(RequestBody body);
        void getMyBankCard(RequestBody body);
    }
}
