package com.example.yuelian01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuelian01.Bean.GouBean;
import com.example.yuelian01.adapter.ShangJiaAdapter;
import com.example.yuelian01.ipresenter.IPresenterImp;
import com.example.yuelian01.iview.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GouActivity extends AppCompatActivity implements IView, View.OnClickListener {
    String path = "http://www.zhaoapi.cn/product/getCarts";
    private RecyclerView mRecycler;
    /**
     * 已选(0)
     */
    private CheckBox mAllClick;
    /**
     * 总价(0)
     */
    private TextView mAllPrice;
    ShangJiaAdapter mJiaAdapter;
    IPresenterImp mIPresenterImp;
    private GouBean mGouBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou);

        initView();
        initRecycler();
        initData();
    }

    private void initData() {
        HashMap <String, String> map = new HashMap <>();
        map.put("uid", 71 + "");
        mIPresenterImp.startRequest(map, path, GouBean.class);
    }

    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mJiaAdapter);
        mJiaAdapter.setPinCall(new ShangJiaAdapter.JiaCall() {
            @Override
            public void jiacall(List<GouBean.DataBean> Pin_List) {
                int CheckNum=0,AllNum=0;
                double AllPrice=0;
                for (int i = 0; i <Pin_List.size() ; i++) {
                    List <GouBean.DataBean.ListBean> pin = Pin_List.get(i).getList();
                    for (int j = 0; j <pin.size() ; j++) {
                        int num = pin.get(i).getNum();
                        AllNum=AllNum+num;
                        if (pin.get(i).getIsChecked()){
                            AllPrice=AllPrice+pin.get(i).getNum()*pin.get(i).getBargainPrice();
                            CheckNum=CheckNum+pin.get(i).getNum();
                        }
                    }
                }
                if (CheckNum<AllNum){
                    mAllClick.setChecked(false);
                }else {
                    mAllClick.setChecked(true);
                }
                mAllPrice.setText("总价("+AllPrice+")");
                mAllClick.setText("已选("+CheckNum+")");

            }
        });
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.Recycler);
        mAllClick = (CheckBox) findViewById(R.id.AllClick);
        mAllPrice = (TextView) findViewById(R.id.AllPrice);
        mJiaAdapter = new ShangJiaAdapter(this);
        mIPresenterImp = new IPresenterImp(this);
        mAllClick.setOnClickListener(this);
    }

    @Override
    public void setData(Object o) {
        if (o instanceof GouBean) {
            mGouBean = (GouBean) o;
            mJiaAdapter.setMjihe(mGouBean.getData());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.AllClick:
                AllChecked(mAllClick.isChecked());
                break;
        }
    }
   // List<GouBean.DataBean>mjiaList=new ArrayList <>();
    private void AllChecked(boolean checked) {
        double AllPrice=0;
        int AllNum=0;
        List <GouBean.DataBean> data = mGouBean.getData();
        for (int i = 0; i <data.size() ; i++) {
                    data.get(i).setChecked(checked);
            List <GouBean.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                    list.get(j).setChecked(checked);
                    AllPrice+=list.get(j).getBargainPrice()*list.get(j).getNum();
                    AllNum+=list.get(j).getNum();
            }

        }
        if (checked){
            mAllPrice.setText("总价("+AllPrice+")");
        }
    }
}
