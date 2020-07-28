package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.ForgetPayPasswordCodeContract;
import com.ylr.hyy.mvp.model.ForgetCodeIsCorrectModel;
import com.ylr.hyy.mvp.model.ForgetPayPasswordCodeModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgetPayPasswordCodePresenter extends RxPresenter<ForgetPayPasswordCodeContract.View> implements ForgetPayPasswordCodeContract.Presenter {
    @Override
    public void forgetPayPasswordCode(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().forgetPayPasswordCode(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<ForgetPayPasswordCodeModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(ForgetPayPasswordCodeModel model) {
                        mView.forgetPayPasswordCodeSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void ForgetCodeIsCorrect(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().forgetCodeIsCorrect(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<ForgetCodeIsCorrectModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(ForgetCodeIsCorrectModel model) {
                        mView.ForgetCodeIsCorrectSus(model);
                    }
                });
        addSubscribe(subscription);
    }

}
