// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            ViewOffsetHelper, CoordinatorLayout

class ViewOffsetBehavior extends CoordinatorLayout.Behavior
{

    public ViewOffsetBehavior()
    {
        mTempTopBottomOffset = 0;
        mTempLeftRightOffset = 0;
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTempTopBottomOffset = 0;
        mTempLeftRightOffset = 0;
    }

    public int getLeftAndRightOffset()
    {
        if(mViewOffsetHelper != null)
            return mViewOffsetHelper.getLeftAndRightOffset();
        else
            return 0;
    }

    public int getTopAndBottomOffset()
    {
        if(mViewOffsetHelper != null)
            return mViewOffsetHelper.getTopAndBottomOffset();
        else
            return 0;
    }

    protected void layoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        coordinatorlayout.onLayoutChild(view, i);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        layoutChild(coordinatorlayout, view, i);
        if(mViewOffsetHelper == null)
            mViewOffsetHelper = new ViewOffsetHelper(view);
        mViewOffsetHelper.onViewLayout();
        if(mTempTopBottomOffset != 0)
        {
            mViewOffsetHelper.setTopAndBottomOffset(mTempTopBottomOffset);
            mTempTopBottomOffset = 0;
        }
        if(mTempLeftRightOffset != 0)
        {
            mViewOffsetHelper.setLeftAndRightOffset(mTempLeftRightOffset);
            mTempLeftRightOffset = 0;
        }
        return true;
    }

    public boolean setLeftAndRightOffset(int i)
    {
        if(mViewOffsetHelper != null)
        {
            return mViewOffsetHelper.setLeftAndRightOffset(i);
        } else
        {
            mTempLeftRightOffset = i;
            return false;
        }
    }

    public boolean setTopAndBottomOffset(int i)
    {
        if(mViewOffsetHelper != null)
        {
            return mViewOffsetHelper.setTopAndBottomOffset(i);
        } else
        {
            mTempTopBottomOffset = i;
            return false;
        }
    }

    private int mTempLeftRightOffset;
    private int mTempTopBottomOffset;
    private ViewOffsetHelper mViewOffsetHelper;
}
