// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusActionFinishedListener;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusDeletedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

class StatusActionApi
{

    StatusActionApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    void destroy(final OnStatusDeletedListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void destroyedStatus(Status status)
            {
                Notificator.toast(context, 0x7f070042);
                (new Handler(Looper.getMainLooper())).post(status. new Runnable() {

                    public void run()
                    {
                        listener.deletedStatus(status.getId());
                    }

                    final _cls5 this$1;
                    final Status val$status;

            
            {
                this$1 = final__pcls5;
                status = Status.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f07007b, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.deletedStatus(-1L);
                    }

                    final _cls5 this$1;

            
            {
                this$1 = _cls5.this;
                super();
            }
                }
);
            }

            final StatusActionApi this$0;
            final OnStatusDeletedListener val$listener;

            
            {
                this$0 = StatusActionApi.this;
                listener = onstatusdeletedlistener;
                super();
            }
        }
);
        asynctwitter.destroyStatus(l);
    }

    void destroyMessage(final OnStatusDeletedListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void destroyedDirectMessage(DirectMessage directmessage)
            {
                Notificator.toast(context, 0x7f070042);
                (new Handler(Looper.getMainLooper())).post(directmessage. new Runnable() {

                    public void run()
                    {
                        listener.deletedStatus(directMessage.getId());
                    }

                    final _cls6 this$1;
                    final DirectMessage val$directMessage;

            
            {
                this$1 = final__pcls6;
                directMessage = DirectMessage.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070079, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.deletedStatus(-1L);
                    }

                    final _cls6 this$1;

            
            {
                this$1 = _cls6.this;
                super();
            }
                }
);
            }

            final StatusActionApi this$0;
            final OnStatusDeletedListener val$listener;

            
            {
                this$0 = StatusActionApi.this;
                listener = onstatusdeletedlistener;
                super();
            }
        }
);
        asynctwitter.destroyDirectMessage(l);
    }

    void favorite(final OnStatusActionFinishedListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void createdFavorite(Status status)
            {
                Notificator.toast(context, 0x7f0700b9);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls2 this$1;

            
            {
                this$1 = _cls2.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070074, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls2 this$1;

            
            {
                this$1 = _cls2.this;
                super();
            }
                }
);
            }

            final StatusActionApi this$0;
            final OnStatusActionFinishedListener val$listener;

            
            {
                this$0 = StatusActionApi.this;
                listener = onstatusactionfinishedlistener;
                super();
            }
        }
);
        asynctwitter.createFavorite(l);
    }

    void favoriteRetweet(final OnStatusActionFinishedListener listener, final long statusId)
    {
        final AsyncTwitter twitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        twitter.addListener(new TwitterAdapter() {

            public void createdFavorite(Status status)
            {
                Notificator.toast(context, 0x7f0700ba);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls3 this$1;

            
            {
                this$1 = _cls3.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070091, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls3 this$1;

            
            {
                this$1 = _cls3.this;
                super();
            }
                }
);
            }

            public void retweetedStatus(Status status)
            {
                twitter.createFavorite(statusId);
            }

            final StatusActionApi this$0;
            final OnStatusActionFinishedListener val$listener;
            final long val$statusId;
            final AsyncTwitter val$twitter;

            
            {
                this$0 = StatusActionApi.this;
                twitter = asynctwitter;
                statusId = l;
                listener = onstatusactionfinishedlistener;
                super();
            }
        }
);
        twitter.retweetStatus(statusId);
    }

    void retweet(final OnStatusActionFinishedListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070091, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            public void retweetedStatus(Status status)
            {
                Notificator.toast(context, 0x7f0700f4);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            final StatusActionApi this$0;
            final OnStatusActionFinishedListener val$listener;

            
            {
                this$0 = StatusActionApi.this;
                listener = onstatusactionfinishedlistener;
                super();
            }
        }
);
        asynctwitter.retweetStatus(l);
    }

    void unFavorite(final OnStatusActionFinishedListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void destroyedFavorite(Status status)
            {
                Notificator.toast(context, 0x7f070133);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls4 this$1;

            
            {
                this$1 = _cls4.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070090, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.finishedStatusAction();
                    }

                    final _cls4 this$1;

            
            {
                this$1 = _cls4.this;
                super();
            }
                }
);
            }

            final StatusActionApi this$0;
            final OnStatusActionFinishedListener val$listener;

            
            {
                this$0 = StatusActionApi.this;
                listener = onstatusactionfinishedlistener;
                super();
            }
        }
);
        asynctwitter.destroyFavorite(l);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;

}
