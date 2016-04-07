package com.android.pixia.americandramatoolkit.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by pixia on 2016/4/6.
 *
 */
public class RequestUtil extends Thread {

    public Handler mHandler;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        Looper.loop();
    }

}
