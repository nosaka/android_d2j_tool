// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;


class MathUtils
{

    MathUtils()
    {
    }

    static float constrain(float f, float f1, float f2)
    {
        if(f < f1)
            return f1;
        if(f > f2)
            return f2;
        else
            return f;
    }

    static int constrain(int i, int j, int k)
    {
        if(i < j)
            return j;
        if(i > k)
            return k;
        else
            return i;
    }
}
