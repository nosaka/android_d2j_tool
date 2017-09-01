// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            TwitterResponse

public interface AccountTotals
    extends TwitterResponse, Serializable
{

    public abstract int getFavorites();

    public abstract int getFollowers();

    public abstract int getFriends();

    public abstract int getUpdates();
}
