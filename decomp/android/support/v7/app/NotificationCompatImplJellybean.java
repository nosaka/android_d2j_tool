// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

class NotificationCompatImplJellybean
{

    NotificationCompatImplJellybean()
    {
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence)
    {
        (new android.app.Notification.BigTextStyle(notificationbuilderwithbuilderaccessor.getBuilder())).bigText(charsequence);
    }
}
