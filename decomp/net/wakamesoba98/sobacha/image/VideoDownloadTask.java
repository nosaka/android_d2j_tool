// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.net.URL;
import net.wakamesoba98.sobacha.twitter.listener.OnDownloadedListener;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            FileDownloadTask

public class VideoDownloadTask extends FileDownloadTask
{

    public VideoDownloadTask(Context context, OnDownloadedListener ondownloadedlistener, URL url, String s, ProgressBar progressbar, TextView textview)
    {
        progressBar = progressbar;
        textView = textview;
        listener = ondownloadedlistener;
        src = url;
        ondownloadedlistener = getFileName(url);
        context = makeDownloadDir(context, s);
        target = (new StringBuilder()).append(context).append("/").append(ondownloadedlistener).toString();
    }

    protected transient void onProgressUpdate(Integer ainteger[])
    {
        if(progressBar != null && textView != null)
        {
            progressBar.setProgress(ainteger[0].intValue());
            textView.setText((new StringBuilder()).append(ainteger[0]).append("%").toString());
        }
    }

    protected volatile void onProgressUpdate(Object aobj[])
    {
        onProgressUpdate((Integer[])aobj);
    }

    private ProgressBar progressBar;
    private TextView textView;
}
