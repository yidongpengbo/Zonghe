package com.example.yuelian01;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class JiaJian extends LinearLayout {

    private Button mJia;
    private Button mJian;
    private EditText mNum;

    public JiaJian(Context context) {
        super(context);
        init(context);
    }

    public JiaJian(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private Context mContext;

    private void init(Context context) {
        this.mContext = context;
        View view = View.inflate(mContext, R.layout.jia_jian, null);
        mJia = view.findViewById(R.id.jia);
        mJian = view.findViewById(R.id.jian);
        mNum = view.findViewById(R.id.num);
        initJia();
        initjian();
        addView(view);
    }

    private void initjian() {
        mJian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickNum>1){
                    ClickNum--;
                    mNum.setText(ClickNum+"");
                }else {
                    Toast.makeText(mContext,"商品数不能小于1",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    int ClickNum;
    private void initJia() {
        mJia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickNum++;
                mNum.setText(ClickNum+"");
            }
        });
    }
}
