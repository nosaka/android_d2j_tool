// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

abstract class EntityIndex
    implements Comparable, Serializable
{

    EntityIndex()
    {
        start = -1;
        end = -1;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((EntityIndex)obj);
    }

    public int compareTo(EntityIndex entityindex)
    {
        long l = start - entityindex.start;
        if(l < 0x80000000L)
            return 0x80000000;
        if(l > 0x7fffffffL)
            return 0x7fffffff;
        else
            return (int)l;
    }

    int getEnd()
    {
        return end;
    }

    int getStart()
    {
        return start;
    }

    void setEnd(int i)
    {
        end = i;
    }

    void setStart(int i)
    {
        start = i;
    }

    private static final long serialVersionUID = 0x26e7c95fL;
    private int end;
    private int start;
}
