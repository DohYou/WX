package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;
import com.ylr.hyy.mvp.model.MeChangeHJNumberModel;

import okhttp3.RequestBody;

public interface GetMyBankCardContract {
    interface View extends BaseContract.BaseView{
        void getMyBankCardSus(GetMyBankCardModel model);
        void deleteBankSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<GetMyBankCardContract.View>{
        void getMyBankCard(RequestBody data);
        void deleteBank(RequestBody body);
    }
}
