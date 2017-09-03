package com.sunxuedian.mvplearn.model.impl;

import android.os.Handler;
import android.os.Message;

import com.sunxuedian.mvplearn.bean.DiaryBean;
import com.sunxuedian.mvplearn.model.IDiaryModel;
import com.sunxuedian.mvplearn.presenter.callback.IPresenterCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 为了方便演示，此处先将数据保存到内存中，不保存到网络或数据库
 * Created by sunxuedian on 2017/9/3.
 */

public class DiaryModelImpl implements IDiaryModel {

    private ArrayList<DiaryBean> mData = new ArrayList<>();
    private Handler mHandler = new Handler();

    @Override
    public void addDiary(final DiaryBean diaryBean, final IPresenterCallBack<String> presenterCallBack) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //模拟网络请求
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isDiaryExist(diaryBean)){
                            presenterCallBack.onFailure("该标题的日记已经存在！");
                        }else {
                            mData.add(diaryBean);
                            presenterCallBack.onSuccess("添加成功！");
                        }
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public void getAllDiaries(final IPresenterCallBack<List<DiaryBean>> presenterCallBack) {
        //模拟网络请求
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mData != null){
                            presenterCallBack.onSuccess(mData);
                        }else {
                            presenterCallBack.onFailure("系统错误");
                        }
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public boolean isDiaryExist(DiaryBean diaryBean) {
        return mData.contains(diaryBean);
    }

}
