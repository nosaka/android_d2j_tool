// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            StreamListener, User, StatusDeletionNotice, DirectMessage, 
//            Status, UserList

public interface SiteStreamsListener
    extends StreamListener
{

    public abstract void onBlock(long l, User user, User user1);

    public abstract void onDeletionNotice(long l, long l1, long l2);

    public abstract void onDeletionNotice(long l, StatusDeletionNotice statusdeletionnotice);

    public abstract void onDirectMessage(long l, DirectMessage directmessage);

    public abstract void onDisconnectionNotice(String s);

    public abstract void onException(Exception exception);

    public abstract void onFavorite(long l, User user, User user1, Status status);

    public abstract void onFavoritedRetweet(User user, User user1, Status status);

    public abstract void onFollow(long l, User user, User user1);

    public abstract void onFriendList(long l, long al[]);

    public abstract void onRetweetedRetweet(User user, User user1, Status status);

    public abstract void onStatus(long l, Status status);

    public abstract void onUnblock(long l, User user, User user1);

    public abstract void onUnfavorite(long l, User user, User user1, Status status);

    public abstract void onUnfollow(long l, User user, User user1);

    public abstract void onUserDeletion(long l, long l1);

    public abstract void onUserListCreation(long l, User user, UserList userlist);

    public abstract void onUserListDeletion(long l, User user, UserList userlist);

    public abstract void onUserListMemberAddition(long l, User user, User user1, UserList userlist);

    public abstract void onUserListMemberDeletion(long l, User user, User user1, UserList userlist);

    public abstract void onUserListSubscription(long l, User user, User user1, UserList userlist);

    public abstract void onUserListUnsubscription(long l, User user, User user1, UserList userlist);

    public abstract void onUserListUpdate(long l, User user, UserList userlist);

    public abstract void onUserProfileUpdate(long l, User user);

    public abstract void onUserSuspension(long l, long l1);
}
