package com.sunxuedian.mvplearn.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 对presenter进行抽象
 * 提供View和Presenter进行绑定的操作，使得Presenter对View进行弱引用，避免发生内存泄漏
 * Created by sunxuedian on 2017/9/3.
 */

public abstract class BasePresenter<T> {

    private Reference<T> mViewRef;

    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);//建立关联
    }

    protected T getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get()!= null;
    }

    public void detachView(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
