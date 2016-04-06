package com.android.pixia.americandramatoolkit.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.pixia.americandramatoolkit.R;

/**
 * Created by pixia on 2016/4/6.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private Toolbar mToolBar;
    public ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        if(getSupportActionBar()!=null){
            mActionBar = getSupportActionBar();
        }

        initView();
    }

    protected abstract void initView();

}
