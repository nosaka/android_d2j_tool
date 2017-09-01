// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.util;


public class LongArray
{

    LongArray(long al[])
    {
        int i = al.length;
        longArray = new long[i];
        System.arraycopy(al, 0, longArray, 0, i);
    }

    public long[] getLongArray()
    {
        return longArray;
    }

    private long longArray[];
}
