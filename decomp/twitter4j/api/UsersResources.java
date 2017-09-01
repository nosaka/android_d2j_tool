// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;

import java.io.File;
import java.io.InputStream;
import twitter4j.*;

public interface UsersResources
{

    public abstract User createBlock(long l)
        throws TwitterException;

    public abstract User createBlock(String s)
        throws TwitterException;

    public abstract User createMute(long l)
        throws TwitterException;

    public abstract User createMute(String s)
        throws TwitterException;

    public abstract User destroyBlock(long l)
        throws TwitterException;

    public abstract User destroyBlock(String s)
        throws TwitterException;

    public abstract User destroyMute(long l)
        throws TwitterException;

    public abstract User destroyMute(String s)
        throws TwitterException;

    public abstract AccountSettings getAccountSettings()
        throws TwitterException;

    public abstract IDs getBlocksIDs()
        throws TwitterException;

    public abstract IDs getBlocksIDs(long l)
        throws TwitterException;

    public abstract PagableResponseList getBlocksList()
        throws TwitterException;

    public abstract PagableResponseList getBlocksList(long l)
        throws TwitterException;

    public abstract ResponseList getContributees(long l)
        throws TwitterException;

    public abstract ResponseList getContributees(String s)
        throws TwitterException;

    public abstract ResponseList getContributors(long l)
        throws TwitterException;

    public abstract ResponseList getContributors(String s)
        throws TwitterException;

    public abstract IDs getMutesIDs(long l)
        throws TwitterException;

    public abstract PagableResponseList getMutesList(long l)
        throws TwitterException;

    public transient abstract ResponseList lookupUsers(long al[])
        throws TwitterException;

    public transient abstract ResponseList lookupUsers(String as[])
        throws TwitterException;

    public abstract void removeProfileBanner()
        throws TwitterException;

    public abstract ResponseList searchUsers(String s, int i)
        throws TwitterException;

    public abstract User showUser(long l)
        throws TwitterException;

    public abstract User showUser(String s)
        throws TwitterException;

    public abstract AccountSettings updateAccountSettings(Integer integer, Boolean boolean1, String s, String s1, String s2, String s3)
        throws TwitterException;

    public abstract User updateProfile(String s, String s1, String s2, String s3)
        throws TwitterException;

    public abstract User updateProfileBackgroundImage(File file, boolean flag)
        throws TwitterException;

    public abstract User updateProfileBackgroundImage(InputStream inputstream, boolean flag)
        throws TwitterException;

    public abstract void updateProfileBanner(File file)
        throws TwitterException;

    public abstract void updateProfileBanner(InputStream inputstream)
        throws TwitterException;

    public abstract User updateProfileColors(String s, String s1, String s2, String s3, String s4)
        throws TwitterException;

    public abstract User updateProfileImage(File file)
        throws TwitterException;

    public abstract User updateProfileImage(InputStream inputstream)
        throws TwitterException;

    public abstract User verifyCredentials()
        throws TwitterException;
}
