// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.util.List;

// Referenced classes of package twitter4j:
//            TwitterResponse, RateLimitStatus

public interface ResponseList
    extends TwitterResponse, List
{

    public abstract RateLimitStatus getRateLimitStatus();
}
