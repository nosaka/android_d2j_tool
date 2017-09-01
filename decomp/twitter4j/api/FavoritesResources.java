// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.*;

public interface FavoritesResources
{

    public abstract Status createFavorite(long l)
        throws TwitterException;

    public abstract Status destroyFavorite(long l)
        throws TwitterException;

    public abstract ResponseList getFavorites()
        throws TwitterException;

    public abstract ResponseList getFavorites(long l)
        throws TwitterException;

    public abstract ResponseList getFavorites(long l, Paging paging)
        throws TwitterException;

    public abstract ResponseList getFavorites(String s)
        throws TwitterException;

    public abstract ResponseList getFavorites(String s, Paging paging)
        throws TwitterException;

    public abstract ResponseList getFavorites(Paging paging)
        throws TwitterException;
}
