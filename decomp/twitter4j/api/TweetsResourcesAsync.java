// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.OEmbedRequest;
import twitter4j.StatusUpdate;

public interface TweetsResourcesAsync
{

    public abstract void destroyStatus(long l);

    public abstract void getOEmbed(OEmbedRequest oembedrequest);

    public abstract void getRetweets(long l);

    public transient abstract void lookup(long al[]);

    public abstract void retweetStatus(long l);

    public abstract void showStatus(long l);

    public abstract void updateStatus(String s);

    public abstract void updateStatus(StatusUpdate statusupdate);
}
