package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.LoginModel;

import okhttp3.RequestBody;

public interface RegisterContract {
    interface View extends BaseContract.BaseView{
        void registerUserSus(Base registerUserModel);
        void getCodeSus(Base base);
        void loginSus(LoginModel loginModel);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void registerUser(RequestBody data);
        void getCode(RequestBody data);
        void login(RequestBody data);
    }
}
