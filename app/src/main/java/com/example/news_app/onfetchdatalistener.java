package com.example.news_app;

import com.example.news_app.models.newsheadlines;

import java.util.List;

public interface onfetchdatalistener<newsapiresponse> {
    void onfetchdata(List<newsheadlines> list,String message);
    void onerror(String message);
}
