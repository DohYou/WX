package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.GetQAdminContract;
import com.ylr.hyy.mvp.model.DelQAdminModel;
import com.ylr.hyy.mvp.model.GetQAdminModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetQAdminPresenter extends RxPresenter<GetQAdminContract.View> implements GetQAdminContract.Presenter {
    @Override
    public void getQAdmin(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().getQAdmin(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<GetQAdminModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(GetQAdminModel model) {
                        mView.getQAdminSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void delQAdmin(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().delQAdmin(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<DelQAdminModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(DelQAdminModel model) {
                        mView.delQAdminSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
