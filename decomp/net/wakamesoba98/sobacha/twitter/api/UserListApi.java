// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.*;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

public class UserListApi
{

    public UserListApi(Context context1, UserAccount useraccount)
    {
        context = context1;
        twitterUtils = new TwitterUtils();
        userAccount = useraccount;
    }

    public void createUserList(final OnUserListCreatedListener listener, String s, boolean flag, String s1)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void createdUserList(UserList userlist)
            {
                (new Handler(Looper.getMainLooper())).post(userlist. new Runnable() {

                    public void run()
                    {
                        listener.createdUserList(userList);
                    }

                    final _cls4 this$1;
                    final UserList val$userList;

            
            {
                this$1 = final__pcls4;
                userList = UserList.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070078, twitterexception);
            }

            final UserListApi this$0;
            final OnUserListCreatedListener val$listener;

            
            {
                this$0 = UserListApi.this;
                listener = onuserlistcreatedlistener;
                super();
            }
        }
);
        asynctwitter.createUserList(s, flag, s1);
    }

    public void createUserListMember(long l, long l1)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070094, twitterexception);
            }

            final UserListApi this$0;

            
            {
                this$0 = UserListApi.this;
                super();
            }
        }
);
        asynctwitter.createUserListMember(l, l1);
    }

    public void destroyUserList(final OnUserListDestroyedListener listener, final int position, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void destroyedUserList(UserList userlist)
            {
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.destroyedUserList(position);
                    }

                    final _cls6 this$1;

            
            {
                this$1 = _cls6.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f07007c, twitterexception);
            }

            final UserListApi this$0;
            final OnUserListDestroyedListener val$listener;
            final int val$position;

            
            {
                this$0 = UserListApi.this;
                listener = onuserlistdestroyedlistener;
                position = i;
                super();
            }
        }
);
        asynctwitter.destroyUserList(l);
    }

    public void destroyUserListMember(long l, long l1)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070094, twitterexception);
            }

            final UserListApi this$0;

            
            {
                this$0 = UserListApi.this;
                super();
            }
        }
);
        asynctwitter.destroyUserListMember(l, l1);
    }

    public void getUserListMembers(final OnUserListMembersGotListener listener, final long userListId)
    {
        final HashSet ids = new HashSet();
        final AsyncTwitter twitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        twitter.addListener(new TwitterAdapter() {

            public void gotUserListMembers(PagableResponseList pagableresponselist)
            {
                User user;
                for(Iterator iterator = pagableresponselist.iterator(); iterator.hasNext(); ids.add(Long.valueOf(user.getId())))
                    user = (User)iterator.next();

                if(pagableresponselist.hasNext())
                {
                    twitter.getUserListMembers(userListId, pagableresponselist.getNextCursor());
                    return;
                } else
                {
                    (new Handler(Looper.getMainLooper())).post(new Runnable() {

                        public void run()
                        {
                            listener.gotUserListMembers(ids);
                        }

                        final _cls8 this$1;

            
            {
                this$1 = _cls8.this;
                super();
            }
                    }
);
                    return;
                }
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f07008e, twitterexception);
            }

            final UserListApi this$0;
            final Set val$ids;
            final OnUserListMembersGotListener val$listener;
            final AsyncTwitter val$twitter;
            final long val$userListId;

            
            {
                this$0 = UserListApi.this;
                ids = set;
                twitter = asynctwitter;
                userListId = l;
                listener = onuserlistmembersgotlistener;
                super();
            }
        }
);
        twitter.getUserListMembers(userListId, -1L);
    }

    public void getUserLists(final OnUserListsGotListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotUserLists(ResponseList responselist)
            {
                (new Handler(Looper.getMainLooper())).post(responselist. new Runnable() {

                    public void run()
                    {
                        listener.gotUserLists(userLists);
                    }

                    final _cls1 this$1;
                    final ResponseList val$userLists;

            
            {
                this$1 = final__pcls1;
                userLists = ResponseList.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twittermethod = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                if(twitterexception.getErrorCode() != 34)
                    Notificator.toast(context, 0x7f07008e, twittermethod);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.gotUserLists(null);
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            final UserListApi this$0;
            final OnUserListsGotListener val$listener;

            
            {
                this$0 = UserListApi.this;
                listener = onuserlistsgotlistener;
                super();
            }
        }
);
        asynctwitter.getUserLists(l);
    }

    public void showUserListsMembership(final OnUserListsMembershipCheckedListener listener, final Queue listIds, final long userId)
    {
        final HashMap result = new HashMap();
        final AsyncTwitter twitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        twitter.addListener(new TwitterAdapter() {

            private void addMap(boolean flag)
            {
                result.put(listIds.poll(), Boolean.valueOf(flag));
                Long long1 = (Long)listIds.peek();
                if(long1 != null)
                {
                    twitter.showUserListMembership(long1.longValue(), userId);
                    return;
                } else
                {
                    (new Handler(Looper.getMainLooper())).post(new Runnable() {

                        public void run()
                        {
                            listener.checkedUserListsMembership(result);
                        }

                        final _cls7 this$1;

            
            {
                this$1 = _cls7.this;
                super();
            }
                    }
);
                    return;
                }
            }

            public void checkedUserListMembership(User user)
            {
                addMap(true);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                if(twitterexception.getStatusCode() == 404)
                {
                    addMap(false);
                    return;
                } else
                {
                    twitterexception.printStackTrace();
                    twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                    Notificator.toast(context, 0x7f07008e, twitterexception);
                    (new Handler(Looper.getMainLooper())).post(new Runnable() {

                        public void run()
                        {
                            listener.checkedUserListsMembership(null);
                        }

                        final _cls7 this$1;

            
            {
                this$1 = _cls7.this;
                super();
            }
                    }
);
                    return;
                }
            }

            final UserListApi this$0;
            final Queue val$listIds;
            final OnUserListsMembershipCheckedListener val$listener;
            final Map val$result;
            final AsyncTwitter val$twitter;
            final long val$userId;

            
            {
                this$0 = UserListApi.this;
                listener = onuserlistsmembershipcheckedlistener;
                result = map;
                listIds = queue;
                twitter = asynctwitter;
                userId = l;
                super();
            }
        }
);
        listener = (Long)listIds.peek();
        if(listener != null)
            twitter.showUserListMembership(listener.longValue(), userId);
    }

    public void updateUserList(final OnUserListUpdatedListener listener, final int position, long l, String s, boolean flag, String s1)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070094, twitterexception);
            }

            public void updatedUserList(UserList userlist)
            {
                (new Handler(Looper.getMainLooper())).post(userlist. new Runnable() {

                    public void run()
                    {
                        listener.updatedUserList(userList, position);
                    }

                    final _cls5 this$1;
                    final UserList val$userList;

            
            {
                this$1 = final__pcls5;
                userList = UserList.this;
                super();
            }
                }
);
            }

            final UserListApi this$0;
            final OnUserListUpdatedListener val$listener;
            final int val$position;

            
            {
                this$0 = UserListApi.this;
                listener = onuserlistupdatedlistener;
                position = i;
                super();
            }
        }
);
        asynctwitter.updateUserList(l, s, flag, s1);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;

}
