package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeChangeHeaderContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeChangeHeaderPresenter extends RxPresenter<MeChangeHeaderContract.View> implements MeChangeHeaderContract.Presenter {
    @Override
    public void changeUserHeader(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().userHeader(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeChangeHeaderModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeChangeHeaderModel model) {
                        mView.changeUserHeaderSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
