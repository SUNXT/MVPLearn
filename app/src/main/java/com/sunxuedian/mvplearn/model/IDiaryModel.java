package com.sunxuedian.mvplearn.model;

import com.sunxuedian.mvplearn.bean.DiaryBean;
import com.sunxuedian.mvplearn.presenter.callback.IPresenterCallBack;

import java.util.List;

/**
 * Created by sunxuedian on 2017/9/3.
 */

public interface IDiaryModel {
    void addDiary(DiaryBean diaryBean, IPresenterCallBack<String> presenterCallBack);
    void getAllDiaries(IPresenterCallBack<List<DiaryBean>> presenterCallBack);//获取所有日记数据
    boolean isDiaryExist(DiaryBean diaryBean);//判断日记是否存在
}
