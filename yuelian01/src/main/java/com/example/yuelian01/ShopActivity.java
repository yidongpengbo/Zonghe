package com.example.yuelian01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuelian01.Bean.AddBean;
import com.example.yuelian01.Bean.PhoneBean;
import com.example.yuelian01.adapter.ShopAdapter;
import com.example.yuelian01.ipresenter.IPresenterImp;
import com.example.yuelian01.iview.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener, IView {
    /**
     * 综合
     */
    private TextView mZongHe;
    /**
     * 销量
     */
    private TextView mXiaoLiang;
    /**
     * 价格
     */
    private TextView mJiaGe;
    /**
     * 筛选
     */
    private TextView mShaiXuan;
    private XRecyclerView mXRecycler;
    ShopAdapter mShopAdapter;
    private ImageView mSousuo;
    /**
     * 搜索
     */
    private EditText mEdit;
    private HashMap <String, String> mMap;
    String  GUANJIANZI="手机";
    int mPage=1;
    int mSort=0;
    int point;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        initView();
        initXrecycler();
    }

    private void initXrecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mXRecycler.setLayoutManager(manager);
        mShopAdapter = new ShopAdapter(this);
        mXRecycler.setAdapter(mShopAdapter);
        mXRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();
        //接口回调---添加到购物车
        mShopAdapter.setAddCall(new ShopAdapter.AddCall() {
            @Override
            public void addcall(int i) {
                //得到点击的下标
                point=i;
                String addpath="http://www.zhaoapi.cn/product/addCart";
                HashMap <String, String> map = new HashMap <>();
                map.put("uid",71+"");
                map.put("pid",i+"");
                mIPresenterImp.startRequest(map,addpath,AddBean.class);
            }
        });


    }

    IPresenterImp mIPresenterImp;
    String path = "http://www.zhaoapi.cn/product/searchProducts";

    private void initData() {
        mMap = new HashMap<>();
        mMap.put("keywords", GUANJIANZI);
        mMap.put("page", mPage + "");
        mMap.put("sort", mSort + "");
        mIPresenterImp = new IPresenterImp(this);
        mIPresenterImp.startRequest(mMap, path, PhoneBean.class);

    }

    private void initView() {
        mZongHe = (TextView) findViewById(R.id.ZongHe);
        mZongHe.setOnClickListener(this);
        mXiaoLiang = (TextView) findViewById(R.id.XiaoLiang);
        mXiaoLiang.setOnClickListener(this);
        mJiaGe = (TextView) findViewById(R.id.JiaGe);
        mJiaGe.setOnClickListener(this);
        mShaiXuan = (TextView) findViewById(R.id.ShaiXuan);
        mShaiXuan.setOnClickListener(this);
        mXRecycler = (XRecyclerView) findViewById(R.id.XRecycler);
        mSousuo = (ImageView) findViewById(R.id.sousuo);
        mSousuo.setOnClickListener(this);
        mEdit = (EditText) findViewById(R.id.edit);
        mXRecycler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ZongHe:
                mPage=1;
                mSort=0;
                initData();
                break;
            case R.id.XiaoLiang:
                mPage=1;
                mSort=1;
                initData();
                break;
            case R.id.JiaGe:
                mPage=1;
                mSort=2;
                initData();
                break;
            case R.id.ShaiXuan:
                break;
            case R.id.sousuo:
                String input_edit = mEdit.getText().toString();
                //右边赋值给左边
                GUANJIANZI=input_edit;
                mPage=1;
                mSort=0;
               initData();
                break;
            case R.id.XRecycler:
                break;
        }
    }

    @Override
    public void setData(Object o) {
        if (o instanceof PhoneBean) {
            PhoneBean phoneBean = (PhoneBean) o;
            if (mPage == 1) {
                mShopAdapter.setMjihe(phoneBean.getData());
            } else {
                mShopAdapter.addMjihe(phoneBean.getData());
            }
            mPage++;
            mXRecycler.refreshComplete();
            mXRecycler.loadMoreComplete();
        }else if (o instanceof AddBean){
            AddBean addBean=(AddBean)o;
            String msg = addBean.getMsg();
            Toast.makeText(ShopActivity.this,msg,Toast.LENGTH_LONG).show();
        }
    }
}
