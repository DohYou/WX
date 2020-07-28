package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;

import okhttp3.RequestBody;

public interface NoFriendContract {
    interface View extends BaseContract.BaseView{
        void addFriendSus(Base model);

    }
    interface Presenter extends BaseContract.BasePresenter<NoFriendContract.View>{
        void addFriend(RequestBody body);
    }

}

