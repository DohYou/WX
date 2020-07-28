package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.SearchFriendContract;
import com.ylr.hyy.mvp.model.FriendModel;
import com.ylr.hyy.mvp.model.SearchFriendModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchFriendPresenter extends RxPresenter<SearchFriendContract.View> implements SearchFriendContract.Presenter {
    @Override
    public void searchFriend(RequestBody data) {
        Subscription subscription = HttpManager.getHttpService().searchFriend(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<SearchFriendModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(SearchFriendModel model) {
                        mView.searchFriendSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getFriendNews(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().getFriendNews(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<FriendModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(FriendModel model) {
                        mView.getFriendNewsSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
