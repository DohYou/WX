package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.NoFriendContract;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NoFriendPresenter extends RxPresenter<NoFriendContract.View> implements NoFriendContract.Presenter {
    @Override
    public void addFriend(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().addFriend(body)
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
                        mView.addFriendSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
