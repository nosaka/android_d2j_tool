// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import twitter4j.*;

public interface ListsResources
{

    public abstract UserList createUserList(String s, boolean flag, String s1)
        throws TwitterException;

    public abstract UserList createUserListMember(long l, long l1)
        throws TwitterException;

    public abstract UserList createUserListMember(long l, String s, long l1)
        throws TwitterException;

    public abstract UserList createUserListMember(String s, String s1, long l)
        throws TwitterException;

    public transient abstract UserList createUserListMembers(long l, String s, long al[])
        throws TwitterException;

    public transient abstract UserList createUserListMembers(long l, String s, String as[])
        throws TwitterException;

    public transient abstract UserList createUserListMembers(long l, long al[])
        throws TwitterException;

    public transient abstract UserList createUserListMembers(long l, String as[])
        throws TwitterException;

    public transient abstract UserList createUserListMembers(String s, String s1, long al[])
        throws TwitterException;

    public transient abstract UserList createUserListMembers(String s, String s1, String as[])
        throws TwitterException;

    public abstract UserList createUserListSubscription(long l)
        throws TwitterException;

    public abstract UserList createUserListSubscription(long l, String s)
        throws TwitterException;

    public abstract UserList createUserListSubscription(String s, String s1)
        throws TwitterException;

    public abstract UserList destroyUserList(long l)
        throws TwitterException;

    public abstract UserList destroyUserList(long l, String s)
        throws TwitterException;

    public abstract UserList destroyUserList(String s, String s1)
        throws TwitterException;

    public abstract UserList destroyUserListMember(long l, long l1)
        throws TwitterException;

    public abstract UserList destroyUserListMember(long l, String s)
        throws TwitterException;

    public abstract UserList destroyUserListMember(long l, String s, long l1)
        throws TwitterException;

    public abstract UserList destroyUserListMember(String s, String s1, long l)
        throws TwitterException;

    public abstract UserList destroyUserListMembers(long l, long al[])
        throws TwitterException;

    public abstract UserList destroyUserListMembers(long l, String as[])
        throws TwitterException;

    public abstract UserList destroyUserListMembers(String s, String s1, String as[])
        throws TwitterException;

    public abstract UserList destroyUserListSubscription(long l)
        throws TwitterException;

    public abstract UserList destroyUserListSubscription(long l, String s)
        throws TwitterException;

    public abstract UserList destroyUserListSubscription(String s, String s1)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(long l, int i, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(long l, int i, long l1, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(long l, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(long l, String s, int i, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(long l, String s, int i, long l1, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(long l, String s, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(String s, String s1, int i, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(String s, String s1, int i, long l, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListMembers(String s, String s1, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(int i, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(long l, int i, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(long l, int i, long l1, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(long l, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(long l, long l1, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(String s, int i, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(String s, int i, long l, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(String s, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListMemberships(String s, long l, boolean flag)
        throws TwitterException;

    public abstract ResponseList getUserListStatuses(long l, String s, Paging paging)
        throws TwitterException;

    public abstract ResponseList getUserListStatuses(long l, Paging paging)
        throws TwitterException;

    public abstract ResponseList getUserListStatuses(String s, String s1, Paging paging)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(long l, int i, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(long l, int i, long l1, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(long l, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(long l, String s, int i, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(long l, String s, int i, long l1, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(long l, String s, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(String s, String s1, int i, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(String s, String s1, int i, long l, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscribers(String s, String s1, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscriptions(long l, int i, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscriptions(long l, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscriptions(String s, int i, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListSubscriptions(String s, long l)
        throws TwitterException;

    public abstract ResponseList getUserLists(long l)
        throws TwitterException;

    public abstract ResponseList getUserLists(long l, boolean flag)
        throws TwitterException;

    public abstract ResponseList getUserLists(String s)
        throws TwitterException;

    public abstract ResponseList getUserLists(String s, boolean flag)
        throws TwitterException;

    public abstract PagableResponseList getUserListsOwnerships(long l, int i, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListsOwnerships(long l, long l1)
        throws TwitterException;

    public abstract PagableResponseList getUserListsOwnerships(String s, int i, long l)
        throws TwitterException;

    public abstract PagableResponseList getUserListsOwnerships(String s, long l)
        throws TwitterException;

    public abstract UserList showUserList(long l)
        throws TwitterException;

    public abstract UserList showUserList(long l, String s)
        throws TwitterException;

    public abstract UserList showUserList(String s, String s1)
        throws TwitterException;

    public abstract User showUserListMembership(long l, long l1)
        throws TwitterException;

    public abstract User showUserListMembership(long l, String s, long l1)
        throws TwitterException;

    public abstract User showUserListMembership(String s, String s1, long l)
        throws TwitterException;

    public abstract User showUserListSubscription(long l, long l1)
        throws TwitterException;

    public abstract User showUserListSubscription(long l, String s, long l1)
        throws TwitterException;

    public abstract User showUserListSubscription(String s, String s1, long l)
        throws TwitterException;

    public abstract UserList updateUserList(long l, String s, String s1, boolean flag, String s2)
        throws TwitterException;

    public abstract UserList updateUserList(long l, String s, boolean flag, String s1)
        throws TwitterException;

    public abstract UserList updateUserList(String s, String s1, String s2, boolean flag, String s3)
        throws TwitterException;
}
