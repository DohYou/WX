package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.MeChangeSexModel;
import com.ylr.hyy.mvp.model.MeChangeSignatureModel;

import okhttp3.RequestBody;

public interface MeChangeSignatureContract {
    interface View extends BaseContract.BaseView{
        void meChangeSignatureSus(MeChangeSignatureModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<MeChangeSignatureContract.View>{
        void meChangeSignature(RequestBody data);
    }
}
