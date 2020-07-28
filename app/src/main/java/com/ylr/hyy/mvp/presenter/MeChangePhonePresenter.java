package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeChangePhoneContract;
import com.ylr.hyy.mvp.model.MeChangePhoneModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeChangePhonePresenter extends RxPresenter<MeChangePhoneContract.View> implements MeChangePhoneContract.Presenter {
    @Override
    public void changeUserPhone(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().changeUserPhone(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeChangePhoneModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeChangePhoneModel model) {
                        mView.changeUserPhoneSus(model);
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void getCode(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().loginGetCode(data)
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
                        mView.getCodeSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
