// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.compatible;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

// Referenced classes of package net.wakamesoba98.sobacha.compatible:
//            SystemVersion

public class DualModeUtil
{

    public DualModeUtil()
    {
    }

    public static boolean canUseDualModeLayout(Activity activity)
    {
        if(!SystemVersion.isNougatOrLater()) goto _L2; else goto _L1
_L1:
        if(!isTabletDevice(activity)) goto _L4; else goto _L3
_L3:
        if(activity.getResources().getConfiguration().smallestScreenWidthDp < 600) goto _L5; else goto _L2
_L2:
        return true;
_L5:
        return false;
_L4:
        if(activity.isInMultiWindowMode())
            return false;
        if(true) goto _L2; else goto _L6
_L6:
    }

    public static boolean isTabletDevice(Context context)
    {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout & 0xf) >= 3;
    }

    private static final int TABLET_WIDTH = 600;
}
