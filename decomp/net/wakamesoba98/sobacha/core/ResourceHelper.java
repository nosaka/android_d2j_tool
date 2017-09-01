// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.core;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;

public class ResourceHelper
{

    public ResourceHelper()
    {
    }

    public static int getColor(Context context, int i)
    {
        return ContextCompat.getColor(context, i);
    }

    public static int getInteger(Context context, int i)
    {
        return context.getResources().getInteger(i);
    }

    public static String getString(Context context, int i)
    {
        return context.getResources().getString(i);
    }
}
