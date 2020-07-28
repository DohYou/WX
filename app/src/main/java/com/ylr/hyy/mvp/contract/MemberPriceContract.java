package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.MemberPriceModel;

import okhttp3.RequestBody;

public interface MemberPriceContract {
    interface View extends BaseContract.BaseView{
        void memberPriceSus(MemberPriceModel model);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void memberPrice(RequestBody data);

    }
}
