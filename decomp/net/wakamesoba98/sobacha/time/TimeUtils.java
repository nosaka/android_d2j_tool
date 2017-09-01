// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.time;

import android.content.Context;
import android.text.format.DateFormat;
import java.util.Date;
import net.wakamesoba98.sobacha.core.ResourceHelper;

public class TimeUtils
{

    public TimeUtils(Context context)
    {
        day = ResourceHelper.getString(context, 0x7f07003c);
        hour = ResourceHelper.getString(context, 0x7f0700ad);
        minute = ResourceHelper.getString(context, 0x7f0700c7);
        second = ResourceHelper.getString(context, 0x7f0700fe);
    }

    public String getAbsoluteTime(Date date)
    {
        return DateFormat.format("yyyy/MM/dd kk:mm:ss", date).toString();
    }

    public String getRelativeTime(Date date)
    {
        long l = date.getTime();
        long l1 = (System.currentTimeMillis() - l) / 1000L;
        l = l1;
        if(l1 < 0L)
            l = 0L;
        l1 = l / 60L;
        long l2 = l1 / 60L;
        long l3 = l2 / 24L;
        if(l3 >= 1L)
            return (new StringBuilder()).append(l3).append(day).toString();
        if(l2 >= 1L)
            return (new StringBuilder()).append(l2).append(hour).toString();
        if(l1 >= 1L)
            return (new StringBuilder()).append(l1).append(minute).toString();
        else
            return (new StringBuilder()).append(l).append(second).toString();
    }

    private String day;
    private String hour;
    private String minute;
    private String second;
}
