package com.listenergao.recyclerviewdemo.bean;

public class MultipleItemBean {


    public int resType;
    public int resType1;
    public String content;
    public String content1;
    public int displayType;

    public MultipleItemBean(int resType, String content, int displayType) {
        this.resType = resType;
        this.content = content;
        this.displayType = displayType;
    }

    public MultipleItemBean(int resType, int resType1, String content, String content1, int displayType) {
        this.resType = resType;
        this.resType1 = resType1;
        this.content = content;
        this.content1 = content1;
        this.displayType = displayType;
    }
}
