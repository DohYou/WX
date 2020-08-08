package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.SetQAdminContract;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SetQAdminPresenter extends RxPresenter<SetQAdminContract.View> implements SetQAdminContract.Presenter {
    @Override
    public void setQAdmin(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().setQAdmin(data)
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
                        mView.setQAdminSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
