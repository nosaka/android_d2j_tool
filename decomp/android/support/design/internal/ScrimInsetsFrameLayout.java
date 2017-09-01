// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ScrimInsetsFrameLayout extends FrameLayout
{

    public ScrimInsetsFrameLayout(Context context)
    {
        this(context, null);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mTempRect = new Rect();
        context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.ScrimInsetsFrameLayout, i, android.support.design.R.style.Widget_Design_ScrimInsetsFrameLayout);
        mInsetForeground = context.getDrawable(android.support.design.R.styleable.ScrimInsetsFrameLayout_insetForeground);
        context.recycle();
        setWillNotDraw(true);
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {

            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
            {
                if(mInsets == null)
                    mInsets = new Rect();
                mInsets.set(windowinsetscompat.getSystemWindowInsetLeft(), windowinsetscompat.getSystemWindowInsetTop(), windowinsetscompat.getSystemWindowInsetRight(), windowinsetscompat.getSystemWindowInsetBottom());
                onInsetsChanged(windowinsetscompat);
                view = ScrimInsetsFrameLayout.this;
                boolean flag;
                if(!windowinsetscompat.hasSystemWindowInsets() || mInsetForeground == null)
                    flag = true;
                else
                    flag = false;
                view.setWillNotDraw(flag);
                ViewCompat.postInvalidateOnAnimation(ScrimInsetsFrameLayout.this);
                return windowinsetscompat.consumeSystemWindowInsets();
            }

            final ScrimInsetsFrameLayout this$0;

            
            {
                this$0 = ScrimInsetsFrameLayout.this;
                super();
            }
        }
);
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        int i = getWidth();
        int j = getHeight();
        if(mInsets != null && mInsetForeground != null)
        {
            int k = canvas.save();
            canvas.translate(getScrollX(), getScrollY());
            mTempRect.set(0, 0, i, mInsets.top);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);
            mTempRect.set(0, j - mInsets.bottom, i, j);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);
            mTempRect.set(0, mInsets.top, mInsets.left, j - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);
            mTempRect.set(i - mInsets.right, mInsets.top, i, j - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);
            canvas.restoreToCount(k);
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mInsetForeground != null)
            mInsetForeground.setCallback(this);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mInsetForeground != null)
            mInsetForeground.setCallback(null);
    }

    protected void onInsetsChanged(WindowInsetsCompat windowinsetscompat)
    {
    }

    Drawable mInsetForeground;
    Rect mInsets;
    private Rect mTempRect;
}
