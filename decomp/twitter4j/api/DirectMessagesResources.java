// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import java.io.InputStream;
import twitter4j.*;

public interface DirectMessagesResources
{

    public abstract DirectMessage destroyDirectMessage(long l)
        throws TwitterException;

    public abstract InputStream getDMImageAsStream(String s)
        throws TwitterException;

    public abstract ResponseList getDirectMessages()
        throws TwitterException;

    public abstract ResponseList getDirectMessages(Paging paging)
        throws TwitterException;

    public abstract ResponseList getSentDirectMessages()
        throws TwitterException;

    public abstract ResponseList getSentDirectMessages(Paging paging)
        throws TwitterException;

    public abstract DirectMessage sendDirectMessage(long l, String s)
        throws TwitterException;

    public abstract DirectMessage sendDirectMessage(String s, String s1)
        throws TwitterException;

    public abstract DirectMessage showDirectMessage(long l)
        throws TwitterException;
}
