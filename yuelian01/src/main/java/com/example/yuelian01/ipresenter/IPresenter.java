package com.example.yuelian01.ipresenter;

import java.util.Map;

public interface IPresenter {
    void startRequest(Map<String,String>map,String path,Class clazz);
}
