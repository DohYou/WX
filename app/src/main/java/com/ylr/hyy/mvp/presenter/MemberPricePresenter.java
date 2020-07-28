package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MemberPriceContract;
import com.ylr.hyy.mvp.model.MemberPriceModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MemberPricePresenter extends RxPresenter<MemberPriceContract.View> implements MemberPriceContract.Presenter {

    @Override
    public void memberPrice(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().memberPrice(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MemberPriceModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MemberPriceModel model) {
                        mView.memberPriceSus(model);
                    }
                });
        addSubscribe(subscription);

    }

}
