// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            RateLimitStatus

public interface TwitterResponse
    extends Serializable
{

    public abstract int getAccessLevel();

    public abstract RateLimitStatus getRateLimitStatus();

    public static final int NONE = 0;
    public static final int READ = 1;
    public static final int READ_WRITE = 2;
    public static final int READ_WRITE_DIRECTMESSAGES = 3;
}
