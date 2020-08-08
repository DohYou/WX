package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.DelAndLeaveContract;
import com.ylr.hyy.mvp.model.CreateGroupModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DelAndLeavePresenter extends RxPresenter<DelAndLeaveContract.View> implements DelAndLeaveContract.Presenter {

    @Override
    public void delAndLeave(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().delAndLeave(body)
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
                        mView.delAndLeaveSus(base);
                    }
                });
        addSubscribe(subscription);
    }
}
