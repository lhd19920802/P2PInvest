package com.lhd.p2pinvest.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lihuaidong on 2017/6/9 14:31.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：自定义圆形下载进度
 */
public class RoundProgress extends View
{

    private int progress=50;// 当前进度
    private int max=100;// 最大进度
    private int roundColor= Color.GRAY;// 圆环的颜色
    private int roundProgressColor=Color.GREEN;// 圆环进度的颜色
    private float roundWidth=10;// 圆环的宽度
    private int textSize=30;// 文字的大小
    private int textColor=Color.RED;// 文字的颜色

    private Paint paint;
    /**
     * 视图的宽
     */
    private int viewWidth;

    public RoundProgress(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public int getProgress()
    {
        return progress;
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
        postInvalidate();
    }

    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //绘制圆环
        float cx=viewWidth/2;
        float radius=cx-roundWidth/2;
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx,cx,radius,paint);
        //绘制圆弧
        RectF oval=new RectF(roundWidth/2,roundWidth/2,viewWidth-roundWidth/2,viewWidth-roundWidth/2);
        paint.setColor(roundProgressColor);
        canvas.drawArc(oval,0,progress*360/max,false,paint);
        //绘制文字
       String text=progress*100/max+"%";
        Rect bounds=new Rect();
        paint.getTextBounds(text,0,text.length(),bounds);
        float textWidth=bounds.width();
        float textHeight=bounds.height();
        float x=cx-textWidth/2;
        float y=cx+textHeight/2;
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        canvas.drawText(text,x,y,paint);
    }
}
