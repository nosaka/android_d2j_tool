// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package it.sephiroth.android.library.imagezoom.easing;


// Referenced classes of package it.sephiroth.android.library.imagezoom.easing:
//            Easing

public class Cubic
    implements Easing
{

    public Cubic()
    {
    }

    public double easeIn(double d, double d1, double d2, double d3)
    {
        d /= d3;
        return d2 * d * d * d + d1;
    }

    public double easeInOut(double d, double d1, double d2, double d3)
    {
        d /= d3 / 2D;
        if(d < 1.0D)
        {
            return (d2 / 2D) * d * d * d + d1;
        } else
        {
            d2 /= 2D;
            d -= 2D;
            return d2 * (d * d * d + 2D) + d1;
        }
    }

    public double easeOut(double d, double d1, double d2, double d3)
    {
        d = d / d3 - 1.0D;
        return (d * d * d + 1.0D) * d2 + d1;
    }
}
