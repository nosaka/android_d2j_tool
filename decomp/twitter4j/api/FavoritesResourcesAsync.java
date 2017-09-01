// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.Paging;

public interface FavoritesResourcesAsync
{

    public abstract void createFavorite(long l);

    public abstract void destroyFavorite(long l);

    public abstract void getFavorites();

    public abstract void getFavorites(long l);

    public abstract void getFavorites(long l, Paging paging);

    public abstract void getFavorites(String s);

    public abstract void getFavorites(String s, Paging paging);

    public abstract void getFavorites(Paging paging);
}
