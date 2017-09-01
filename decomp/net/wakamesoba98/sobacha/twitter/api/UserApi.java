// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import net.wakamesoba98.sobacha.database.FriendsIdDatabase;
import net.wakamesoba98.sobacha.database.MuteDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.*;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

public class UserApi
{

    public UserApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    private void createFriendship(final Context context, final OnRelationshipGotListener relationListener, final OnUserActionFinishedListener actionListener, final long sourceId, final long targetId)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        final SpinnerDialog dialog = new SpinnerDialog(context);
        dialog.show(0x7f07009f);
        asynctwitter.addListener(new TwitterAdapter() {

            public void createdFriendship(User user)
            {
                dialog.dismiss();
                putFriends(context, user);
                if(user.isProtected())
                    Notificator.toast(context, 0x7f07009a);
                if(relationListener != null)
                {
                    getFriendship(relationListener, sourceId, targetId);
                    return;
                }
                if(!user.isProtected())
                    Notificator.toast(context, 0x7f07009c);
                actionListener.finishedUserAction();
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                dialog.dismiss();
                Notificator.toast(context, 0x7f070080, twitterexception);
                if(actionListener != null)
                    actionListener.finishedUserAction();
            }

            final UserApi this$0;
            final OnUserActionFinishedListener val$actionListener;
            final Context val$context;
            final SpinnerDialog val$dialog;
            final OnRelationshipGotListener val$relationListener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApi.this;
                dialog = spinnerdialog;
                context = context1;
                relationListener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                actionListener = onuseractionfinishedlistener;
                super();
            }
        }
);
        asynctwitter.createFriendship(targetId);
    }

    private void getUserDetail(final OnUserDetailGotListener listener, long l, String s)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotUserDetail(User user)
            {
                (new Handler(Looper.getMainLooper())).post(user. new Runnable() {

                    public void run()
                    {
                        listener.gotUserDetail(user);
                    }

                    final _cls1 this$1;
                    final User val$user;

            
            {
                this$1 = final__pcls1;
                user = User.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f07008d, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.gotUserDetail(null);
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            final UserApi this$0;
            final OnUserDetailGotListener val$listener;

            
            {
                this$0 = UserApi.this;
                listener = onuserdetailgotlistener;
                super();
            }
        }
);
        if(s != null)
        {
            asynctwitter.showUser(s);
            return;
        } else
        {
            asynctwitter.showUser(l);
            return;
        }
    }

    private void putFriends(Context context1, User user)
    {
        user = new AccountData(user);
        context1 = new FriendsIdDatabase(context1, userAccount.getId());
        context1.openDatabase();
        if(context1.getCount() > 0)
            context1.putAccount(user);
        context1.closeDatabase();
    }

    private void putMute(Context context1, User user)
    {
        context1 = new MuteDatabase(context1, "users");
        context1.openDatabase();
        context1.putString(user.getScreenName());
        context1.closeDatabase();
    }

    private void removeFriends(Context context1, User user)
    {
        context1 = new FriendsIdDatabase(context1, userAccount.getId());
        context1.openDatabase();
        if(context1.getCount() > 0)
            context1.deleteAccount(user.getId());
        context1.closeDatabase();
    }

    private void removeMute(Context context1, User user)
    {
        context1 = new MuteDatabase(context1, "users");
        context1.openDatabase();
        context1.deleteData(user.getScreenName());
        context1.closeDatabase();
    }

    void createBlock(final Context context, final OnRelationshipGotListener listener, final long sourceId, final long targetId)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        final SpinnerDialog dialog = new SpinnerDialog(context);
        dialog.show(0x7f07002d);
        asynctwitter.addListener(new TwitterAdapter() {

            public void createdBlock(User user)
            {
                dialog.dismiss();
                getFriendship(listener, sourceId, targetId);
                removeFriends(context, user);
                putMute(context, user);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                dialog.dismiss();
                Notificator.toast(context, 0x7f070080, twitterexception);
            }

            final UserApi this$0;
            final Context val$context;
            final SpinnerDialog val$dialog;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApi.this;
                dialog = spinnerdialog;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                context = context1;
                super();
            }
        }
);
        asynctwitter.createBlock(targetId);
    }

    public void createFriendship(Context context1, OnRelationshipGotListener onrelationshipgotlistener, long l, long l1)
    {
        createFriendship(context1, onrelationshipgotlistener, null, l, l1);
    }

    public void createFriendship(Context context1, OnUserActionFinishedListener onuseractionfinishedlistener, long l, long l1)
    {
        createFriendship(context1, null, onuseractionfinishedlistener, l, l1);
    }

    void destroyBlock(final Context context, final OnRelationshipGotListener listener, final long sourceId, final long targetId)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        final SpinnerDialog dialog = new SpinnerDialog(context);
        dialog.show(0x7f07012e);
        asynctwitter.addListener(new TwitterAdapter() {

            public void destroyedBlock(User user)
            {
                dialog.dismiss();
                getFriendship(listener, sourceId, targetId);
                removeMute(context, user);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                dialog.dismiss();
                Notificator.toast(context, 0x7f070080, twitterexception);
            }

            final UserApi this$0;
            final Context val$context;
            final SpinnerDialog val$dialog;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApi.this;
                dialog = spinnerdialog;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                context = context1;
                super();
            }
        }
);
        asynctwitter.destroyBlock(targetId);
    }

    void destroyFriendship(final Context context, final OnRelationshipGotListener listener, final long sourceId, final long targetId)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        final SpinnerDialog dialog = new SpinnerDialog(context);
        dialog.show(0x7f070130);
        asynctwitter.addListener(new TwitterAdapter() {

            public void destroyedFriendship(User user)
            {
                dialog.dismiss();
                getFriendship(listener, sourceId, targetId);
                removeFriends(context, user);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                dialog.dismiss();
                Notificator.toast(context, 0x7f070080, twitterexception);
            }

            final UserApi this$0;
            final Context val$context;
            final SpinnerDialog val$dialog;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApi.this;
                dialog = spinnerdialog;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                context = context1;
                super();
            }
        }
);
        asynctwitter.destroyFriendship(targetId);
    }

    public void getFriendship(final OnRelationshipGotListener listener, long l, long l1)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotShowFriendship(Relationship relationship)
            {
                (new Handler(Looper.getMainLooper())).post(relationship. new Runnable() {

                    public void run()
                    {
                        listener.gotFriendship(relationship);
                    }

                    final _cls2 this$1;
                    final Relationship val$relationship;

            
            {
                this$1 = final__pcls2;
                relationship = Relationship.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070080, twitterexception);
            }

            final UserApi this$0;
            final OnRelationshipGotListener val$listener;

            
            {
                this$0 = UserApi.this;
                listener = onrelationshipgotlistener;
                super();
            }
        }
);
        asynctwitter.showFriendship(l, l1);
    }

    public void getUserDetail(OnUserDetailGotListener onuserdetailgotlistener, long l)
    {
        getUserDetail(onuserdetailgotlistener, l, null);
    }

    public void getUserDetail(OnUserDetailGotListener onuserdetailgotlistener, String s)
    {
        getUserDetail(onuserdetailgotlistener, -1L, s);
    }

    void reportSpam(final Context context, final OnRelationshipGotListener listener, final long sourceId, final long targetId)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        final SpinnerDialog dialog = new SpinnerDialog(context);
        dialog.show(0x7f0700ef);
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                dialog.dismiss();
                Notificator.toast(context, 0x7f070080, twitterexception);
            }

            public void reportedSpam(User user)
            {
                dialog.dismiss();
                getFriendship(listener, sourceId, targetId);
                removeFriends(context, user);
                putMute(context, user);
            }

            final UserApi this$0;
            final Context val$context;
            final SpinnerDialog val$dialog;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApi.this;
                dialog = spinnerdialog;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                context = context1;
                super();
            }
        }
);
        asynctwitter.reportSpam(targetId);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;





}
