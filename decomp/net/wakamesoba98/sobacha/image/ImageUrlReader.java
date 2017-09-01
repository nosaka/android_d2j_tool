// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.media.EnumMediaProvider;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.Twitter;
import twitter4j.TwitterException;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            ImageStreamReader

class ImageUrlReader extends ImageStreamReader
{

    ImageUrlReader()
    {
    }

    private InputStream openInputStream(Context context, MediaURL mediaurl, boolean flag, UserAccount useraccount)
        throws IOException, TwitterException
    {
        URL url = mediaurl.getUrl(flag);
        if(mediaurl.getProvider() == EnumMediaProvider.TWITTER_DM && useraccount != null)
            return (new TwitterUtils()).getTwitterInstance(context, useraccount.getAccessToken(context)).getDMImageAsStream(url.toString());
        else
            return url.openStream();
    }

    public Bitmap getBitmap(Context context, MediaURL mediaurl, boolean flag, UserAccount useraccount, int i)
    {
        context = getBitmapFromStream(openInputStream(context, mediaurl, flag, useraccount), calcSampleSize(openInputStream(context, mediaurl, flag, useraccount), i));
        return context;
        context;
_L2:
        context.printStackTrace();
        return null;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
