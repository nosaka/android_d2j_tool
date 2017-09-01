// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

class VisibilityAwareImageButton extends ImageButton
{

    public VisibilityAwareImageButton(Context context)
    {
        this(context, null);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mUserSetVisibility = getVisibility();
    }

    final int getUserSetVisibility()
    {
        return mUserSetVisibility;
    }

    final void internalSetVisibility(int i, boolean flag)
    {
        super.setVisibility(i);
        if(flag)
            mUserSetVisibility = i;
    }

    public void setVisibility(int i)
    {
        internalSetVisibility(i, true);
    }

    private int mUserSetVisibility;
}
