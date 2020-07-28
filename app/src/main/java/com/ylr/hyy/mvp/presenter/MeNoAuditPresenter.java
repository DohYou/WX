package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeNoAuditContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.MeNoAuditGetCodeModel;
import com.ylr.hyy.mvp.model.MeNoAuditModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeNoAuditPresenter extends RxPresenter<MeNoAuditContract.View> implements MeNoAuditContract.Presenter {
    @Override
    public void meNoAudit(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().meNoAudit(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeNoAuditModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeNoAuditModel model) {
                        mView.meNoAuditSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void meNoAuditGetCode(RequestBody data) {
            Subscription subscription = HttpManager.getHttpService().meNoAuditGetCode(data)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ObserverImp<MeNoAuditGetCodeModel>() {
                        @Override
                        protected void onErr(int errCode, String str) {
                            mView.showError(errCode,str);
                        }

                        @Override
                        protected void doNext(MeNoAuditGetCodeModel model) {
                            mView.meNoAuditGetCodeSus(model);
                        }
                    });
            addSubscribe(subscription);
    }
}
