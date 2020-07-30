package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeChangeSexContract;
import com.ylr.hyy.mvp.contract.MomentsContract;
import com.ylr.hyy.mvp.model.MeChangeNameModel;
import com.ylr.hyy.mvp.model.MomentsModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MomentsPresenter extends RxPresenter<MomentsContract.View> implements MomentsContract.Presenter {
    @Override
    public void upMoments(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().upMomentsList(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MomentsModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MomentsModel model) {
                        mView.upMomentsSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
