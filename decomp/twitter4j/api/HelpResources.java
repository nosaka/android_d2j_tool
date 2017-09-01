// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import java.io.Serializable;
import java.util.Map;
import twitter4j.*;

public interface HelpResources
{
    public static interface Language
        extends Serializable
    {

        public abstract String getCode();

        public abstract String getName();

        public abstract String getStatus();
    }


    public abstract TwitterAPIConfiguration getAPIConfiguration()
        throws TwitterException;

    public abstract ResponseList getLanguages()
        throws TwitterException;

    public abstract String getPrivacyPolicy()
        throws TwitterException;

    public abstract Map getRateLimitStatus()
        throws TwitterException;

    public transient abstract Map getRateLimitStatus(String as[])
        throws TwitterException;

    public abstract String getTermsOfService()
        throws TwitterException;
}
