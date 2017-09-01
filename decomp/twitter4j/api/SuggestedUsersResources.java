// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.ResponseList;
import twitter4j.TwitterException;

public interface SuggestedUsersResources
{

    public abstract ResponseList getMemberSuggestions(String s)
        throws TwitterException;

    public abstract ResponseList getSuggestedUserCategories()
        throws TwitterException;

    public abstract ResponseList getUserSuggestions(String s)
        throws TwitterException;
}
