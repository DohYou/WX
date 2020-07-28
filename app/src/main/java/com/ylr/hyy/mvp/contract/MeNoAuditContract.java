package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.MeNoAuditGetCodeModel;
import com.ylr.hyy.mvp.model.MeNoAuditModel;

import okhttp3.RequestBody;

public interface MeNoAuditContract {
    interface View extends BaseContract.BaseView{
        void meNoAuditSus(MeNoAuditModel model);
        void meNoAuditGetCodeSus(MeNoAuditGetCodeModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<MeNoAuditContract.View>{
        void meNoAudit(RequestBody data);
        void meNoAuditGetCode(RequestBody data);
    }
}
