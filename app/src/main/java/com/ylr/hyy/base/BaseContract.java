package com.ylr.hyy.base;


public interface BaseContract {
    interface BasePresenter<T> {
        void attachView(T view);
        void detachView();
    }

    interface BaseView {
        void showError(int code, String message);
    }
}
