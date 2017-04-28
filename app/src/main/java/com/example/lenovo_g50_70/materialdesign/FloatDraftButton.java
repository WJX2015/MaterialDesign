package com.example.lenovo_g50_70.materialdesign;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo-G50-70 on 2017/4/25.
 */

public class FloatDraftButton extends FloatingActionButton implements View.OnTouchListener{

    private int lastX,lastY;
    private int originX,originY;
    private  int screenWidth;
    private  int screenHeight;
    private ArrayList<TextView> mTextViews=new ArrayList<>();

    public FloatDraftButton(Context context) {
        this(context,null);
    }

    public FloatDraftButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth =ScreenUtil.getScreenWidth(context);
        screenHeight=ScreenUtil.getScreenHeight(context);
        setOnTouchListener(this);
    }

    public FloatDraftButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 注册点击弹出菜单的TextViews
     * @param textView
     */
    public void registerTextView(TextView textView){
        mTextViews.add(textView);
    }

    /**
     * 返回子菜单
     * @return
     */
    public ArrayList<TextView> getTextViews(){
        return mTextViews;
    }

    /**
     * 返回字菜单的项数
     * @return
     */
    public int getTextViewSize(){
        return mTextViews.size();
    }

    /**
     * 判断FloatDraftButton当前状态是否支持拖拽
     * @return
     */
    public boolean isDraftable(){
        for(TextView textView:mTextViews){
            if(textView.getVisibility()==View.VISIBLE){
                return false;
            }
        }
        return true;
    }

    /**
     * 子菜单随FloatDraftButton移动而移动
     * @param left   View的矩形空间左上角X的值
     * @param top    View的矩形空间左上角Y的值
     * @param right  View的矩形空间右下角X的值
     * @param bottom View的矩形空间右下角Y的值
     */
    private void slideTextViews(int left,int top,int right,int bottom){
        for(TextView textView:mTextViews){
            textView.layout(left,top,right,bottom);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        if(!isDraftable()){
//            return false;
//        }

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                Log.i("FloaDraftButton-Dowm",lastX+","+lastY);
                originX =lastX;
                originY=lastY;
                break;

            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX()-lastX);
                int dy = (int) (event.getY()-lastY);
                Log.i("FloaDraftButton-Move",dx+","+dy);
                int left =v.getLeft()+dx;
                int top =v.getTop()+dy;
                int right =v.getRight()+dx;
                int bottom=v.getBottom()+dy;
                //判断移动是否超出屏幕
                if(left<0){
                    left=0;
                    right=left+v.getWidth();
                }
                if(top<0){
                    top=0;
                    bottom=top+v.getHeight();
                }
                if(right>screenWidth){
                    right =screenWidth;
                    left=right-v.getWidth();
                }
                if(bottom>screenHeight){
                    bottom=screenHeight;
                    top=bottom-v.getHeight();
                }
                v.layout(left,top,right,bottom);
                slideTextViews(left,top,right,bottom);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();

                v.postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                int distance = (int) (event.getRawX()-originX+event.getRawY()-originY);
                if(Math.abs(distance)<20){
                    //当变化太小的时候什么都不做 OnClick执行
                }else {
                    return true;
                }
                break;
        }
        return false;
    }
}
