// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.GeoLocation;

public interface TrendsResourcesAsync
{

    public abstract void getAvailableTrends();

    public abstract void getClosestTrends(GeoLocation geolocation);

    public abstract void getPlaceTrends(int i);
}
