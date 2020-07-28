package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeChangeHJNumberContract;
import com.ylr.hyy.mvp.model.MeChangeHJNumberModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeChangeHJNumberPresenter extends RxPresenter<MeChangeHJNumberContract.View> implements MeChangeHJNumberContract.Presenter {

    @Override
    public void changeUserHJNumber(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().changeUserHJNumber(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeChangeHJNumberModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeChangeHJNumberModel model) {
                        mView.changeUserHJNumberSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
