// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.compatible;

import twitter4j.MediaEntity;
import twitter4j.User;

public class HttpsMediaURL
{

    public HttpsMediaURL()
    {
    }

    public static String getBiggerProfileImageURL(User user)
    {
        return user.getBiggerProfileImageURLHttps();
    }

    public static String getMediaURL(MediaEntity mediaentity)
    {
        return mediaentity.getMediaURLHttps();
    }

    public static String getMiniProfileImageURL(User user)
    {
        return user.getMiniProfileImageURLHttps();
    }

    public static String getOriginalProfileImageURL(User user)
    {
        return user.getOriginalProfileImageURLHttps();
    }

    public static String getProfileImageURL(User user)
    {
        return user.getProfileImageURLHttps();
    }
}
