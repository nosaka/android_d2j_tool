// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;

class ThemeUtils
{

    ThemeUtils()
    {
    }

    static void checkAppCompatTheme(Context context)
    {
        boolean flag = false;
        context = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        if(!context.hasValue(0))
            flag = true;
        context.recycle();
        if(flag)
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        else
            return;
    }

    private static final int APPCOMPAT_CHECK_ATTRS[];

    static 
    {
        APPCOMPAT_CHECK_ATTRS = (new int[] {
            android.support.v7.appcompat.R.attr.colorPrimary
        });
    }
}
