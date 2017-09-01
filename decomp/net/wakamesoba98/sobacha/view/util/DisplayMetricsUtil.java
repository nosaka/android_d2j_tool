// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DisplayMetricsUtil
{

    public DisplayMetricsUtil()
    {
    }

    public static int convertDipToPixel(Context context, int i)
    {
        return (int)((float)i * context.getResources().getDisplayMetrics().density + 0.5F);
    }
}
