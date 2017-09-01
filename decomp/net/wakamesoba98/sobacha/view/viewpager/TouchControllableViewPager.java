// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TouchControllableViewPager extends ViewPager
{

    public TouchControllableViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        enabled = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if(!enabled) goto _L2; else goto _L1
_L1:
        boolean flag = super.onInterceptTouchEvent(motionevent);
        return flag;
        motionevent;
_L4:
        motionevent.printStackTrace();
        return false;
_L2:
        return false;
        motionevent;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(enabled)
        {
            boolean flag;
            try
            {
                flag = super.onTouchEvent(motionevent);
            }
            // Misplaced declaration of an exception variable
            catch(MotionEvent motionevent)
            {
                motionevent.printStackTrace();
                return false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void setTouchEnable(boolean flag)
    {
        enabled = flag;
    }

    private boolean enabled;
}
