// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            StreamListener, RawStreamListener

interface StreamImplementation
{

    public abstract void close();

    public abstract void next(StreamListener astreamlistener[], RawStreamListener arawstreamlistener[]);

    public abstract void onException(Exception exception, StreamListener astreamlistener[], RawStreamListener arawstreamlistener[]);
}
