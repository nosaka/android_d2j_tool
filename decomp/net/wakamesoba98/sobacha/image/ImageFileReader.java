// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            ImageStreamReader, ImageThresholdConstants, UriResolver, EnumImageFormat, 
//            ImageEditor

public class ImageFileReader extends ImageStreamReader
    implements ImageThresholdConstants
{

    public ImageFileReader()
    {
    }

    public Bitmap getBitmap(Context context, Uri uri, int i)
    {
        try
        {
            context = context.getContentResolver();
            context = getBitmapFromStream(context.openInputStream(uri), calcSampleSize(context.openInputStream(uri), i));
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return null;
        }
        return context;
    }

    public Bitmap getThumbnail(Context context, Uri uri)
    {
        UriResolver uriresolver = new UriResolver();
        EnumImageFormat enumimageformat = uriresolver.getImageFormat(context, uri);
        Bitmap bitmap1 = getBitmap(context, uri, 320);
        Bitmap bitmap = bitmap1;
        if(enumimageformat == EnumImageFormat.JPEG)
            bitmap = (new ImageEditor()).rotateBitmap(bitmap1, uriresolver.getImageOrientation(context, uri));
        return bitmap;
    }
}
