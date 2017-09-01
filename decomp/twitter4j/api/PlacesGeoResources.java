// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.*;

public interface PlacesGeoResources
{

    public abstract Place getGeoDetails(String s)
        throws TwitterException;

    public abstract ResponseList getSimilarPlaces(GeoLocation geolocation, String s, String s1, String s2)
        throws TwitterException;

    public abstract ResponseList reverseGeoCode(GeoQuery geoquery)
        throws TwitterException;

    public abstract ResponseList searchPlaces(GeoQuery geoquery)
        throws TwitterException;
}
