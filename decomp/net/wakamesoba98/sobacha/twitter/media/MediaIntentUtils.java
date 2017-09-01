// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.menu.StatusUrlMenu;
import net.wakamesoba98.sobacha.view.activity.*;
import twitter4j.ExtendedMediaEntity;
import twitter4j.MediaEntity;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.media:
//            MediaURL, EnumMediaProvider

public class MediaIntentUtils
{

    public MediaIntentUtils()
    {
    }

    private boolean isMediaURL(String s)
    {
        s = s.toLowerCase();
        return s.matches("https?://pbs\\.twimg\\.com/media/[a-zA-Z0-9_\\-]+\\.\\w+") || s.matches("https?://twitpic\\.com/\\w+") || s.matches("https?://yfrog\\.com/\\w+") || s.matches("https?://instagram\\.com/p/[a-zA-Z0-9_\\-/]+") || s.matches("https?://p\\.twipple\\.jp/\\w+") || s.matches("https?://imgur\\.com/\\w+") || s.matches("https?://img\\.ly/\\w+") || s.matches("https?://via\\.me/[a-zA-Z0-9_\\-]+") || s.matches("https?://www\\.flickr\\.com/photos/[a-zA-Z0-9_@\\-/]+") || s.matches("https?://flic\\.kr/\\w/\\w+") || s.endsWith(".jpg") || s.endsWith(".jpeg") || s.endsWith(".png") || s.endsWith(".gif") || s.endsWith(".mp4");
    }

    private void showImage(Activity activity, String as[], String s)
    {
        Bundle bundle = new Bundle();
        bundle.putStringArray("image_urls", as);
        bundle.putString("screen_name", s);
        bundle.putInt("position", 0);
        as = new Intent(activity, net/wakamesoba98/sobacha/view/activity/ImageViewerActivity);
        as.putExtras(bundle);
        as.setFlags(0x10000000);
        activity.startActivity(as);
        if(Flavor.isMateCha())
            activity.overridePendingTransition(0x7f040010, 0x7f040012);
    }

    private void showVideo(Context context, String s, String s1, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putString("lowest_bitrate_video_url", s);
        bundle.putString("highest_bitrate_video_url", s1);
        bundle.putString("screen_name", s2);
        s = new Intent(context, net/wakamesoba98/sobacha/view/activity/VideoViewActivity);
        s.putExtras(bundle);
        s.setFlags(0x10000000);
        context.startActivity(s);
    }

    public void startActivityByMediaStatusURL(ViewPagerActivity viewpageractivity, String s, String s1, MediaEntity amediaentity[], ExtendedMediaEntity aextendedmediaentity[], View view)
    {
        int i = 0;
        if(s.matches("https?://(mobile\\.)?twitter\\.com/[a-zA-Z0-9_]+/status/\\d+/photo/.+") || s.matches("https?://(mobile\\.)?twitter\\.com/messages/media/\\d+"))
        {
            s = new ArrayList();
            if(aextendedmediaentity.length > 1)
            {
                for(int j = aextendedmediaentity.length; i < j; i++)
                    s.add(HttpsMediaURL.getMediaURL(aextendedmediaentity[i]));

            } else
            if(amediaentity.length > 0)
                s.add(HttpsMediaURL.getMediaURL(amediaentity[0]));
            if(s.size() > 0)
                showImage(viewpageractivity, (String[])s.toArray(new String[s.size()]), s1);
        } else
        if(s.matches("https?://(mobile\\.)?twitter\\.com/[a-zA-Z0-9_]+/status/\\d+/video/.+"))
        {
            s = new MediaURL(amediaentity, aextendedmediaentity);
            if(s.getProvider() == EnumMediaProvider.VIDEO_MP4)
            {
                showVideo(viewpageractivity, s.getUrl(false).toString(), s.getOriginalUrl().toString(), s1);
                return;
            }
        } else
        {
            if(isMediaURL(s))
                if(s.endsWith(".mp4") && SystemVersion.isIcsOrLater())
                {
                    showVideo(viewpageractivity, s, s, s1);
                    return;
                } else
                {
                    showImage(viewpageractivity, new String[] {
                        s
                    }, s1);
                    return;
                }
            if(s.matches("https?://(mobile\\.)?twitter\\.com/(i/)?[a-zA-Z0-9_]+/status/\\d+"))
            {
                (new StatusUrlMenu(viewpageractivity, s)).show(view);
                return;
            } else
            {
                viewpageractivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                return;
            }
        }
    }

    private static final String PIC_TWITTER_MESSAGES_PHOTO = "https?://(mobile\\.)?twitter\\.com/messages/media/\\d+";
    private static final String PIC_TWITTER_STATUS_PHOTO = "https?://(mobile\\.)?twitter\\.com/[a-zA-Z0-9_]+/status/\\d+/photo/.+";
    private static final String PIC_TWITTER_STATUS_VIDEO = "https?://(mobile\\.)?twitter\\.com/[a-zA-Z0-9_]+/status/\\d+/video/.+";
}
