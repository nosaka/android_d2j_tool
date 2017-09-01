// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.graphics.Paint;

class PaintCompatApi23
{

    PaintCompatApi23()
    {
    }

    static boolean hasGlyph(Paint paint, String s)
    {
        return paint.hasGlyph(s);
    }
}
