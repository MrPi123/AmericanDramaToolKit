package com.android.pixia.americandramatoolkit.support;

import com.android.pixia.americandramatoolkit.AppConfig;
import com.android.pixia.americandramatoolkit.adapter.TimeAdapter;
import com.android.pixia.americandramatoolkit.base.OnParserListener;
import com.android.pixia.americandramatoolkit.bean.UpdateBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by pixia on 2016/4/6.
 * 获取美剧贩资源API
 */
public class API {

    //获取每日更新美剧
    public static void load(final String url, final TimeAdapter adapter, final int action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document= null;
                try {
                    document = Jsoup.connect(url).get();
                    ParserHtmlListener.with(document)
                            .parser(new OnParserListener() {
                                @Override
                                public void onSuccess(List<UpdateBean> list) {
                                    if(action== AppConfig.LOAD_REFRESH){
                                        adapter.notifyDataSetChanged();
                                    }else {
                                        adapter.init(list);
                                    }
                                }

                                @Override
                                public void onFail(String msg) {

                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
