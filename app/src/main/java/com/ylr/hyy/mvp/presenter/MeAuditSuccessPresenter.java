package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.MeAuditSuccessContract;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeAuditSuccessPresenter extends RxPresenter<MeAuditSuccessContract.View> implements MeAuditSuccessContract.Presenter {
    @Override
    public void meAuditSuccess(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().upVoucher(body)
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
                        mView.meAuditSuccessSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getMyBankCard(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().getMyBankCard(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<GetMyBankCardModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode, str);
                    }

                    @Override
                    protected void doNext(GetMyBankCardModel model) {
                        mView.getMyBankCardSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
