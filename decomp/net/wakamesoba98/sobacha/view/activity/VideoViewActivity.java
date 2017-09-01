// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.*;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.image.VideoCacheTask;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.twitter.listener.OnDownloadedListener;
import net.wakamesoba98.sobacha.view.activity.base.TranslucentThemeFragmentActivity;
import net.wakamesoba98.sobacha.view.card.PlayerCardManager;

public class VideoViewActivity extends TranslucentThemeFragmentActivity
    implements OnDownloadedListener
{

    public VideoViewActivity()
    {
    }

    private void cacheVideo(String s)
    {
        try
        {
            videoCacheTask = new VideoCacheTask(this, this, new URL(s), progressBar, textViewProgress);
            videoCacheTask.execute(new Void[0]);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
        }
        Notificator.toast(this, 0x7f070084);
        finish();
    }

    private void cardInitialize(String s)
    {
        playerCardManager = new PlayerCardManager(this);
        playerCardManager.setScreenName(s);
    }

    private void requestPermission(String s, int i)
    {
        if(SystemVersion.isMarshmallowOrLater())
            (new RequestPermission()).request(this, s, i);
    }

    public void downloaded(File file)
    {
        cacheFile = file;
        progressBar.setVisibility(8);
        textViewProgress.setVisibility(8);
        VideoView videoview = (VideoView)videoViewReference.get();
        if(file != null && videoview != null)
        {
            videoview.setVisibility(0);
            videoview.setOnErrorListener(new android.media.MediaPlayer.OnErrorListener() {

                public boolean onError(MediaPlayer mediaplayer, int i, int j)
                {
                    Notificator.toast(VideoViewActivity.this, 0x7f070084);
                    finish();
                    return false;
                }

                final VideoViewActivity this$0;

            
            {
                this$0 = VideoViewActivity.this;
                super();
            }
            }
);
            videoview.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mediaplayer)
                {
                    mediaplayer.setLooping(true);
                    mediaplayer.start();
                }

                final VideoViewActivity this$0;

            
            {
                this$0 = VideoViewActivity.this;
                super();
            }
            }
);
            videoview.setVideoPath(file.getPath());
            playerCardManager.setVideoURL(highestBitrateVideoUrl);
            playerCardManager.setVideoViewReference(videoViewReference);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030027);
        bundle = (VideoView)findViewById(0x7f0e008d);
        bundle.setVisibility(4);
        videoViewReference = new WeakReference(bundle);
        textViewProgress = (TextView)findViewById(0x7f0e008f);
        progressBar = (ProgressBar)findViewById(0x7f0e008e);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        bundle = getIntent().getExtras();
        String s = bundle.getString("screen_name");
        lowestBitrateVideoUrl = bundle.getString("lowest_bitrate_video_url");
        highestBitrateVideoUrl = bundle.getString("highest_bitrate_video_url");
        cardInitialize(s);
        if(PermissionUtil.checkSelfPermissions(this, new String[] {
    "android.permission.WRITE_EXTERNAL_STORAGE"
}))
        {
            cacheVideo(lowestBitrateVideoUrl);
            return;
        } else
        {
            Notificator.toast(this, 0x7f07002e);
            requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 1);
            return;
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(videoCacheTask != null)
            videoCacheTask.cancel(false);
        VideoView videoview = (VideoView)videoViewReference.get();
        if(videoview != null)
            videoview.stopPlayback();
        if(cacheFile != null && cacheFile.exists())
            cacheFile.delete();
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
label0:
        {
            super.onRequestPermissionsResult(i, as, ai);
            if(i == 1 && ai.length > 0)
            {
                if(!PermissionUtil.verifyPermissions(ai))
                    break label0;
                cacheVideo(lowestBitrateVideoUrl);
            }
            return;
        }
        Notificator.toast(this, 0x7f070082);
        finish();
    }

    public static final int PERMISSION_REQUEST_VIDEO = 1;
    private File cacheFile;
    private String highestBitrateVideoUrl;
    private String lowestBitrateVideoUrl;
    private PlayerCardManager playerCardManager;
    private ProgressBar progressBar;
    private TextView textViewProgress;
    private VideoCacheTask videoCacheTask;
    private WeakReference videoViewReference;
}
