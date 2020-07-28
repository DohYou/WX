package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.UnfreezeCodeContract;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UnfreezeCodePresenter extends RxPresenter<UnfreezeCodeContract.View> implements UnfreezeCodeContract.Presenter {
    @Override
    public void unFreezeCode(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().freezeCode(body)
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
                        mView.unFreezeCodeSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void unFreeze(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().unfreeze(body)
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
                        mView.unFreezeSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
