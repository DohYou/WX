package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.FriendModel;
import com.ylr.hyy.mvp.model.MeNoAuditGetCodeModel;
import com.ylr.hyy.mvp.model.MeNoAuditModel;
import com.ylr.hyy.mvp.model.SearchFriendModel;

import okhttp3.RequestBody;

public interface SearchFriendContract {
    interface View extends BaseContract.BaseView{
        void searchFriendSus(SearchFriendModel model);
        void getFriendNewsSus(FriendModel model);
    }
    interface Presenter extends BaseContract.BasePresenter<SearchFriendContract.View>{
        void searchFriend(RequestBody data);
        void getFriendNews(RequestBody body);
    }
}
