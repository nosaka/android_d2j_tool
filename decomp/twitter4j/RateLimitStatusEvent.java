// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.util.EventObject;

// Referenced classes of package twitter4j:
//            RateLimitStatus

public final class RateLimitStatusEvent extends EventObject
{

    RateLimitStatusEvent(Object obj, RateLimitStatus ratelimitstatus, boolean flag)
    {
        super(obj);
        rateLimitStatus = ratelimitstatus;
        isAccountRateLimitStatus = flag;
    }

    public RateLimitStatus getRateLimitStatus()
    {
        return rateLimitStatus;
    }

    public boolean isAccountRateLimitStatus()
    {
        return isAccountRateLimitStatus;
    }

    public boolean isIPRateLimitStatus()
    {
        return !isAccountRateLimitStatus;
    }

    private static final long serialVersionUID = 0x354c712eL;
    private final boolean isAccountRateLimitStatus;
    private final RateLimitStatus rateLimitStatus;
}
