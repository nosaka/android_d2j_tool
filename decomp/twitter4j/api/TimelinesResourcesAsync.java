// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.Paging;

public interface TimelinesResourcesAsync
{

    public abstract void getHomeTimeline();

    public abstract void getHomeTimeline(Paging paging);

    public abstract void getMentions();

    public abstract void getMentions(Paging paging);

    public abstract void getRetweetsOfMe();

    public abstract void getRetweetsOfMe(Paging paging);

    public abstract void getUserTimeline();

    public abstract void getUserTimeline(long l);

    public abstract void getUserTimeline(long l, Paging paging);

    public abstract void getUserTimeline(String s);

    public abstract void getUserTimeline(String s, Paging paging);

    public abstract void getUserTimeline(Paging paging);
}
