package com.lhd.p2pinvest.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by lihuaidong on 2017/8/2 16:19.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class MyScrollView extends ScrollView
{
    public MyScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    private View innerView;
    private Rect normal = new Rect();
    private boolean animationFinish = true;

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (childCount > 0)
        {
            innerView = getChildAt(0);
        }
    }

    int lastY, downY;
    int lastX, downX;


    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (innerView == null || !animationFinish)
        {
            return super.onTouchEvent(ev);
        }

        int eventY = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downY = lastY = eventY;
                break;

            case MotionEvent.ACTION_MOVE:
                int dy = eventY - lastY;

                //补充：可限制上滑、下滑的移动范围
                //                int totalY = eventY - downY;
                //                if(totalY > UIUtils.dp2px(120) || totalY < -UIUtils.dp2px(120)){
                //                    break;
                //                }

                //                Log.e("TAG",innerView.getMeasuredHeight() + ":" + innerView
                // .getHeight());
                //                Log.e("TAG",this.getHeight() + "");
                //操作view进行拖动detailY的一半
                if (isNeedMove())
                {
                    //布局改变位置之前，记录一下正常状态的位置
                    if (normal.isEmpty())
                    {
                        normal.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(),
                                innerView.getBottom());
                    }
                    innerView.layout(innerView.getLeft(), innerView.getTop() + dy / 2, innerView
                            .getRight(), innerView.getBottom() + dy / 2);
                }

                lastY = eventY;
                break;

            case MotionEvent.ACTION_UP:
                //布局回滚到原来的位置
                if (isNeedAnimation())
                {
                    animation();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void animation()
    {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, normal.top - innerView
                .getTop());
        animation.setDuration(300);

        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                innerView.clearAnimation();
                innerView.layout(normal.left, normal.top, normal.right, normal.bottom);
                normal.setEmpty();
                animationFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        innerView.startAnimation(animation);
    }

    /**
     * 判断是否需要回滚
     *
     * @return
     */
    private boolean isNeedAnimation()
    {
        return !normal.isEmpty();
    }

    private boolean isNeedMove()
    {
        int measuredHeight = innerView.getMeasuredHeight();
        int dy = measuredHeight - this.getHeight();
        int scrollY = getScrollY();
        //左加右减，上加下减
        if (scrollY == 0 || scrollY == dy)
        {
            return true;
        }
        else
        {
            return false;
        }


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        boolean isIntercept = false;
        int eventX = (int) ev.getX();
        int eventY = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX = downX = eventX;
                lastY = downY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                int totalX = Math.abs(eventX - downX);
                int totalY = Math.abs(eventY - downY);
                Log.e("TAG", "totalX = " + totalX + ",totalY = " + totalY);
                if (totalY > totalX && totalY > 6)
                {
                    isIntercept = true;
                }
                lastX = eventX;
                lastY = eventY;
                break;

        }

        return isIntercept;
    }
}
