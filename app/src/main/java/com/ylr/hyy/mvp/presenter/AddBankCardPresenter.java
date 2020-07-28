package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.AddBankCardContract;
import com.ylr.hyy.mvp.model.AddBankCardModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddBankCardPresenter extends RxPresenter<AddBankCardContract.View> implements AddBankCardContract.Presenter {
    @Override
    public void addBankCard(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().addBankCard(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<AddBankCardModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode, str);
                    }

                    @Override
                    protected void doNext(AddBankCardModel model) {
                        mView.addBankCardSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void bankCode(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().bankCode(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<Base>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(Base base) {
                        mView.bankCodeSus(base);
                    }
                });
        addSubscribe(subscription);
    }
}

