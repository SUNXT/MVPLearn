package com.sunxuedian.mvplearn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sunxuedian.mvplearn.R;
import com.sunxuedian.mvplearn.bean.DiaryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxuedian on 2017/9/3.
 */

public class DiaryListAdapter extends BaseAdapter {

    private List<DiaryBean> mData = new ArrayList<>();
    private Context mContext;

    public DiaryListAdapter(Context context){
        mContext = context;
    }

    public void setData(List<DiaryBean> list){
        mData.clear();
        mData.addAll(list);
    }

    public void addItem(DiaryBean diaryBean){
        mData.add(diaryBean);
    }

    @Override
    public int getCount() {
        if (mData != null){
            return mData.size();
        }
        return 0;
    }

    @Override
    public DiaryBean getItem(int i) {
        if (mData != null){
            return mData.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.diary_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mTvTitle = view.findViewById(R.id.tv_title);
            viewHolder.mTvContent = view.findViewById(R.id.tv_content);
            viewHolder.mTvTime = view.findViewById(R.id.tv_time);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DiaryBean item = getItem(pos);
        if (item != null){
            viewHolder.mTvTitle.setText(item.getTitle());
            viewHolder.mTvContent.setText(item.getContent());
            viewHolder.mTvTime.setText(item.getLastEditTime().toString());
        }
        return view;
    }

    private class ViewHolder{
        TextView mTvTitle;
        TextView mTvContent;
        TextView mTvTime;
    }
}
