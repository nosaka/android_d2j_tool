// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.*;

public interface TimelinesResources
{

    public abstract ResponseList getHomeTimeline()
        throws TwitterException;

    public abstract ResponseList getHomeTimeline(Paging paging)
        throws TwitterException;

    public abstract ResponseList getMentionsTimeline()
        throws TwitterException;

    public abstract ResponseList getMentionsTimeline(Paging paging)
        throws TwitterException;

    public abstract ResponseList getRetweetsOfMe()
        throws TwitterException;

    public abstract ResponseList getRetweetsOfMe(Paging paging)
        throws TwitterException;

    public abstract ResponseList getUserTimeline()
        throws TwitterException;

    public abstract ResponseList getUserTimeline(long l)
        throws TwitterException;

    public abstract ResponseList getUserTimeline(long l, Paging paging)
        throws TwitterException;

    public abstract ResponseList getUserTimeline(String s)
        throws TwitterException;

    public abstract ResponseList getUserTimeline(String s, Paging paging)
        throws TwitterException;

    public abstract ResponseList getUserTimeline(Paging paging)
        throws TwitterException;
}
