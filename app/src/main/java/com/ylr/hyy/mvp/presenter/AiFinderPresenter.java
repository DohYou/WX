package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.AiFinderContract;
import com.ylr.hyy.mvp.model.AiNewsModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AiFinderPresenter extends RxPresenter<AiFinderContract.View> implements AiFinderContract.Presenter {
    @Override
    public void upAiNews(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().upAi(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<AiNewsModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(AiNewsModel model) {
                        mView.upAiNewsSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void switchAi(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().switchAi(data)
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
                        mView.switchAiSus(base);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void add(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().addAi(body)
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
                        mView.switchAiSus(base);
                    }
                });
        addSubscribe(subscription);
    }
}
