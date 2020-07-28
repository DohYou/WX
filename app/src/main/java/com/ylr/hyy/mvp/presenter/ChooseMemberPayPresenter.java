package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.ChooseMemberPayContract;
import com.ylr.hyy.mvp.contract.HeadImageContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.WXPayModel;
import com.ylr.hyy.mvp.model.ZFBPayModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChooseMemberPayPresenter extends RxPresenter<ChooseMemberPayContract.View> implements ChooseMemberPayContract.Presenter {
    @Override
    public void wx(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().wxPay(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<WXPayModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(WXPayModel model) {
                        mView.wxSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void zfb(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().zfbPay(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<ZFBPayModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(ZFBPayModel model) {
                        mView.zfbSus(model);
                    }
                });
        addSubscribe(subscription);

    }
}
