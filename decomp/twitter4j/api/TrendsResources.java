// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.*;

public interface TrendsResources
{

    public abstract ResponseList getAvailableTrends()
        throws TwitterException;

    public abstract ResponseList getClosestTrends(GeoLocation geolocation)
        throws TwitterException;

    public abstract Trends getPlaceTrends(int i)
        throws TwitterException;
}
