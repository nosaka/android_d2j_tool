// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.Context;
import android.os.AsyncTask;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import net.wakamesoba98.sobacha.compatible.ExternalDirectory;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.twitter.listener.OnDownloadedListener;

abstract class FileDownloadTask extends AsyncTask
{

    FileDownloadTask()
    {
    }

    File doDownload(InputStream inputstream, long l)
    {
        boolean flag;
        boolean flag1;
        int i;
        long l1;
        FileOutputStream fileoutputstream;
        byte abyte0[];
        try
        {
            fileoutputstream = new FileOutputStream(target);
            abyte0 = new byte[4096];
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            inputstream.printStackTrace();
            return null;
        }
        l1 = 0L;
        flag1 = false;
        i = inputstream.read(abyte0);
        flag = flag1;
        if(i == -1)
            break MISSING_BLOCK_LABEL_101;
        l1 += i;
        if(l <= 0L)
            break MISSING_BLOCK_LABEL_81;
        publishProgress(new Integer[] {
            Integer.valueOf((int)((100L * l1) / l))
        });
        fileoutputstream.write(abyte0, 0, i);
        if(!isCancelled())
            break MISSING_BLOCK_LABEL_26;
        flag = true;
        fileoutputstream.flush();
        fileoutputstream.close();
        inputstream.close();
        if(flag)
            return null;
        inputstream = new File(target);
        return inputstream;
    }

    protected transient File doInBackground(Void avoid[])
    {
        if(src == null || target == null)
            return null;
        try
        {
            avoid = src.openConnection();
            avoid = doDownload(avoid.getInputStream(), avoid.getContentLength());
        }
        // Misplaced declaration of an exception variable
        catch(Void avoid[])
        {
            avoid.printStackTrace();
            return null;
        }
        return avoid;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    String getFileName(URL url)
    {
        url = url.getFile().split("/");
        String s = url[url.length - 1];
        url = s;
        if(s.contains("?"))
            url = s.substring(0, s.indexOf("?"));
        return url;
    }

    String makeDownloadDir(Context context, String s)
    {
        boolean flag = PreferenceUtil.getBooleanPreference(context, EnumPrefs.USE_SCREEN_NAME_DIR);
        context = new File((new ExternalDirectory()).getDownloadDir());
        context = new File((new StringBuilder()).append(context.getPath()).append("/SobaCha").toString());
        s = new File((new StringBuilder()).append(context.getPath()).append("/").append(s).toString());
        if(flag)
            context = s;
        if(context.exists() || context.mkdirs())
            return context.getPath();
        else
            return null;
    }

    protected void onCancelled()
    {
        File file = new File(target);
        if(file.exists())
            file.delete();
    }

    protected void onPostExecute(File file)
    {
        listener.downloaded(file);
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((File)obj);
    }

    protected OnDownloadedListener listener;
    protected URL src;
    protected String target;
}
