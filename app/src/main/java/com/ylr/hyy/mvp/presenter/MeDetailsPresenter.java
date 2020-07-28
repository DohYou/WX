package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeDetailsContract;
import com.ylr.hyy.mvp.model.MeDetailsModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeDetailsPresenter extends RxPresenter<MeDetailsContract.View> implements MeDetailsContract.Presenter {
    @Override
    public void upMeInfo(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().upMeInfo(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeDetailsModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeDetailsModel model) {
                        mView.upMeInfoSus(model);
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void commitAddress(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().commitAddress(body)
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
                        mView.commitAddressSus(base);
                    }
                });
        addSubscribe(subscription);
    }
}
