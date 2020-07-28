package com.ylr.hyy.mvp.presenter;

import com.ylr.hyy.http.HttpManager;
import com.ylr.hyy.http.ObserverImp;
import com.ylr.hyy.http.RxPresenter;
import com.ylr.hyy.mvp.contract.ChooseMemberPayContract;
import com.ylr.hyy.mvp.contract.CreateGroupContract;
import com.ylr.hyy.mvp.model.CreateGroupModel;
import com.ylr.hyy.mvp.model.LabelModel;
import com.ylr.hyy.mvp.model.WXPayModel;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateGroupPresenter extends RxPresenter<CreateGroupContract.View> implements CreateGroupContract.Presenter {
    @Override
    public void upAllList(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().upGroupList(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<CreateGroupModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(CreateGroupModel model) {
                        mView.upAllListSus(model);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void upLabelList(RequestBody body) {
        Subscription subscription = HttpManager.getHttpService().upLabelList(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImp<LabelModel>() {
                    @Override
                    protected void onErr(int errCode, String str) {
                        mView.showError(errCode,str);
                    }

                    @Override
                    protected void doNext(LabelModel model) {
                        mView.upLabelListSus(model);
                    }
                });
        addSubscribe(subscription);
    }
}
