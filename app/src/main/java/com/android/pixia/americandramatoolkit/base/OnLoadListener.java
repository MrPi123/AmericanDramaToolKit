package com.android.pixia.americandramatoolkit.base;

import org.jsoup.nodes.Document;

/**
 * Created by pixia on 2016/4/6.
 *
 */

public interface OnLoadListener {

    void onSuccess(Document document);
    void onFail(String err);

}
