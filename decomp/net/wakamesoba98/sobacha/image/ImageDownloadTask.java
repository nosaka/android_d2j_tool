// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnDownloadedListener;
import net.wakamesoba98.sobacha.twitter.media.EnumMediaProvider;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.Twitter;
import twitter4j.TwitterException;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            FileDownloadTask

public class ImageDownloadTask extends FileDownloadTask
{

    public ImageDownloadTask(Context context1, OnDownloadedListener ondownloadedlistener, MediaURL mediaurl, String s, UserAccount useraccount)
    {
        context = context1;
        listener = ondownloadedlistener;
        userAccount = useraccount;
        if(mediaurl.getProvider() == EnumMediaProvider.TWITTER)
        {
            src = mediaurl.getOriginalUrl();
            ondownloadedlistener = getFileName(mediaurl.getUrl(false));
            isDirectMessage = false;
        } else
        if(mediaurl.getProvider() == EnumMediaProvider.TWITTER_DM)
        {
            src = mediaurl.getUrl(false);
            ondownloadedlistener = getFileName(mediaurl.getUrl(false));
            isDirectMessage = true;
        } else
        {
            src = mediaurl.getUrl(false);
            ondownloadedlistener = getFileName(src);
            isDirectMessage = false;
        }
        context1 = makeDownloadDir(context1, s);
        target = (new StringBuilder()).append(context1).append("/").append(ondownloadedlistener).toString();
    }

    protected transient File doInBackground(Void avoid[])
    {
        if(src == null || target == null)
            return null;
        if(isDirectMessage)
            return doDownload((new TwitterUtils()).getTwitterInstance(context, userAccount.getAccessToken(context)).getDMImageAsStream(src.toString()), -1L);
        avoid = src.openConnection();
        avoid = doDownload(avoid.getInputStream(), avoid.getContentLength());
        return avoid;
        avoid;
_L2:
        avoid.printStackTrace();
        return null;
        avoid;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    private Context context;
    private boolean isDirectMessage;
    private UserAccount userAccount;
}
