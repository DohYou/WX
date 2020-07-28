package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.GetMyBankCardContract;
import com.ylr.hyy.mvp.model.AddBankCardModel;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetMyBankCardPresenter extends RxPresenter<GetMyBankCardContract.View> implements GetMyBankCardContract.Presenter {
    @Override
    public void getMyBankCard(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().getMyBankCard(data)
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

    @Override
    public void deleteBank(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().deleteBank(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<Base>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode, str);
                    }

                    @Override
                    protected void doNext(Base base) {
                        mView.deleteBankSus(base);
                    }
                });
        addSubscribe(subscription);
    }
}
