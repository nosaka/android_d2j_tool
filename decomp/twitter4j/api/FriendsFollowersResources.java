// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.*;

public interface FriendsFollowersResources
{

    public abstract User createFriendship(long l)
        throws TwitterException;

    public abstract User createFriendship(long l, boolean flag)
        throws TwitterException;

    public abstract User createFriendship(String s)
        throws TwitterException;

    public abstract User createFriendship(String s, boolean flag)
        throws TwitterException;

    public abstract User destroyFriendship(long l)
        throws TwitterException;

    public abstract User destroyFriendship(String s)
        throws TwitterException;

    public abstract IDs getFollowersIDs(long l)
        throws TwitterException;

    public abstract IDs getFollowersIDs(long l, long l1)
        throws TwitterException;

    public abstract IDs getFollowersIDs(long l, long l1, int i)
        throws TwitterException;

    public abstract IDs getFollowersIDs(String s, long l)
        throws TwitterException;

    public abstract IDs getFollowersIDs(String s, long l, int i)
        throws TwitterException;

    public abstract PagableResponseList getFollowersList(long l, long l1)
        throws TwitterException;

    public abstract PagableResponseList getFollowersList(long l, long l1, int i)
        throws TwitterException;

    public abstract PagableResponseList getFollowersList(long l, long l1, int i, boolean flag, boolean flag1)
        throws TwitterException;

    public abstract PagableResponseList getFollowersList(String s, long l)
        throws TwitterException;

    public abstract PagableResponseList getFollowersList(String s, long l, int i)
        throws TwitterException;

    public abstract PagableResponseList getFollowersList(String s, long l, int i, boolean flag, boolean flag1)
        throws TwitterException;

    public abstract IDs getFriendsIDs(long l)
        throws TwitterException;

    public abstract IDs getFriendsIDs(long l, long l1)
        throws TwitterException;

    public abstract IDs getFriendsIDs(long l, long l1, int i)
        throws TwitterException;

    public abstract IDs getFriendsIDs(String s, long l)
        throws TwitterException;

    public abstract IDs getFriendsIDs(String s, long l, int i)
        throws TwitterException;

    public abstract PagableResponseList getFriendsList(long l, long l1)
        throws TwitterException;

    public abstract PagableResponseList getFriendsList(long l, long l1, int i)
        throws TwitterException;

    public abstract PagableResponseList getFriendsList(long l, long l1, int i, boolean flag, boolean flag1)
        throws TwitterException;

    public abstract PagableResponseList getFriendsList(String s, long l)
        throws TwitterException;

    public abstract PagableResponseList getFriendsList(String s, long l, int i)
        throws TwitterException;

    public abstract PagableResponseList getFriendsList(String s, long l, int i, boolean flag, boolean flag1)
        throws TwitterException;

    public abstract IDs getIncomingFriendships(long l)
        throws TwitterException;

    public abstract IDs getNoRetweetsFriendships()
        throws TwitterException;

    public abstract IDs getOutgoingFriendships(long l)
        throws TwitterException;

    public transient abstract ResponseList lookupFriendships(long al[])
        throws TwitterException;

    public transient abstract ResponseList lookupFriendships(String as[])
        throws TwitterException;

    public abstract Relationship showFriendship(long l, long l1)
        throws TwitterException;

    public abstract Relationship showFriendship(String s, String s1)
        throws TwitterException;

    public abstract Relationship updateFriendship(long l, boolean flag, boolean flag1)
        throws TwitterException;

    public abstract Relationship updateFriendship(String s, boolean flag, boolean flag1)
        throws TwitterException;
}
