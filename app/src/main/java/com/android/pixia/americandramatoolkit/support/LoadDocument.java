package com.android.pixia.americandramatoolkit.support;

import android.content.Context;
import android.os.AsyncTask;

import com.android.pixia.americandramatoolkit.R;
import com.android.pixia.americandramatoolkit.base.OnLoadListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by pixia on 2016/4/6.
 */
public class LoadDocument {

    private OnLoadListener loadListener;
    private static LoadDocument loadDocument;
    private String url;
    private Context context;

    public static synchronized LoadDocument with(String url,Context context){
        if(loadDocument==null){
            synchronized (LoadDocument.class){
                if(loadDocument==null) {
                    loadDocument = new LoadDocument(url,context);
                }
            }
        }
        return loadDocument;
    }

    public LoadDocument(String url,Context context){
        this.url = url;
        this.context = context;
    }

    public void load(OnLoadListener onLoadListener){
        this.loadListener = onLoadListener;
        LoadDocTask task = new LoadDocTask(url);
        task.execute();
    }

    public class LoadDocTask extends AsyncTask<String,Integer,Document>{

        private String url;
        public LoadDocTask(String url){
            this.url = url;
        }
        @Override
        protected Document doInBackground(String... params) {

            try {
                return Jsoup.connect(url).get();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            if(document!=null){
                loadListener.onSuccess(document);
            }else {
                loadListener.onFail(context.getString(R.string.tip_connect_err));
            }
        }
    }
}
