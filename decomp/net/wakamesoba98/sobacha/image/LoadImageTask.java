// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.IOException;
import java.net.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnLoadedImageListener;
import net.wakamesoba98.sobacha.twitter.media.EnumMediaProvider;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            ImageThresholdConstants, ImageUrlReader

public class LoadImageTask extends AsyncTask
    implements ImageThresholdConstants
{

    public LoadImageTask(Context context1, UserAccount useraccount, ImageView imageview, OnLoadedImageListener onloadedimagelistener)
    {
        context = context1;
        imageView = imageview;
        listener = onloadedimagelistener;
        userAccount = useraccount;
    }

    private MediaURL getImageURL(MediaURL mediaurl)
    {
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$twitter$media$EnumMediaProvider[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$twitter$media$EnumMediaProvider = new int[EnumMediaProvider.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$media$EnumMediaProvider[EnumMediaProvider.TWITPIC.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$media$EnumMediaProvider[EnumMediaProvider.YFROG.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$media$EnumMediaProvider[EnumMediaProvider.INSTAGRAM.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$media$EnumMediaProvider[EnumMediaProvider.IMG_LY.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.twitter.media.EnumMediaProvider[mediaurl.getProvider().ordinal()])
        {
        default:
            return mediaurl;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return getRedirectUrl(mediaurl);
        }
    }

    private MediaURL getRedirectUrl(MediaURL mediaurl)
    {
        URL url;
        String s;
        try
        {
            url = mediaurl.getUrl(false);
            HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection(Proxy.NO_PROXY);
            httpurlconnection.setInstanceFollowRedirects(false);
            httpurlconnection.connect();
            httpurlconnection.getResponseCode();
            s = httpurlconnection.getHeaderField("Location");
            httpurlconnection.disconnect();
        }
        // Misplaced declaration of an exception variable
        catch(MediaURL mediaurl)
        {
            mediaurl.printStackTrace();
            return null;
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_93;
        if(s.equals(url.toString()))
            break MISSING_BLOCK_LABEL_93;
        mediaurl = new MediaURL(new URL(s), mediaurl.getProvider());
        return mediaurl;
        return null;
    }

    protected transient Bitmap doInBackground(MediaURL amediaurl[])
    {
        amediaurl = getImageURL(amediaurl[0]);
        if(isCancelled() || amediaurl == null)
        {
            amediaurl = null;
        } else
        {
            amediaurl = (new ImageUrlReader()).getBitmap(context, amediaurl, false, userAccount, IMAGE_THRESHOLD);
            if(isCancelled())
                return null;
        }
        return amediaurl;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((MediaURL[])aobj);
    }

    protected void onPostExecute(Bitmap bitmap)
    {
        if(bitmap == null) goto _L2; else goto _L1
_L1:
        imageView.setImageBitmap(bitmap);
_L4:
        listener.onLoadedImage();
        return;
_L2:
        if(!isCancelled())
            Notificator.toast(context, 0x7f070085);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Bitmap)obj);
    }

    private Context context;
    private ImageView imageView;
    private OnLoadedImageListener listener;
    private UserAccount userAccount;
}
