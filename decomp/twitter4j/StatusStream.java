// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.IOException;

// Referenced classes of package twitter4j:
//            TwitterException, StatusListener

interface StatusStream
{

    public abstract void close()
        throws IOException;

    public abstract void next(StatusListener statuslistener)
        throws TwitterException;
}
