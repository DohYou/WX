package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.FreezeCodeContract;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FreezeCodePresenter extends RxPresenter<FreezeCodeContract.View> implements FreezeCodeContract.Presenter {
    @Override
    public void freezeCode(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().freezeCode(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<Base>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(Base model) {
                        mView.freezeCodeSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void freeze(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().freeze(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<Base>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(Base model) {
                        mView.freezeSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}