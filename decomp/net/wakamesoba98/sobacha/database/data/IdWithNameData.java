// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database.data;


public class IdWithNameData
{

    public IdWithNameData(long l, String s)
    {
        id = l;
        name = s;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    private long id;
    private String name;
}
