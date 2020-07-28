package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.HeadImageContract;
import com.ylr.hyy.mvp.contract.MeAuditSuccessContract;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.QiNiuModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HeadImagePresenter extends RxPresenter<HeadImageContract.View> implements HeadImageContract.Presenter {

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
