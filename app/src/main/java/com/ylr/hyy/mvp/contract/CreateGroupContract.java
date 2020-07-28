package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.CreateGroupModel;
import com.ylr.hyy.mvp.model.LabelModel;

import okhttp3.RequestBody;

public interface CreateGroupContract {
    interface View extends BaseContract.BaseView{
        void upLabelListSus(LabelModel model);
        void upAllListSus(CreateGroupModel model);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void upAllList(RequestBody body);
        void upLabelList(RequestBody body);
    }
}
