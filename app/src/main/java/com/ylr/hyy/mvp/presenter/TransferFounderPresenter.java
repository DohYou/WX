package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.TransferFounderContract;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TransferFounderPresenter extends RxPresenter<TransferFounderContract.View> implements TransferFounderContract.Presenter {
    @Override
    public void transferFounder(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().transferAdmin(data)
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
                        mView.transferFounderSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void openOrCloseVerify(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().openOrCloseVerify(data)
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
                        mView.openOrCloseVerifySus(model);
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void openOrCloseProhibit(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().openOrCloseProhibit(data)
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
                        mView.openOrCloseProhibitSus(model);
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void openOrCloseProtect(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().openOrCloseProtect(data)
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
                        mView.openOrCloseProtectSus(model);
                    }
                });
        addSubscribe(subscription);

    }
}
