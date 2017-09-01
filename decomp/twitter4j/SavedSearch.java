// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Date;

// Referenced classes of package twitter4j:
//            TwitterResponse

public interface SavedSearch
    extends Comparable, TwitterResponse, Serializable
{

    public abstract Date getCreatedAt();

    public abstract long getId();

    public abstract String getName();

    public abstract int getPosition();

    public abstract String getQuery();
}
