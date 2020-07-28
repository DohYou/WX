package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.AddBankCardModel;

import okhttp3.RequestBody;

public interface AddBankCardContract {
    interface View extends BaseContract.BaseView{
        void addBankCardSus(AddBankCardModel model);
        void bankCodeSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<AddBankCardContract.View>{
        void addBankCard(RequestBody data);
        void bankCode(RequestBody data);
    }
}
