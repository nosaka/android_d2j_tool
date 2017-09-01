// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.compatible;


public class SystemVersion
{

    public SystemVersion()
    {
    }

    public static boolean isHoneycombOrLater()
    {
        return android.os.Build.VERSION.SDK_INT >= 11;
    }

    public static boolean isIcsOrLater()
    {
        return android.os.Build.VERSION.SDK_INT >= 14;
    }

    public static boolean isJellyBeanOrLater()
    {
        return android.os.Build.VERSION.SDK_INT >= 16;
    }

    public static boolean isKitKatOrLater()
    {
        return android.os.Build.VERSION.SDK_INT >= 19;
    }

    public static boolean isLollipopOrLater()
    {
        return android.os.Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isMarshmallowOrLater()
    {
        return android.os.Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isNougatOrLater()
    {
        return android.os.Build.VERSION.SDK_INT >= 24;
    }
}
