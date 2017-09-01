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
import net.wakamesoba98.sobacha.twitter.listener.OnStatusReceivedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

public class StatusApi
{

    public StatusApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    private void showExceptionMessage(final OnStatusReceivedListener listener, TwitterException twitterexception, int i)
    {
        twitterexception.printStackTrace();
        twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
        Notificator.toast(context, i, twitterexception);
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                listener.receivedStatus(null);
            }

            final StatusApi this$0;
            final OnStatusReceivedListener val$listener;

            
            {
                this$0 = StatusApi.this;
                listener = onstatusreceivedlistener;
                super();
            }
        }
);
    }

    public void showStatus(final OnStatusReceivedListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotShowStatus(Status status)
            {
                (new Handler(Looper.getMainLooper())).post(status. new Runnable() {

                    public void run()
                    {
                        listener.receivedStatus(status);
                    }

                    final _cls2 this$1;
                    final Status val$status;

            
            {
                this$1 = final__pcls2;
                status = Status.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f070081);
            }

            final StatusApi this$0;
            final OnStatusReceivedListener val$listener;

            
            {
                this$0 = StatusApi.this;
                listener = onstatusreceivedlistener;
                super();
            }
        }
);
        asynctwitter.showStatus(l);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;

}
