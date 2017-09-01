// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import java.io.File;
import java.io.InputStream;
import twitter4j.*;

public interface TweetsResources
{

    public abstract Status destroyStatus(long l)
        throws TwitterException;

    public abstract OEmbed getOEmbed(OEmbedRequest oembedrequest)
        throws TwitterException;

    public abstract IDs getRetweeterIds(long l, int i, long l1)
        throws TwitterException;

    public abstract IDs getRetweeterIds(long l, long l1)
        throws TwitterException;

    public abstract ResponseList getRetweets(long l)
        throws TwitterException;

    public transient abstract ResponseList lookup(long al[])
        throws TwitterException;

    public abstract Status retweetStatus(long l)
        throws TwitterException;

    public abstract Status showStatus(long l)
        throws TwitterException;

    public abstract Status updateStatus(String s)
        throws TwitterException;

    public abstract Status updateStatus(StatusUpdate statusupdate)
        throws TwitterException;

    public abstract UploadedMedia uploadMedia(File file)
        throws TwitterException;

    public abstract UploadedMedia uploadMedia(String s, InputStream inputstream)
        throws TwitterException;
}
