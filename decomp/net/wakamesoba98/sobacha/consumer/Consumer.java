// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.consumer;

import android.content.Context;
import android.util.Base64;
import net.wakamesoba98.sobacha.core.ResourceHelper;

public class Consumer
{

    public Consumer()
    {
    }

    public static String getConsumer(Context context)
    {
        int i = 0;
        char ac[] = (new String(Base64.decode(ResourceHelper.getString(context, 0x7f070155), 0))).toCharArray();
        context = "";
        for(int j = ac.length; i < j; i++)
        {
            char c = ac[i];
            context = (new StringBuilder()).append(context).append((char)(c + 1)).toString();
        }

        return context;
    }

    public static String getSecret(Context context)
    {
        return new String(Base64.decode(ResourceHelper.getString(context, 0x7f070156), 0));
    }
}
