// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.*;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.media.ExifInterface;
import java.io.*;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            EnumImageFormat

public class UriResolver
{

    public UriResolver()
    {
    }

    public Uri getCameraUri(Context context)
        throws UnsupportedOperationException
    {
        String s = (new StringBuilder()).append(System.currentTimeMillis()).append(".jpg").toString();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("title", s);
        contentvalues.put("mime_type", "image/jpeg");
        return context.getContentResolver().insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentvalues);
    }

    public String getFileName(Context context, Uri uri)
    {
        if("content".equals(uri.getScheme().toLowerCase()))
        {
            uri = context.getContentResolver().query(uri, new String[] {
                "_display_name"
            }, null, null, null);
            if(uri != null)
            {
                context = null;
                if(uri.moveToFirst())
                    context = uri.getString(0);
                uri.close();
                return context;
            }
        } else
        if("file".equals(uri.getScheme().toLowerCase()))
            return (new File(uri.getPath())).getName();
        return null;
    }

    public long getFileSize(Context context, Uri uri)
    {
        if("content".equals(uri.getScheme().toLowerCase()))
        {
            context = context.getContentResolver().query(uri, new String[] {
                "_size"
            }, null, null, null);
            if(context != null)
            {
                long l = -1L;
                if(context.moveToFirst())
                    l = context.getLong(0);
                context.close();
                return l;
            }
        } else
        if("file".equals(uri.getScheme().toLowerCase()))
            return (new File(uri.getPath())).length();
        return -1L;
    }

    public EnumImageFormat getImageFormat(Context context, Uri uri)
    {
        byte byte0;
        context = context.getContentResolver().getType(uri);
        if(context == null)
            return EnumImageFormat.OTHER;
        context = context.toLowerCase();
        byte0 = -1;
        context.hashCode();
        JVM INSTR lookupswitch 2: default 56
    //                   -1487394660: 84
    //                   -879258763: 98;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_98;
_L4:
        switch(byte0)
        {
        default:
            return EnumImageFormat.OTHER;

        case 0: // '\0'
            return EnumImageFormat.JPEG;

        case 1: // '\001'
            return EnumImageFormat.PNG;
        }
_L2:
        if(context.equals("image/jpeg"))
            byte0 = 0;
          goto _L4
        if(context.equals("image/png"))
            byte0 = 1;
          goto _L4
    }

    int getImageOrientation(Context context, Uri uri)
    {
        int i = 0;
        if(getImageFormat(context, uri) == EnumImageFormat.JPEG) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        Context context1;
        Object obj;
        Object obj1;
        obj1 = null;
        obj = null;
        context1 = obj1;
        context = context.getContentResolver().openInputStream(uri);
        if(context != null)
            break; /* Loop/switch isn't completed */
        if(false)
        {
            try
            {
                throw new NullPointerException();
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context.printStackTrace();
            }
            return 0;
        }
        if(true) goto _L1; else goto _L3
_L3:
        context1 = obj1;
        context = new BufferedInputStream(context);
        int j = (new ExifInterface(context)).getAttributeInt("Orientation", 1);
        switch(j)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 7: // '\007'
        default:
            if(context != null)
            {
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    context.printStackTrace();
                    return 0;
                }
                return 0;
            }
            break;

        case 6: // '\006'
            i = 90;
            if(context != null)
            {
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    context.printStackTrace();
                    return 90;
                }
                return 90;
            }
            break;

        case 3: // '\003'
            i = 180;
            if(context != null)
            {
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    context.printStackTrace();
                    return 180;
                }
                return 180;
            }
            break;

        case 8: // '\b'
            i = 270;
            continue; /* Loop/switch isn't completed */
        }
        if(true) goto _L1; else goto _L4
_L4:
        if(context == null) goto _L1; else goto _L5
_L5:
        try
        {
            context.close();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return 270;
        }
        return 270;
        uri;
        context = obj;
_L9:
        context1 = context;
        uri.printStackTrace();
        if(context == null) goto _L1; else goto _L6
_L6:
        try
        {
            context.close();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return 0;
        }
        return 0;
        context;
_L8:
        if(context1 != null)
            try
            {
                context1.close();
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri)
            {
                uri.printStackTrace();
            }
        throw context;
        uri;
        context1 = context;
        context = uri;
        if(true) goto _L8; else goto _L7
_L7:
        uri;
          goto _L9
    }

    public void registerCameraImage(Context context, Uri uri, android.media.MediaScannerConnection.OnScanCompletedListener onscancompletedlistener)
    {
        String s;
        Object obj;
        s = null;
        obj = null;
        if(!"content".equals(uri.getScheme().toLowerCase())) goto _L2; else goto _L1
_L1:
        uri = context.getContentResolver().query(uri, new String[] {
            "_data"
        }, null, null, null);
        if(uri != null)
        {
            s = obj;
            if(uri.moveToFirst())
                s = uri.getString(0);
            uri.close();
        }
_L4:
        if(s != null)
            MediaScannerConnection.scanFile(context, new String[] {
                s
            }, null, onscancompletedlistener);
        return;
_L2:
        if("file".equals(uri.getScheme().toLowerCase()))
            s = (new File(uri.getPath())).getAbsolutePath();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void updateMediaScanner(Context context, Uri uri)
    {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    private static final String SCHEME_CONTENT = "content";
    private static final String SCHEME_FILE = "file";
}
