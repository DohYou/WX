package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeChangeSexContract;
import com.ylr.hyy.mvp.model.MeChangeSexModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeChangeSexPresenter extends RxPresenter<MeChangeSexContract.View> implements MeChangeSexContract.Presenter {
    @Override
    public void changeUserSex(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().changeUserSex(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeChangeSexModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeChangeSexModel model) {
                        mView.changeUserSexSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
