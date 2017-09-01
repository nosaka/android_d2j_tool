// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.media;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import net.wakamesoba98.sobacha.compatible.HttpsMediaURL;
import twitter4j.ExtendedMediaEntity;
import twitter4j.MediaEntity;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.media:
//            EnumMediaProvider

public class MediaURL
{

    public MediaURL(String s)
    {
        try
        {
            if(s.matches("https?://pbs\\.twimg\\.com/media/[a-zA-Z0-9_\\-]+\\.\\w+"))
            {
                url = new URL(s);
                originalUrl = new URL((new StringBuilder()).append(s).append(":orig").toString());
                thumbnailUrl = new URL((new StringBuilder()).append(s).append(":thumb").toString());
                provider = EnumMediaProvider.TWITTER;
                return;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            return;
        }
        if(s.matches("https://ton\\.twitter\\.com/(1\\.1|i)/ton/data/dm/[a-zA-Z0-9/_\\-]+\\.\\w+"))
        {
            url = new URL(s);
            originalUrl = url;
            thumbnailUrl = new URL((new StringBuilder()).append(s).append(":thumb").toString());
            provider = EnumMediaProvider.TWITTER_DM;
            return;
        }
        if(s.matches("https?://twitpic\\.com/\\w+"))
        {
            url = new URL((new StringBuilder()).append(s.replaceAll("https?://twitpic\\.com/", "https://twitpic.com/show/large/")).append(".jpg").toString());
            originalUrl = url;
            thumbnailUrl = new URL((new StringBuilder()).append(s.replaceAll("https?://twitpic\\.com/", "https://twitpic.com/show/mini/")).append(".jpg").toString());
            provider = EnumMediaProvider.TWITPIC;
            return;
        }
        if(s.matches("https?://yfrog\\.com/\\w+"))
        {
            url = new URL((new StringBuilder()).append(s).append(":medium").toString());
            originalUrl = url;
            thumbnailUrl = new URL((new StringBuilder()).append(s).append(":small").toString());
            provider = EnumMediaProvider.YFROG;
            return;
        }
        if(!s.matches("https?://instagram\\.com/p/[a-zA-Z0-9_\\-/]+"))
            break MISSING_BLOCK_LABEL_517;
        if(!s.endsWith("/"))
            break MISSING_BLOCK_LABEL_444;
        url = new URL((new StringBuilder()).append(s).append("media/?size=l").toString());
        thumbnailUrl = new URL((new StringBuilder()).append(s).append("media/?size=t").toString());
_L2:
        originalUrl = url;
        provider = EnumMediaProvider.INSTAGRAM;
        return;
        url = new URL((new StringBuilder()).append(s).append("/").append("media/?size=l").toString());
        thumbnailUrl = new URL((new StringBuilder()).append(s).append("/").append("media/?size=t").toString());
        if(true) goto _L2; else goto _L1
_L1:
        if(s.matches("https?://p\\.twipple\\.jp/\\w+"))
        {
            url = new URL(s.replaceAll("https?://p\\.twipple\\.jp/", "http://p.twpl.jp/show/orig/"));
            originalUrl = url;
            thumbnailUrl = new URL(s.replaceAll("https?://p\\.twipple\\.jp/", "http://p.twpl.jp/show/thumb/"));
            provider = EnumMediaProvider.TWIPPLE;
            return;
        }
        if(s.matches("https?://imgur\\.com/\\w+"))
        {
            url = new URL((new StringBuilder()).append(s.replaceAll("https?://imgur\\.com/", "http://i.imgur.com/")).append(".jpg").toString());
            originalUrl = url;
            thumbnailUrl = new URL((new StringBuilder()).append(s.replaceAll("https?://imgur\\.com/", "http://i.imgur.com/")).append("s.jpg").toString());
            provider = EnumMediaProvider.IMGUR;
            return;
        }
        if(s.matches("https?://img\\.ly/\\w+"))
        {
            url = new URL(s.replaceAll("https?://img\\.ly/", "http://img.ly/show/large/"));
            originalUrl = url;
            thumbnailUrl = new URL(s.replaceAll("https?://img\\.ly/", "http://img.ly/show/thumb/"));
            provider = EnumMediaProvider.IMG_LY;
            return;
        }
        if(s.matches("https?://via\\.me/[a-zA-Z0-9_\\-]+"))
        {
            url = new URL(s);
            originalUrl = url;
            thumbnailUrl = new URL(s);
            provider = EnumMediaProvider.VIA_ME;
            return;
        }
        if(s.matches("https?://www\\.flickr\\.com/photos/[a-zA-Z0-9_@\\-/]+") || s.matches("https?://flic\\.kr/\\w/\\w+"))
        {
            url = new URL(s);
            originalUrl = url;
            thumbnailUrl = new URL(s);
            provider = EnumMediaProvider.FLICKR;
            return;
        }
        url = new URL(s);
        originalUrl = url;
        thumbnailUrl = new URL(s);
        provider = EnumMediaProvider.OTHER;
        return;
    }

    public MediaURL(URL url1, EnumMediaProvider enummediaprovider)
    {
        url = url1;
        provider = enummediaprovider;
    }

    public MediaURL(MediaEntity amediaentity[], ExtendedMediaEntity aextendedmediaentity[])
    {
        if(amediaentity.length > 0)
        {
            amediaentity = HttpsMediaURL.getMediaURL(amediaentity[0]);
            if(amediaentity.matches("https?://pbs\\.twimg\\.com/tweet_video_thumb/[a-zA-Z0-9_\\-]+\\.png"))
            {
                String s = amediaentity.replace("tweet_video_thumb", "tweet_video").replace("png", "mp4");
                try
                {
                    url = new URL(s);
                    originalUrl = url;
                    thumbnailUrl = new URL(amediaentity);
                    provider = EnumMediaProvider.VIDEO_MP4;
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(MediaEntity amediaentity[])
                {
                    amediaentity.printStackTrace();
                }
            }
        }
        if(aextendedmediaentity.length > 0)
        {
            int k = aextendedmediaentity.length;
            for(int i = 0; i < k; i++)
            {
                amediaentity = aextendedmediaentity[i];
                twitter4j.ExtendedMediaEntity.Variant avariant[] = amediaentity.getVideoVariants();
                if(avariant == null || avariant.length <= 0)
                    continue;
                Object obj = new TreeMap();
                ArrayList arraylist = new ArrayList();
                int l = avariant.length;
                for(int j = 0; j < l; j++)
                {
                    twitter4j.ExtendedMediaEntity.Variant variant = avariant[j];
                    String s2 = variant.getContentType();
                    String s3 = variant.getUrl();
                    int i1 = variant.getBitrate();
                    if(s2.equals("video/mp4"))
                    {
                        arraylist.add(Integer.valueOf(i1));
                        ((Map) (obj)).put(Integer.valueOf(i1), s3);
                    }
                }

                Collections.sort(arraylist);
                String s1 = (String)((Map) (obj)).get(arraylist.get(0));
                obj = (String)((Map) (obj)).get(arraylist.get(arraylist.size() - 1));
                try
                {
                    url = new URL(s1);
                    originalUrl = new URL(((String) (obj)));
                    thumbnailUrl = new URL((new StringBuilder()).append(HttpsMediaURL.getMediaURL(amediaentity)).append(":thumb").toString());
                    provider = EnumMediaProvider.VIDEO_MP4;
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(MediaEntity amediaentity[])
                {
                    amediaentity.printStackTrace();
                }
            }

        }
        provider = EnumMediaProvider.OTHER;
    }

    public URL getOriginalUrl()
    {
        return originalUrl;
    }

    public EnumMediaProvider getProvider()
    {
        return provider;
    }

    public URL getUrl(boolean flag)
    {
        if(flag)
            return thumbnailUrl;
        else
            return url;
    }

    static final String FLICKR = "https?://www\\.flickr\\.com/photos/[a-zA-Z0-9_@\\-/]+";
    static final String FLICKR_SHORT = "https?://flic\\.kr/\\w/\\w+";
    static final String IMGUR = "https?://imgur\\.com/\\w+";
    private static final String IMGUR_ADD = ".jpg";
    private static final String IMGUR_REPLACE = "http://i.imgur.com/";
    private static final String IMGUR_THUMB_ADD = "s.jpg";
    private static final String IMGUR_URL = "https?://imgur\\.com/";
    static final String IMG_LY = "https?://img\\.ly/\\w+";
    private static final String IMG_LY_REPLACE = "http://img.ly/show/large/";
    private static final String IMG_LY_THUMB_REPLACE = "http://img.ly/show/thumb/";
    private static final String IMG_LY_URL = "https?://img\\.ly/";
    static final String INSTAGRAM = "https?://instagram\\.com/p/[a-zA-Z0-9_\\-/]+";
    private static final String INSTAGRAM_ADD = "media/?size=l";
    private static final String INSTAGRAM_THUMB_ADD = "media/?size=t";
    static final String PIC_TWITTER = "https?://pbs\\.twimg\\.com/media/[a-zA-Z0-9_\\-]+\\.\\w+";
    private static final String PIC_TWITTER_GIF = "https?://pbs\\.twimg\\.com/tweet_video_thumb/[a-zA-Z0-9_\\-]+\\.png";
    private static final String PIC_TWITTER_GIF_REPLACE_1 = "tweet_video";
    private static final String PIC_TWITTER_GIF_REPLACE_2 = "mp4";
    private static final String PIC_TWITTER_GIF_URL_1 = "tweet_video_thumb";
    private static final String PIC_TWITTER_GIF_URL_2 = "png";
    private static final String PIC_TWITTER_ORIG_ADD = ":orig";
    private static final String PIC_TWITTER_THUMB_ADD = ":thumb";
    private static final String TON_TWITTER_DM = "https://ton\\.twitter\\.com/(1\\.1|i)/ton/data/dm/[a-zA-Z0-9/_\\-]+\\.\\w+";
    static final String TWIPPLE = "https?://p\\.twipple\\.jp/\\w+";
    private static final String TWIPPLE_REPLACE = "http://p.twpl.jp/show/orig/";
    private static final String TWIPPLE_THUMB_REPLACE = "http://p.twpl.jp/show/thumb/";
    private static final String TWIPPLE_URL = "https?://p\\.twipple\\.jp/";
    static final String TWITPIC = "https?://twitpic\\.com/\\w+";
    private static final String TWITPIC_ADD = ".jpg";
    private static final String TWITPIC_REPLACE = "https://twitpic.com/show/large/";
    private static final String TWITPIC_THUMB_REPLACE = "https://twitpic.com/show/mini/";
    private static final String TWITPIC_URL = "https?://twitpic\\.com/";
    static final String VIA_ME = "https?://via\\.me/[a-zA-Z0-9_\\-]+";
    static final String YFROG = "https?://yfrog\\.com/\\w+";
    private static final String YFROG_ADD = ":medium";
    private static final String YFROG_THUMB_ADD = ":small";
    private URL originalUrl;
    private EnumMediaProvider provider;
    private URL thumbnailUrl;
    private URL url;
}
