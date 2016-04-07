package com.android.pixia.americandramatoolkit.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

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
    private TextView mTimeView;
    private List<UpdateBean> listItems = new ArrayList<>();
    private static BaseActivity mActivity;
    private FrameLayout mFrame;

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
        mFrame = (FrameLayout) findViewById(R.id.timeParent);
        mTimeView = (TextView) findViewById(R.id.text_time);
        mRec = (FastScrollRecyclerView) findViewById(R.id.list_item_update);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mLinear = new LinearLayoutManager(mActivity);
        mRec.setLayoutManager(mLinear);
        mAdapter = new TimeAdapter(listItems,mActivity);
        mRec.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                API.load(AppConfig.UPDATE, mAdapter);
                mSwipe.setRefreshing(false);
            }
        });

        API.load(AppConfig.UPDATE, mAdapter);
        //load(AppConfig.UPDATE, mActivity);
        final ShowBoxTime showBoxTime = new ShowBoxTime(3000,1000);

        mRec.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(mAdapter.getItemCount()>0) {
                    final int fistItem = mLinear.findFirstCompletelyVisibleItemPosition();
                    if(mAdapter.getItem(fistItem).getType()!=null) {
                        showBoxTime.start();
                        mTimeView.setText(mAdapter.getItem(fistItem).getType());
                    }
                }
            }
        });
    }
    /**
     * 隐藏搜索框
     */
    public class ShowBoxTime extends CountDownTimer {

        public ShowBoxTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            if(mFrame.getVisibility() == View.GONE)
                mFrame.startAnimation(AnimationUtils.loadAnimation(mActivity,R.anim.scalbig));
                mFrame.setVisibility(View.VISIBLE);
        }

        @Override
        public void onFinish() {
            mFrame.startAnimation(AnimationUtils.loadAnimation(mActivity,R.anim.scalsmall));
            mFrame.setVisibility(View.GONE);
        }
    }
}
