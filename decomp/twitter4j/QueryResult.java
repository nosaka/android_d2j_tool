// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.List;

// Referenced classes of package twitter4j:
//            TwitterResponse, Query

public interface QueryResult
    extends TwitterResponse, Serializable
{

    public abstract double getCompletedIn();

    public abstract int getCount();

    public abstract long getMaxId();

    public abstract String getQuery();

    public abstract String getRefreshURL();

    public abstract long getSinceId();

    public abstract List getTweets();

    public abstract boolean hasNext();

    public abstract Query nextQuery();
}
