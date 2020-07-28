package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.CoinsSafetySetPasswordContract;
import com.ylr.hyy.mvp.model.CoinsSafetySetPasswordModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CoinsSafetySetPasswordPresenter extends RxPresenter<CoinsSafetySetPasswordContract.View> implements CoinsSafetySetPasswordContract.Presenter {
    @Override
    public void coinsSafetySetPassword(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().coinsSafetySetPassword(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<CoinsSafetySetPasswordModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(CoinsSafetySetPasswordModel model) {
                        mView.coinsSafetySetPasswordSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void changePass(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().changePayPass(data)
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
                        mView.changePassSus(base);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void findPass(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().findPass(data)
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
                        mView.changePassSus(base);
                    }
                });
        addSubscribe(subscription);
    }
}
