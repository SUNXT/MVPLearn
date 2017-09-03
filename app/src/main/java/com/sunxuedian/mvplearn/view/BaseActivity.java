package com.sunxuedian.mvplearn.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunxuedian.mvplearn.presenter.BasePresenter;

/**
 * Created by sunxuedian on 2017/9/3.
 */
public abstract class BaseActivity<View, P extends BasePresenter<View>> extends Activity{

    protected P mPresenter;//Presenter对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        mPresenter.attachView((View)this);//View和Presenter建立关联
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();//解除View和Presenter的关联
    }

    public abstract P createPresenter();
}
