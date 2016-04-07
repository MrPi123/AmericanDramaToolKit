package com.android.pixia.americandramatoolkit.base;

import com.android.pixia.americandramatoolkit.bean.UpdateBean;

import java.util.List;

/**
 * Created by pixia on 2016/4/7.
 */
public interface OnParserListener {

    void onSuccess(List<UpdateBean> list);
    void onFail(String msg);

}
