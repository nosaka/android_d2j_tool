// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            TwitterResponse, TimeZone, Location

public interface AccountSettings
    extends TwitterResponse, Serializable
{

    public abstract String getLanguage();

    public abstract String getScreenName();

    public abstract String getSleepEndTime();

    public abstract String getSleepStartTime();

    public abstract TimeZone getTimeZone();

    public abstract Location[] getTrendLocations();

    public abstract boolean isAlwaysUseHttps();

    public abstract boolean isDiscoverableByEmail();

    public abstract boolean isGeoEnabled();

    public abstract boolean isSleepTimeEnabled();
}
