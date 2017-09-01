// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.os.IBinder;
import android.view.View;

class WindowIdPort
{

    private WindowIdPort(IBinder ibinder)
    {
        mToken = ibinder;
    }

    static WindowIdPort getWindowId(View view)
    {
        return new WindowIdPort(view.getWindowToken());
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof WindowIdPort) && ((WindowIdPort)obj).mToken.equals(mToken);
    }

    private final IBinder mToken;
}
