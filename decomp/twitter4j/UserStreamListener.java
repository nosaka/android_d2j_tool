// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            StatusListener, User, DirectMessage, Status, 
//            UserList

public interface UserStreamListener
    extends StatusListener
{

    public abstract void onBlock(User user, User user1);

    public abstract void onDeletionNotice(long l, long l1);

    public abstract void onDirectMessage(DirectMessage directmessage);

    public abstract void onFavorite(User user, User user1, Status status);

    public abstract void onFavoritedRetweet(User user, User user1, Status status);

    public abstract void onFollow(User user, User user1);

    public abstract void onFriendList(long al[]);

    public abstract void onQuotedTweet(User user, User user1, Status status);

    public abstract void onRetweetedRetweet(User user, User user1, Status status);

    public abstract void onUnblock(User user, User user1);

    public abstract void onUnfavorite(User user, User user1, Status status);

    public abstract void onUnfollow(User user, User user1);

    public abstract void onUserDeletion(long l);

    public abstract void onUserListCreation(User user, UserList userlist);

    public abstract void onUserListDeletion(User user, UserList userlist);

    public abstract void onUserListMemberAddition(User user, User user1, UserList userlist);

    public abstract void onUserListMemberDeletion(User user, User user1, UserList userlist);

    public abstract void onUserListSubscription(User user, User user1, UserList userlist);

    public abstract void onUserListUnsubscription(User user, User user1, UserList userlist);

    public abstract void onUserListUpdate(User user, UserList userlist);

    public abstract void onUserProfileUpdate(User user);

    public abstract void onUserSuspension(long l);
}
