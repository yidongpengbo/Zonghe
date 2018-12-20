package com.example.yuelian01.imodel;

import com.example.yuelian01.Bean.AddBean;
import com.example.yuelian01.Bean.GouBean;
import com.example.yuelian01.Bean.PhoneBean;
import com.example.yuelian01.until.MyCallBack;
import com.example.yuelian01.until.OKHttpUntil;

import java.util.Map;

public class IModelImp implements IModel {
    MyCallBack mMyCallBack;
    @Override
    public void requestData(final String path, Map<String, String> map, Class clazz, MyCallBack myCallBack) {
                mMyCallBack=myCallBack;
        OKHttpUntil.getInstance().postEnque(map, path, clazz, new OKHttpUntil.CallBack() {
            @Override
            public void fail(Exception e) {

            }

            @Override
            public void success(Object o) {
                    if (o instanceof PhoneBean){
                        PhoneBean phoneBean=(PhoneBean)o;
                        mMyCallBack.getData(phoneBean);
                    }else if(o instanceof AddBean){
                        AddBean addBean=(AddBean)o;
                        mMyCallBack.getData(addBean);
                    }else if (o instanceof GouBean){
                        GouBean gouBean=(GouBean)o;
                        mMyCallBack.getData(gouBean);
                    }
            }
        });
    }
}
