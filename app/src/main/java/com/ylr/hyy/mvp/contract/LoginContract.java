package com.ylr.hyy.mvp.contract;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.LoginModel;

import okhttp3.RequestBody;

/**
 * 登录契约接口：
 *      1、获取验证码
 *      2、登录
 */

public interface LoginContract {
    interface View extends BaseContract.BaseView{
        void loginSus(LoginModel loginModel);
        void getCodeSus(Base base);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void login(RequestBody data);
        void getCode(RequestBody data);
    }
}
