// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.compatible;

import android.app.Activity;

public class RequestPermission
{

    public RequestPermission()
    {
    }

    public void request(Activity activity, String s, int i)
    {
        activity.requestPermissions(new String[] {
            s
        }, i);
    }
}
