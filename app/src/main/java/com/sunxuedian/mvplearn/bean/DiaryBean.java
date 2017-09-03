package com.sunxuedian.mvplearn.bean;

import java.util.Date;

/**
 * 日记的实体类
 * Created by sunxuedian on 2017/9/3.
 */

public class DiaryBean {

    private String title;
    private String content;
    private Date lastEditTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DiaryBean){
            DiaryBean diaryBean = (DiaryBean) obj;
            return title.equals(diaryBean.getTitle());
        }
        return false;
    }
}
