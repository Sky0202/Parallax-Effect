package com.sky.parallaxeffect;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 作者：SKY
 * 创建时间：2016-9-10 19:34
 * 描述：
 */
public class ParallaxeListView extends ListView {

    private ImageView iv_head;
    private int originalHeight;
    private int layoutHeight;
    private ValueAnimator va;

    public ImageView getImageView () {
        return iv_head;
    }

    public void setImageView (ImageView iv_head) {
        this.iv_head = iv_head;
        // 获取真实的高
        originalHeight = iv_head.getDrawable().getIntrinsicHeight();
        // 显示的高
        layoutHeight = iv_head.getLayoutParams().height;
    }

    public ParallaxeListView (Context context) {
        this(context, null);
    }

    public ParallaxeListView (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxeListView (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    /**
     * deltaY:　垂直方向的变化量
     * scrollY： 垂直方向滚动的坐标
     * scrollRangeY：垂直方向滚动的范围
     * maxOverScrollY：垂直方向滚动超出的最大范围
     * isTouchEvent： 是否是超出滚动时的触摸（不包含惯性滚动的超出滚动）
     */
    @Override
    protected boolean overScrollBy (int deltaX, int deltaY, int scrollX,
                                    int scrollY, int scrollRangeX, int scrollRangeY,
                                    int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        // 如果是触摸滚动，并且值为负则进行图片的缩放操作
        if (isTouchEvent && deltaY < 0) {
            int newHeight = iv_head.getHeight() + Math.abs(deltaY);
            if (newHeight > originalHeight * 0.7f){
                newHeight = (int) (originalHeight * 0.7f);
            }
            iv_head.getLayoutParams().height = newHeight;
            iv_head.requestLayout();
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    // 定义手抬起时的动画
    @Override
    public boolean onTouchEvent (MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP :
                va = ValueAnimator.ofFloat(iv_head.getHeight(), layoutHeight);
                // 设置动画改变得监听
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate (ValueAnimator valueAnimator) {
                        float newHeight = (float) valueAnimator.getAnimatedValue();
                        iv_head.getLayoutParams().height = (int) newHeight;
                        iv_head.requestLayout();
                    }
                });
                va.setDuration(1000);
                va.setInterpolator(new OvershootInterpolator(3));
                va.start();
                break;
            // 当按下时判断是否有未执行完的动画，有就取消，即优化操作
            case MotionEvent.ACTION_DOWN:
                if (va != null && va.isRunning()){
                    va.cancel();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
