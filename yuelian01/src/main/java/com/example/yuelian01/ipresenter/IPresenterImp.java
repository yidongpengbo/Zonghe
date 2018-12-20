package com.example.yuelian01.ipresenter;

import com.example.yuelian01.imodel.IModelImp;
import com.example.yuelian01.iview.IView;
import com.example.yuelian01.until.MyCallBack;

import java.util.Map;

public class IPresenterImp implements IPresenter {
        IView mIView;
        IModelImp mIModelImp;

    public IPresenterImp(IView IView) {
        mIView = IView;
        mIModelImp=new IModelImp();
    }

    @Override
    public void startRequest(Map<String, String> map, String path, Class clazz) {
        mIModelImp.requestData(path, map, clazz, new MyCallBack() {
            @Override
            public void getData(Object o) {
                mIView.setData(o);
            }
        });
    }
}
