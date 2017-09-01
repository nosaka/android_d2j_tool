// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.util;

import java.util.*;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.util:
//            LongArray

public class ArrayUtils
{

    public ArrayUtils()
    {
    }

    public Queue splitList(List list, int i)
    {
        LinkedList linkedlist = new LinkedList();
        int j1 = list.size() / i;
        for(int j = 0; j < j1; j++)
        {
            long al[] = new long[i];
            for(int l = 0; l < i; l++)
                al[l] = ((Long)list.get(j * i + l)).longValue();

            linkedlist.offer(new LongArray(al));
        }

        int i1 = list.size() % i;
        long al1[] = new long[i1];
        for(int k = 0; k < i1; k++)
            al1[k] = ((Long)list.get(j1 * i + k)).longValue();

        if(al1.length > 0)
            linkedlist.offer(new LongArray(al1));
        return linkedlist;
    }
}
