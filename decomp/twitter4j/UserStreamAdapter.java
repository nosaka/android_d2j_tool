// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            StatusAdapter, UserStreamListener, User, DirectMessage, 
//            Status, UserList

public class UserStreamAdapter extends StatusAdapter
    implements UserStreamListener
{

    public UserStreamAdapter()
    {
    }

    public void onBlock(User user, User user1)
    {
    }

    public void onDeletionNotice(long l, long l1)
    {
    }

    public void onDirectMessage(DirectMessage directmessage)
    {
    }

    public void onException(Exception exception)
    {
    }

    public void onFavorite(User user, User user1, Status status)
    {
    }

    public void onFavoritedRetweet(User user, User user1, Status status)
    {
    }

    public void onFollow(User user, User user1)
    {
    }

    public void onFriendList(long al[])
    {
    }

    public void onQuotedTweet(User user, User user1, Status status)
    {
    }

    public void onRetweetedRetweet(User user, User user1, Status status)
    {
    }

    public void onUnblock(User user, User user1)
    {
    }

    public void onUnfavorite(User user, User user1, Status status)
    {
    }

    public void onUnfollow(User user, User user1)
    {
    }

    public void onUserDeletion(long l)
    {
    }

    public void onUserListCreation(User user, UserList userlist)
    {
    }

    public void onUserListDeletion(User user, UserList userlist)
    {
    }

    public void onUserListMemberAddition(User user, User user1, UserList userlist)
    {
    }

    public void onUserListMemberDeletion(User user, User user1, UserList userlist)
    {
    }

    public void onUserListSubscription(User user, User user1, UserList userlist)
    {
    }

    public void onUserListUnsubscription(User user, User user1, UserList userlist)
    {
    }

    public void onUserListUpdate(User user, UserList userlist)
    {
    }

    public void onUserProfileUpdate(User user)
    {
    }

    public void onUserSuspension(long l)
    {
    }
}
