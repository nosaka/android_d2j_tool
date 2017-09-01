// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import net.wakamesoba98.sobacha.compatible.SystemVersion;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            FlushedInputStream

class ImageStreamReader
{

    ImageStreamReader()
    {
    }

    private int calcSampleSize(int i, int j, int k)
    {
label0:
        {
            int l = 1;
            if(j > k || i > k)
            {
                if(i <= j)
                    break label0;
                l = Math.round((float)j / (float)k);
            }
            return l;
        }
        return Math.round((float)i / (float)k);
    }

    int calcSampleSize(InputStream inputstream, int i)
        throws IOException
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FlushedInputStream(inputstream), null, options);
        inputstream.close();
        return calcSampleSize(options.outWidth, options.outHeight, i);
    }

    Bitmap getBitmapFromStream(InputStream inputstream, int i)
    {
        Object obj;
        try
        {
            obj = new android.graphics.BitmapFactory.Options();
            obj.inSampleSize = i;
            if(!SystemVersion.isHoneycombOrLater())
                obj.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
            obj = BitmapFactory.decodeStream(new FlushedInputStream(inputstream), null, ((android.graphics.BitmapFactory.Options) (obj)));
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            inputstream.printStackTrace();
            return null;
        }
        return ((Bitmap) (obj));
    }
}
