package com.android.pixia.americandramatoolkit.bean;

/**
 * Created by pixia on 2016/4/6.
 */
public class UpdateBean {

    private String picUrl;
    private String title;
    private String status;
    private String time;
    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
