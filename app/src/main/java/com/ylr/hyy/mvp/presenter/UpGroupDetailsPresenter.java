package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.UpGroupDetailsContract;
import com.ylr.hyy.mvp.model.UpGroupDetailsModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpGroupDetailsPresenter extends RxPresenter<UpGroupDetailsContract.View> implements UpGroupDetailsContract.Presenter{
    @Override
    public void upGroupDetails(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().upGroupDetails(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<UpGroupDetailsModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(UpGroupDetailsModel model) {
                        mView.upGroupDetailsSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
