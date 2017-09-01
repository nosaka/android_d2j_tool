// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import twitter4j.auth.Authorization;
import twitter4j.conf.Configuration;
import twitter4j.util.function.Consumer;

// Referenced classes of package twitter4j:
//            TwitterException, RateLimitStatusListener

public interface TwitterBase
{

    public abstract void addRateLimitStatusListener(RateLimitStatusListener ratelimitstatuslistener);

    public abstract Authorization getAuthorization();

    public abstract Configuration getConfiguration();

    public abstract long getId()
        throws TwitterException, IllegalStateException;

    public abstract String getScreenName()
        throws TwitterException, IllegalStateException;

    public abstract void onRateLimitReached(Consumer consumer);

    public abstract void onRateLimitStatus(Consumer consumer);
}
