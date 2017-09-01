// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.graphics.Outline;

// Referenced classes of package android.support.design.widget:
//            CircularBorderDrawable

class CircularBorderDrawableLollipop extends CircularBorderDrawable
{

    CircularBorderDrawableLollipop()
    {
    }

    public void getOutline(Outline outline)
    {
        copyBounds(mRect);
        outline.setOval(mRect);
    }
}
