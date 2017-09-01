// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database.data;

import net.wakamesoba98.sobacha.compatible.HttpsMediaURL;
import twitter4j.User;

public class AccountData
{

    public AccountData(long l, String s, String s1, String s2)
    {
        userId = l;
        screenName = s;
        userName = s1;
        iconUrl = s2;
    }

    public AccountData(long l, String s, String s1, String s2, String s3, String s4)
    {
        userId = l;
        token = s3;
        tokenSecret = s4;
        screenName = s;
        userName = s1;
        iconUrl = s2;
    }

    public AccountData(User user)
    {
        userId = user.getId();
        screenName = user.getScreenName();
        userName = user.getName();
        iconUrl = HttpsMediaURL.getProfileImageURL(user);
    }

    public AccountData(User user, String s, String s1)
    {
        userId = user.getId();
        token = s;
        tokenSecret = s1;
        screenName = user.getScreenName();
        userName = user.getName();
        iconUrl = HttpsMediaURL.getProfileImageURL(user);
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public String getScreenName()
    {
        return screenName;
    }

    public String getToken()
    {
        return token;
    }

    public String getTokenSecret()
    {
        return tokenSecret;
    }

    public long getUserId()
    {
        return userId;
    }

    public String getUserName()
    {
        return userName;
    }

    private String iconUrl;
    private String screenName;
    private String token;
    private String tokenSecret;
    private long userId;
    private String userName;
}
