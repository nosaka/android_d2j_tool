// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            SiteStreamsListener, User, StatusDeletionNotice, DirectMessage, 
//            Status, UserList

public class SiteStreamsAdapter
    implements SiteStreamsListener
{

    public SiteStreamsAdapter()
    {
    }

    public void onBlock(long l, User user, User user1)
    {
    }

    public void onDeletionNotice(long l, long l1, long l2)
    {
    }

    public void onDeletionNotice(long l, StatusDeletionNotice statusdeletionnotice)
    {
    }

    public void onDirectMessage(long l, DirectMessage directmessage)
    {
    }

    public void onDisconnectionNotice(String s)
    {
    }

    public void onException(Exception exception)
    {
    }

    public void onFavorite(long l, User user, User user1, Status status)
    {
    }

    public void onFavoritedRetweet(User user, User user1, Status status)
    {
    }

    public void onFollow(long l, User user, User user1)
    {
    }

    public void onFriendList(long l, long al[])
    {
    }

    public void onRetweetedRetweet(User user, User user1, Status status)
    {
    }

    public void onStatus(long l, Status status)
    {
    }

    public void onUnblock(long l, User user, User user1)
    {
    }

    public void onUnfavorite(long l, User user, User user1, Status status)
    {
    }

    public void onUnfollow(long l, User user, User user1)
    {
    }

    public void onUserDeletion(long l, long l1)
    {
    }

    public void onUserListCreation(long l, User user, UserList userlist)
    {
    }

    public void onUserListDeletion(long l, User user, UserList userlist)
    {
    }

    public void onUserListMemberAddition(long l, User user, User user1, UserList userlist)
    {
    }

    public void onUserListMemberDeletion(long l, User user, User user1, UserList userlist)
    {
    }

    public void onUserListSubscription(long l, User user, User user1, UserList userlist)
    {
    }

    public void onUserListUnsubscription(long l, User user, User user1, UserList userlist)
    {
    }

    public void onUserListUpdate(long l, User user, UserList userlist)
    {
    }

    public void onUserProfileUpdate(long l, User user)
    {
    }

    public void onUserSuspension(long l, long l1)
    {
    }
}
