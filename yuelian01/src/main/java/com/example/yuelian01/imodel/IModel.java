package com.example.yuelian01.imodel;

import com.example.yuelian01.until.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String path, Map<String,String>map, Class clazz, MyCallBack myCallBack);
}
