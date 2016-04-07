package com.android.pixia.americandramatoolkit.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pixia.americandramatoolkit.AppConfig;
import com.android.pixia.americandramatoolkit.R;
import com.android.pixia.americandramatoolkit.adapter.TimeAdapter;
import com.android.pixia.americandramatoolkit.base.BaseActivity;
import com.android.pixia.americandramatoolkit.base.BaseFragment;
import com.android.pixia.americandramatoolkit.bean.UpdateBean;
import com.android.pixia.americandramatoolkit.support.API;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class UpdateFragment extends BaseFragment {


    private FastScrollRecyclerView mRec;
    private SwipeRefreshLayout mSwipe;
    private TimeAdapter mAdapter;
    private LinearLayoutManager mLinear;
    private List<UpdateBean> listItems = new ArrayList<>();

    private static BaseActivity mActivity;

    public static UpdateFragment newInstance(BaseActivity activity) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        mActivity = activity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_update, container, false);
        initView();
        mActivity.mActionBar.setTitle("更新时间表");
        return mView;
    }

    private void initView(){

        mRec = (FastScrollRecyclerView) findViewById(R.id.list_item_update);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mLinear = new LinearLayoutManager(mActivity);
        mRec.setLayoutManager(mLinear);
        mAdapter = new TimeAdapter(listItems,mActivity);
        mRec.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                API.load(AppConfig.UPDATE,mAdapter,AppConfig.LOAD_REFRESH);
                mSwipe.setRefreshing(false);
            }
        });

        API.load(AppConfig.UPDATE, mAdapter,AppConfig.LOAD_INIT);
        //load(AppConfig.UPDATE, mActivity);
    }
}
