// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Date;

// Referenced classes of package twitter4j:
//            TwitterResponse, Location, Trend

public interface Trends
    extends TwitterResponse, Comparable, Serializable
{

    public abstract Date getAsOf();

    public abstract Location getLocation();

    public abstract Date getTrendAt();

    public abstract Trend[] getTrends();
}
