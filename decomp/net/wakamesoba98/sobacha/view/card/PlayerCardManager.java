// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.card;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.io.*;
import java.lang.ref.WeakReference;
import java.net.*;
import java.util.List;
import java.util.Map;
import net.wakamesoba98.sobacha.compatible.PermissionUtil;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.image.UriResolver;
import net.wakamesoba98.sobacha.image.VideoDownloadTask;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.twitter.listener.OnDownloadedListener;
import net.wakamesoba98.sobacha.view.activity.VideoViewActivity;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

public class PlayerCardManager
    implements android.view.View.OnClickListener, OnDownloadedListener
{
    private class GetContentLengthTask extends AsyncTask
    {

        private String getSizeWithUnit(long l)
        {
            double d = (double)l / 1024D;
            double d1 = (double)l / 1024D / 1024D;
            if(d1 > 1.0D)
                return (new StringBuilder()).append((int)d1).append(" MB").toString();
            if(d > 1.0D)
                return (new StringBuilder()).append((int)d).append(" KB").toString();
            else
                return (new StringBuilder()).append(l).append(" B").toString();
        }

        protected transient Long doInBackground(URL aurl[])
        {
            Object obj;
            Object obj1;
            obj1 = aurl[0];
            obj = null;
            aurl = null;
            obj1 = (HttpURLConnection)((URL) (obj1)).openConnection();
            aurl = ((URL []) (obj1));
            obj = obj1;
            ((HttpURLConnection) (obj1)).setRequestMethod("HEAD");
            aurl = ((URL []) (obj1));
            obj = obj1;
            long l = Long.parseLong((String)((List)((HttpURLConnection) (obj1)).getHeaderFields().get("Content-Length")).get(0));
            aurl = ((URL []) (obj1));
            obj = obj1;
            ((HttpURLConnection) (obj1)).getInputStream().close();
            if(obj1 != null)
                ((HttpURLConnection) (obj1)).disconnect();
            return Long.valueOf(l);
            IOException ioexception;
            ioexception;
            obj = aurl;
            ioexception.printStackTrace();
            if(aurl != null)
                aurl.disconnect();
            return null;
            aurl;
            if(obj != null)
                ((HttpURLConnection) (obj)).disconnect();
            throw aurl;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((URL[])aobj);
        }

        protected void onPostExecute(Long long1)
        {
            if(long1 != null)
            {
                (new ConfirmDialog() {

                    public void onPositiveButtonClick()
                    {
                        downloadVideo();
                    }

                    final GetContentLengthTask this$1;

            
            {
                this$1 = GetContentLengthTask.this;
                super();
            }
                }
).build(activity, 0x7f070048, 0x7f070098, getSizeWithUnit(long1.longValue()));
                return;
            } else
            {
                Notificator.toast(activity, 0x7f07007e);
                return;
            }
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Long)obj);
        }

        final PlayerCardManager this$0;

        private GetContentLengthTask()
        {
            this$0 = PlayerCardManager.this;
            super();
        }

    }


    public PlayerCardManager(VideoViewActivity videoviewactivity)
    {
        activity = videoviewactivity;
        viewGroupCard = (ViewGroup)videoviewactivity.findViewById(0x7f0e0092);
        theme = new ThemeManager(videoviewactivity);
        setButtonProperties();
        isBackground = PreferenceUtil.getBooleanPreference(videoviewactivity, EnumPrefs.BACKGROUND_DOWNLOAD);
    }

    private void dismissProgress()
    {
        if(activity != null)
        {
            View view = activity.findViewById(0x7f0e008e);
            View view1 = activity.findViewById(0x7f0e008f);
            if(!isBackground)
            {
                if(view != null)
                    view.setVisibility(8);
                if(view1 != null)
                {
                    view1.setVisibility(8);
                    return;
                }
            }
        }
    }

    private void downloadVideo()
    {
        try
        {
            ProgressBar progressbar = (ProgressBar)activity.findViewById(0x7f0e008e);
            TextView textview = (TextView)activity.findViewById(0x7f0e008f);
            showProgress();
            (new VideoDownloadTask(activity, this, new URL(highestBitrateVideoUrl), screenName, progressbar, textview)).execute(new Void[0]);
            return;
        }
        catch(MalformedURLException malformedurlexception)
        {
            malformedurlexception.printStackTrace();
        }
    }

    private int getButtonResourceId(View view)
    {
        switch(view.getId())
        {
        default:
            return 0;

        case 2131624083: 
            return 0x7f02006d;

        case 2131624084: 
            return 0x7f0200e6;

        case 2131624085: 
            return 0x7f0200d5;

        case 2131624086: 
            return 0x7f02009d;

        case 2131624087: 
            return 0x7f020091;
        }
    }

    private void pause(VideoView videoview)
    {
        videoview.pause();
        videoViewPosition = videoview.getCurrentPosition();
        ((ImageButton)activity.findViewById(0x7f0e0095)).setImageResource(theme.getThemeDrawableId(0x7f0200de));
    }

    private void resume(VideoView videoview)
    {
        videoview.seekTo(videoViewPosition);
        videoview.start();
        ((ImageButton)activity.findViewById(0x7f0e0095)).setImageResource(theme.getThemeDrawableId(0x7f0200d5));
    }

    private void seek(VideoView videoview, int i)
    {
        int j = videoview.getCurrentPosition();
        int k = videoview.getDuration();
        i *= 1000;
        if(j + i < 0)
        {
            videoview.seekTo(0);
            videoViewPosition = 0;
        } else
        if(j + i < k)
        {
            videoview.seekTo(j + i);
            videoViewPosition = j + i;
            return;
        }
    }

    private void setButtonProperties()
    {
        for(int i = 0; i < viewGroupCard.getChildCount(); i++)
        {
            View view = viewGroupCard.getChildAt(i);
            if(view instanceof ImageButton)
            {
                ImageButton imagebutton = (ImageButton)view;
                imagebutton.setImageResource(theme.getThemeDrawableId(getButtonResourceId(view)));
                imagebutton.setOnClickListener(this);
            }
        }

    }

    private void showProgress()
    {
        if(activity != null)
        {
            View view = activity.findViewById(0x7f0e008e);
            View view1 = activity.findViewById(0x7f0e008f);
            if(!isBackground)
            {
                if(view != null)
                    view.setVisibility(0);
                if(view1 != null)
                {
                    view1.setVisibility(0);
                    return;
                }
            }
        }
    }

    public void downloaded(File file)
    {
        dismissProgress();
        if(file != null)
        {
            Notificator.toast(activity, 0x7f07004a);
            (new UriResolver()).updateMediaScanner(activity, Uri.fromFile(file));
            return;
        } else
        {
            Notificator.toast(activity, 0x7f07007e);
            return;
        }
    }

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131624083 2131624083: default 24
    //                   2131624083 32;
           goto _L1 _L2
_L1:
        if(videoViewReference != null) goto _L4; else goto _L3
_L3:
        return;
_L2:
        VideoView videoview;
        activity.finish();
        continue; /* Loop/switch isn't completed */
_L4:
        if((videoview = (VideoView)videoViewReference.get()) == null) goto _L3; else goto _L5
_L5:
        switch(view.getId())
        {
        default:
            return;

        case 2131624084: 
            seek(videoview, -10);
            return;

        case 2131624085: 
            if(videoview.isPlaying())
            {
                pause(videoview);
                return;
            } else
            {
                resume(videoview);
                return;
            }

        case 2131624086: 
            seek(videoview, 10);
            return;

        case 2131624087: 
            break;
        }
        if(!PermissionUtil.checkSelfPermissions(activity, new String[] {
            "android.permission.WRITE_EXTERNAL_STORAGE"
        })) goto _L3; else goto _L6
_L6:
        try
        {
            (new GetContentLengthTask()).execute(new URL[] {
                new URL(highestBitrateVideoUrl)
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            view.printStackTrace();
        }
        return;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void setScreenName(String s)
    {
        screenName = s;
    }

    public void setVideoURL(String s)
    {
        highestBitrateVideoUrl = s;
    }

    public void setVideoViewReference(WeakReference weakreference)
    {
        videoViewReference = weakreference;
    }

    private static final int SEEK_SEC = 10;
    private VideoViewActivity activity;
    private String highestBitrateVideoUrl;
    private boolean isBackground;
    private String screenName;
    private ThemeManager theme;
    private int videoViewPosition;
    private WeakReference videoViewReference;
    private ViewGroup viewGroupCard;


}
