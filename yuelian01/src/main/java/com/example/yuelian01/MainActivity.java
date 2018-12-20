package com.example.yuelian01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuelian01.flowlayout.FlowLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditSousuo;
    private ImageView mImageSearch;
    private FlowLayout mFlowlayout;
    private FlowLayout mFlowlayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFlow();
        initFlow2();
    }

    private void initFlow2() {
        String[] shuju={"二等分","我二哥","威多福","万德福","上网电费","上网电费","地方","水电费",};
        for (int i = 0; i <shuju.length ; i++) {
            TextView view = new TextView(this);
            view.setText(shuju[i]);
            mFlowlayout2.addView(view);
        }
    }

    private void initFlow() {
        for (int i = 0; i < 30; i++) {
            TextView view = new TextView(this);
            view.setText("数据" + i);
            mFlowlayout.addView(view);
        }
    }

    private void initView() {
        mEditSousuo = (EditText) findViewById(R.id.edit_sousuo);
        mImageSearch = (ImageView) findViewById(R.id.image_search);
        mFlowlayout = (FlowLayout) findViewById(R.id.flowlayout);
        mImageSearch.setOnClickListener(this);

        mFlowlayout2 = (FlowLayout) findViewById(R.id.flowlayout2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.image_search:
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
