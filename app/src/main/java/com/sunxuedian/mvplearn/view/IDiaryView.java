package com.sunxuedian.mvplearn.view;

import com.sunxuedian.mvplearn.bean.DiaryBean;

import java.util.Date;
import java.util.List;

/**
 * Created by sunxuedian on 2017/9/3.
 */

public interface IDiaryView {
    void showLoading();
    void hideLoading();
    String getNewDiaryTitle();
    String getNewDiaryContent();
    Date getNewDiaryCreateTime();
    void showAddDiarySuccess(DiaryBean diaryBean);
    void showDiaries(List<DiaryBean> diaryBeanList);
    void showFailure(String msg);
}
