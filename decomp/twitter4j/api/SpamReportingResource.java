// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.TwitterException;
import twitter4j.User;

public interface SpamReportingResource
{

    public abstract User reportSpam(long l)
        throws TwitterException;

    public abstract User reportSpam(String s)
        throws TwitterException;
}
