// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.compatible;

import android.content.Context;

// Referenced classes of package net.wakamesoba98.sobacha.compatible:
//            SystemVersion

public class PermissionUtil
{

    public PermissionUtil()
    {
    }

    public static transient boolean checkSelfPermissions(Context context, String as[])
    {
        if(SystemVersion.isMarshmallowOrLater())
        {
            int j = as.length;
            for(int i = 0; i < j; i++)
                if(context.checkSelfPermission(as[i]) != 0)
                    return false;

        }
        return true;
    }

    public static transient boolean verifyPermissions(int ai[])
    {
        if(ai.length >= 1) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j = ai.length;
        int i = 0;
label0:
        do
        {
label1:
            {
                if(i >= j)
                    break label1;
                if(ai[i] != 0)
                    break label0;
                i++;
            }
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
        return true;
    }
}
