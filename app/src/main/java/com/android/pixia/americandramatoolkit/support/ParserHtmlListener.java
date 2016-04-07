package com.android.pixia.americandramatoolkit.support;

import android.os.AsyncTask;

import com.android.pixia.americandramatoolkit.base.OnLoadListener;
import com.android.pixia.americandramatoolkit.base.OnParserListener;
import com.android.pixia.americandramatoolkit.bean.UpdateBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pixia on 2016/4/7.
 */
public class ParserHtmlListener {

    private static Document document;
    private static ParserHtmlListener listener;
    private OnParserListener parserListener;
    private List<UpdateBean> list = new ArrayList<>();
    private static String time;

    public static synchronized ParserHtmlListener with(Document document){
        if(listener==null){
            synchronized (ParserHtmlListener.class){
                if(listener==null){
                    listener = new ParserHtmlListener(document);
                    return listener;
                }else {
                    return listener;
                }
            }
        }
        return listener;
    }

    public ParserHtmlListener(Document document){
        ParserHtmlListener.document = document;
    }

    public void parser(OnParserListener onParserListener){
        parserListener = onParserListener;
        ParserTask task = new ParserTask();
        task.execute();
    }

    public class ParserTask extends AsyncTask<String,Integer,List<UpdateBean>>{

        @Override
        protected List<UpdateBean> doInBackground(String... params) {
            List<String> urls = new ArrayList<>();
            Elements tableElement = document.getElementsByTag("tbody");
            for (Element element:tableElement){
                Elements trElements = element.getElementsByTag("tr");

                for (int i=0;i<trElements.size();i++){
                    UpdateBean bean = new UpdateBean();
                    if(i==0){
                        bean.setType(trElements.get(i).text());
                        time = trElements.get(i).text();
                    }

                    bean.setTime(time.replace(" 剧集名称 电视网 集数 本集名称", "")
                            .replace("美剧贩","")
                            .replace("导视"," "));
                    Elements urlEls = trElements.get(i).getElementsByTag("a");
                    Elements imgEls = trElements.get(i).getElementsByTag("img");
                    for (Element imgs:imgEls){

                        bean.setPicUrl(imgs.attr("src"));
                    }
                    for (Element el:urlEls){
                        bean.setTitle(el.text());
                    }
//                    for(UpdateBean bean1:list){
//                        System.out.println(bean1.getType());
//                        System.out.println(bean1.getPicUrl());
//                        System.out.println(bean1.getTitle());
//
//                    }
                    list.add(bean);
//                    System.out.println(trElements.text());
                }

            }

            return list;
        }

        @Override
        protected void onPostExecute(List<UpdateBean> list) {
            super.onPostExecute(list);
            parserListener.onSuccess(list);
        }
    }

}
