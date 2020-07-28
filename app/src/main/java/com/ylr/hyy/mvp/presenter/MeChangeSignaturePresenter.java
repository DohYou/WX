package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeChangeSignatureContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.MeChangeSignatureModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeChangeSignaturePresenter extends RxPresenter<MeChangeSignatureContract.View> implements MeChangeSignatureContract.Presenter  {
    @Override
    public void meChangeSignature(RequestBody data) {
            Subscription subscription = HttpManager.getHttpService().meChangeSignature(data)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ObserverImp<MeChangeSignatureModel>() {
                        @Override
                        protected void onErr(int errCode, String str) {
                            mView.showError(errCode,str);
                        }

                        @Override
                        protected void doNext(MeChangeSignatureModel model) {
                            mView.meChangeSignatureSus(model);
                        }
                    });
            addSubscribe(subscription);
        }

}
