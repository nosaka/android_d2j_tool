// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.compatible;

import android.content.Context;
import android.os.Environment;
import java.io.File;

public class ExternalDirectory
{

    public ExternalDirectory()
    {
    }

    public String getCacheDir(Context context)
    {
        if(context.getExternalCacheDir() != null)
            return context.getExternalCacheDir().getAbsolutePath();
        else
            return context.getCacheDir().getAbsolutePath();
    }

    public String getDownloadDir()
    {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }
}
