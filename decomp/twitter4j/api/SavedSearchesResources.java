// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.*;

public interface SavedSearchesResources
{

    public abstract SavedSearch createSavedSearch(String s)
        throws TwitterException;

    public abstract SavedSearch destroySavedSearch(long l)
        throws TwitterException;

    public abstract ResponseList getSavedSearches()
        throws TwitterException;

    public abstract SavedSearch showSavedSearch(long l)
        throws TwitterException;
}
