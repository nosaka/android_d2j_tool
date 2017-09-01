// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;

class TransitionValuesMaps
{

    TransitionValuesMaps()
    {
        viewValues = new ArrayMap();
        idValues = new SparseArray();
        itemIdValues = new LongSparseArray();
    }

    public SparseArray idValues;
    public LongSparseArray itemIdValues;
    public ArrayMap viewValues;
}
