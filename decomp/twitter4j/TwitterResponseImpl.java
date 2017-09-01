// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            TwitterResponse, RateLimitStatusJSONImpl, ParseUtil, RateLimitStatus, 
//            HttpResponse

abstract class TwitterResponseImpl
    implements TwitterResponse, Serializable
{

    public TwitterResponseImpl()
    {
        rateLimitStatus = null;
        accessLevel = 0;
    }

    public TwitterResponseImpl(HttpResponse httpresponse)
    {
        rateLimitStatus = null;
        rateLimitStatus = RateLimitStatusJSONImpl.createFromResponseHeader(httpresponse);
        accessLevel = ParseUtil.toAccessLevel(httpresponse);
    }

    public int getAccessLevel()
    {
        return accessLevel;
    }

    public RateLimitStatus getRateLimitStatus()
    {
        return rateLimitStatus;
    }

    private static final long serialVersionUID = 0x500789e0L;
    private final transient int accessLevel;
    private transient RateLimitStatus rateLimitStatus;
}
