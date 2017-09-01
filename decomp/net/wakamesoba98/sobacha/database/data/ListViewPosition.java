// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database.data;


public class ListViewPosition
{

    public ListViewPosition(int i, int j)
    {
        position = i;
        offset = j;
    }

    public int getOffset()
    {
        return offset;
    }

    public int getPosition()
    {
        return position;
    }

    private int offset;
    private int position;
}
