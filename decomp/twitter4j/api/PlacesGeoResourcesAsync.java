// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.GeoLocation;
import twitter4j.GeoQuery;

public interface PlacesGeoResourcesAsync
{

    public abstract void getGeoDetails(String s);

    public abstract void getSimilarPlaces(GeoLocation geolocation, String s, String s1, String s2);

    public abstract void reverseGeoCode(GeoQuery geoquery);

    public abstract void searchPlaces(GeoQuery geoquery);
}
