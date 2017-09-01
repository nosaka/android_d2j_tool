// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.*;
import net.wakamesoba98.sobacha.compatible.ExternalDirectory;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            ImageThresholdConstants, EnumImageFormat, ImageFileReader, ImageEditor, 
//            UriResolver

public class ImageFileWriter
    implements ImageThresholdConstants
{

    public ImageFileWriter()
    {
    }

    private Uri writeFromBitmap(Context context, Bitmap bitmap, String s, EnumImageFormat enumimageformat)
    {
        Object obj;
        context = (new ExternalDirectory()).getCacheDir(context);
        obj = new File(context);
        if(((File) (obj)).exists() || ((File) (obj)).mkdir()) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        obj = new ByteArrayOutputStream();
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$image$EnumImageFormat[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$image$EnumImageFormat = new int[EnumImageFormat.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$image$EnumImageFormat[EnumImageFormat.JPEG.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$image$EnumImageFormat[EnumImageFormat.PNG.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.image.EnumImageFormat[enumimageformat.ordinal()];
        JVM INSTR tableswitch 1 2: default 80
    //                   1 82
    //                   2 178;
           goto _L3 _L4 _L5
_L3:
        return null;
_L4:
        bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 90, ((java.io.OutputStream) (obj)));
        context = (new StringBuilder()).append(context).append("/").append(s).append(".jpg").toString();
_L7:
        ((ByteArrayOutputStream) (obj)).flush();
        s = ((ByteArrayOutputStream) (obj)).toByteArray();
        ((ByteArrayOutputStream) (obj)).close();
        enumimageformat = new FileOutputStream(context);
        enumimageformat.write(s);
        enumimageformat.close();
        bitmap.recycle();
        context = Uri.fromFile(new File(context));
        return context;
_L5:
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 90, ((java.io.OutputStream) (obj)));
        context = (new StringBuilder()).append(context).append("/").append(s).append(".png").toString();
        if(true) goto _L7; else goto _L6
_L6:
        context;
        context.printStackTrace();
        if(!bitmap.isRecycled())
        {
            bitmap.recycle();
            return null;
        }
        if(true) goto _L1; else goto _L8
_L8:
    }

    public Uri outputResizeImage(Context context, Uri uri, int i, int j)
    {
        Bitmap bitmap = (new ImageFileReader()).getBitmap(context, uri, 1600);
        if(bitmap == null)
            return null;
        ImageEditor imageeditor = new ImageEditor();
        Bitmap bitmap1 = imageeditor.resizeBitmapKeepRatio(bitmap, i);
        UriResolver uriresolver = new UriResolver();
        EnumImageFormat enumimageformat = uriresolver.getImageFormat(context, uri);
        bitmap = bitmap1;
        if(enumimageformat == EnumImageFormat.JPEG)
            bitmap = imageeditor.rotateBitmap(bitmap1, uriresolver.getImageOrientation(context, uri));
        uri = (new StringBuilder()).append("resize_").append(j).toString();
        if(enumimageformat == EnumImageFormat.PNG)
            return writeFromBitmap(context, bitmap, uri, EnumImageFormat.PNG);
        else
            return writeFromBitmap(context, bitmap, uri, EnumImageFormat.JPEG);
    }
}
