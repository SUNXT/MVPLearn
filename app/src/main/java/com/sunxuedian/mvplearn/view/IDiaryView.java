package com.sunxuedian.mvplearn.view;

import com.sunxuedian.mvplearn.bean.DiaryBean;

import java.util.Date;
import java.util.List;

/**
 * Created by sunxuedian on 2017/9/3.
 */

public interface IDiaryView {
    void showLoading();//显示进度条
    void hideLoading();//隐藏进度条
    String getNewDiaryTitle();//获取新建日记的标题
    String getNewDiaryContent();//获取日记内容
    Date getNewDiaryCreateTime();//获取日记的创建时间
    void showAddDiarySuccess(DiaryBean diaryBean);//显示添加日记成功
    void showDiaries(List<DiaryBean> diaryBeanList);//显示日记列表
    void showFailure(String msg);//显示操作失败信息
}
