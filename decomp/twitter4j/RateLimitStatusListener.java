// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            RateLimitStatusEvent

public interface RateLimitStatusListener
{

    public abstract void onRateLimitReached(RateLimitStatusEvent ratelimitstatusevent);

    public abstract void onRateLimitStatus(RateLimitStatusEvent ratelimitstatusevent);
}
