package com.sunxuedian.mvplearn.presenter.impl;

import android.text.TextUtils;

import com.sunxuedian.mvplearn.bean.DiaryBean;
import com.sunxuedian.mvplearn.model.IDiaryModel;
import com.sunxuedian.mvplearn.model.impl.DiaryModelImpl;
import com.sunxuedian.mvplearn.presenter.BasePresenter;
import com.sunxuedian.mvplearn.presenter.IDiaryPresenter;
import com.sunxuedian.mvplearn.presenter.callback.IPresenterCallBack;
import com.sunxuedian.mvplearn.view.IDiaryView;

import java.util.List;

/**
 * Created by sunxuedian on 2017/9/3.
 */

public class DiaryPresenterImpl extends BasePresenter<IDiaryView> implements IDiaryPresenter {

    private IDiaryView mView;
    private IDiaryModel mModel;

    @Override
    public void initPresenter() {
        mView = getView();//调用BasePresenter中的方法获取到View
        mModel = new DiaryModelImpl();
    }

    @Override
    public void addDiary() {
        if (TextUtils.isEmpty(mView.getNewDiaryTitle()) || TextUtils.isEmpty(mView.getNewDiaryContent())){
            mView.showFailure("标题和内容不能为空！");
            return;
        }

        final DiaryBean diaryBean = new DiaryBean();
        diaryBean.setTitle(mView.getNewDiaryTitle());
        diaryBean.setContent(mView.getNewDiaryContent());
        diaryBean.setLastEditTime(mView.getNewDiaryCreateTime());

        mView.showLoading();
        mModel.addDiary(diaryBean, new IPresenterCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                mView.hideLoading();
                mView.showAddDiarySuccess(diaryBean);
            }

            @Override
            public void onFailure(String msg) {
                mView.hideLoading();
                mView.showFailure(msg);
            }
        });
    }

    @Override
    public void getAllDiaries() {
        mView.showLoading();
        mModel.getAllDiaries(new IPresenterCallBack<List<DiaryBean>>() {
            @Override
            public void onSuccess(List<DiaryBean> data) {
                mView.hideLoading();
                if (data != null && data.size() > 0){
                    mView.showDiaries(data);
                }else {
                    mView.showFailure("日记为空！请先添加日记！");
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.hideLoading();
                mView.showFailure(msg);
            }
        });
    }
}
