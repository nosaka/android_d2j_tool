// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            TwitterResponseImpl, AccountTotals, ParseUtil, TwitterException, 
//            HttpResponse, TwitterObjectFactory, JSONObject

class AccountTotalsJSONImpl extends TwitterResponseImpl
    implements AccountTotals, Serializable
{

    private AccountTotalsJSONImpl(HttpResponse httpresponse, JSONObject jsonobject)
    {
        super(httpresponse);
        updates = ParseUtil.getInt("updates", jsonobject);
        followers = ParseUtil.getInt("followers", jsonobject);
        favorites = ParseUtil.getInt("favorites", jsonobject);
        friends = ParseUtil.getInt("friends", jsonobject);
    }

    AccountTotalsJSONImpl(HttpResponse httpresponse, Configuration configuration)
        throws TwitterException
    {
        this(httpresponse, httpresponse.asJSONObject());
        if(configuration.isJSONStoreEnabled())
        {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, httpresponse.asJSONObject());
        }
    }

    AccountTotalsJSONImpl(JSONObject jsonobject)
        throws TwitterException
    {
        this(null, jsonobject);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (AccountTotalsJSONImpl)obj;
            if(favorites != ((AccountTotalsJSONImpl) (obj)).favorites)
                return false;
            if(followers != ((AccountTotalsJSONImpl) (obj)).followers)
                return false;
            if(friends != ((AccountTotalsJSONImpl) (obj)).friends)
                return false;
            if(updates != ((AccountTotalsJSONImpl) (obj)).updates)
                return false;
        }
        return true;
    }

    public int getFavorites()
    {
        return favorites;
    }

    public int getFollowers()
    {
        return followers;
    }

    public int getFriends()
    {
        return friends;
    }

    public int getUpdates()
    {
        return updates;
    }

    public int hashCode()
    {
        return ((updates * 31 + followers) * 31 + favorites) * 31 + friends;
    }

    public String toString()
    {
        return (new StringBuilder()).append("AccountTotalsJSONImpl{updates=").append(updates).append(", followers=").append(followers).append(", favorites=").append(favorites).append(", friends=").append(friends).append('}').toString();
    }

    private static final long serialVersionUID = 0x5e45b544L;
    private final int favorites;
    private final int followers;
    private final int friends;
    private final int updates;
}
