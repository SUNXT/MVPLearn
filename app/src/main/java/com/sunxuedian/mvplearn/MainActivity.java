package com.sunxuedian.mvplearn;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sunxuedian.mvplearn.adapter.DiaryListAdapter;
import com.sunxuedian.mvplearn.bean.DiaryBean;
import com.sunxuedian.mvplearn.presenter.IDiaryPresenter;
import com.sunxuedian.mvplearn.presenter.impl.DiaryPresenterImpl;
import com.sunxuedian.mvplearn.view.BaseActivity;
import com.sunxuedian.mvplearn.view.IDiaryView;

import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity<IDiaryView, DiaryPresenterImpl> implements IDiaryView {

    private DiaryListAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    private EditText mEtTitle;
    private EditText mEtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mPresenter.initPresenter();//初始化presenter
        mPresenter.getAllDiaries();//调用presenter获取日记列表
    }

    private void initView(){
        //为按钮添加点击事件
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addDiary();//调用presenter进行添加日记
            }
        });
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getAllDiaries();//获取日记列表
            }
        });

        //初始化编辑框
        mEtTitle = findViewById(R.id.et_title);
        mEtContent = findViewById(R.id.et_content);

        //为listView绑定adapter
        mAdapter = new DiaryListAdapter(this);
        ListView listView = findViewById(R.id.lv_show);
        listView.setAdapter(mAdapter);

        //初始化进度条对话框
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(null);
        mProgressDialog.setMessage("进行中...");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public DiaryPresenterImpl createPresenter() {
        return new DiaryPresenterImpl();
    }

    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public String getNewDiaryTitle() {
        return mEtTitle.getText().toString().trim();
    }

    @Override
    public String getNewDiaryContent() {
        return mEtContent.getText().toString().trim();
    }

    @Override
    public Date getNewDiaryCreateTime() {
        return new Date(System.currentTimeMillis());
    }

    @Override
    public void showAddDiarySuccess(DiaryBean diaryBean) {
        showToast("添加成功！");
        //将新增的日记显示在列表中
        mAdapter.addItem(diaryBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDiaries(List<DiaryBean> diaryBeanList) {
        //显示获取到的列表
        mAdapter.setData(diaryBeanList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFailure(String msg) {
        showToast(msg);
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
