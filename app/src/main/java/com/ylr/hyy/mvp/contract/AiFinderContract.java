package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.AiNewsModel;
import okhttp3.RequestBody;

public interface AiFinderContract {
    interface View extends BaseContract.BaseView{
        void upAiNewsSus(AiNewsModel model);
        void switchAiSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void upAiNews(RequestBody data);
        void switchAi(RequestBody data);
        void add(RequestBody body);}
}
