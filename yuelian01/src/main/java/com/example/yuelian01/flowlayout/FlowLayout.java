package com.example.yuelian01.flowlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class FlowLayout extends LinearLayout {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    int left=0,top=0;
    int mSpaceH=20,mSpachV=20;

    /**1.得到所有文本中最高的一个
     */
    int mHeight=0;
    public void FindMaxHeight(){
        //定义初始高为0

      //得到所有的文本
        int childCount = getChildCount();
        //循环文本
        for (int i = 0; i <childCount ; i++) {
                //得到文本当前的视图
            View view = getChildAt(i);
            //判断当前文本高
            if (view.getMeasuredHeight()>mHeight){
                mHeight=view.getMeasuredHeight();
            }
        }

    }

    /**
     * 通过测量所有的文本大小，来确定是否需要换行
     *
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到屏幕最大支持的宽高
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //测量出每行的宽高
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //给出文本的高
        FindMaxHeight();
        int left=0,top=0;
        //得到所有的 文本
        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View view = getChildAt(i);
            if (left!=0){
                if (left+(view.getMeasuredWidth())>size){
                    //换行
                    top=top+mHeight+mSpachV;
                    left=0;
                }
            }
            //此时屏幕起始位置
            left+=view.getMeasuredWidth()+mSpaceH;
            setMeasuredDimension(size,height>(top+mHeight)?(top+mHeight):height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int left=0,top=0;
        FindMaxHeight();
        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View view = getChildAt(i);
            if (left!=0){
                if (left+(view.getMeasuredWidth())>getWidth()){
                    top+=mHeight+mSpachV;
                    left=0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+mHeight);
            left+=view.getMeasuredWidth()+mSpaceH;
        }
    }
}
