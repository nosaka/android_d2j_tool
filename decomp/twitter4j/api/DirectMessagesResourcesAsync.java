// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.Paging;

public interface DirectMessagesResourcesAsync
{

    public abstract void destroyDirectMessage(long l);

    public abstract void getDirectMessages();

    public abstract void getDirectMessages(Paging paging);

    public abstract void getSentDirectMessages();

    public abstract void getSentDirectMessages(Paging paging);

    public abstract void sendDirectMessage(long l, String s);

    public abstract void sendDirectMessage(String s, String s1);

    public abstract void showDirectMessage(long l);
}
