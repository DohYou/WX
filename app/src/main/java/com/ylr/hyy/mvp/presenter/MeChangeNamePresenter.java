package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeChangeNameContract;
import com.ylr.hyy.mvp.model.MeChangeNameModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeChangeNamePresenter extends RxPresenter<MeChangeNameContract.View> implements MeChangeNameContract.Presenter {
    @Override
    public void changeUserName(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().changeUserName(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeChangeNameModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeChangeNameModel model) {
                        mView.changeUserNameSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
