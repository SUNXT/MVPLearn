package com.sunxuedian.mvplearn.presenter.callback;

/**
 * Created by sunxuedian on 2017/9/3.
 */

public interface IPresenterCallBack<T> {
    void onSuccess(T data);
    void onFailure(String msg);
}
