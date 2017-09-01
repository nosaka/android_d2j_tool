// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageEditor
{

    public ImageEditor()
    {
    }

    Bitmap resizeBitmapKeepRatio(Bitmap bitmap, int i)
    {
        int j = bitmap.getHeight();
        int k = bitmap.getWidth();
        float f;
        float f1;
        int l;
        Bitmap bitmap1;
        if(k < j)
        {
            f = (float)k / (float)j;
            f1 = 1.0F;
        } else
        {
            f = 1.0F;
            f1 = (float)j / (float)k;
        }
        l = (int)((float)i * f);
        i = (int)((float)i * f1);
        if(j > i || k > l)
            bitmap1 = Bitmap.createScaledBitmap(bitmap, l, i, true);
        else
        if(bitmap.getConfig() == null)
            bitmap1 = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true);
        else
            bitmap1 = bitmap.copy(bitmap.getConfig(), true);
        bitmap.recycle();
        return bitmap1;
    }

    public Bitmap rotateBitmap(Bitmap bitmap, int i)
    {
        Object obj = new Matrix();
        ((Matrix) (obj)).postRotate(i);
        obj = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), ((Matrix) (obj)), true);
        if(!bitmap.equals(obj))
            bitmap.recycle();
        return ((Bitmap) (obj));
    }
}
