package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.GetMyBankCardContract;
import com.ylr.hyy.mvp.contract.MainContract;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    @Override
    public void isVoucher(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().upVoucher(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<MeAuditSuccessModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(MeAuditSuccessModel model) {
                        mView.getIsVoucher(model);
                    }
                });
        addSubscribe(subscription);
    }
}
