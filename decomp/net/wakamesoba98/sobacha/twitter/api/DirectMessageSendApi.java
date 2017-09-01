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
import net.wakamesoba98.sobacha.twitter.listener.OnDirectMessageSentListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

class DirectMessageSendApi
{

    DirectMessageSendApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    void send(final OnDirectMessageSentListener listener, long l, String s)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070092, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.sentDirectMessage(null);
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            public void sentDirectMessage(DirectMessage directmessage)
            {
                Notificator.toast(context, 0x7f070104);
                (new Handler(Looper.getMainLooper())).post(directmessage. new Runnable() {

                    public void run()
                    {
                        listener.sentDirectMessage(directMessage);
                    }

                    final _cls1 this$1;
                    final DirectMessage val$directMessage;

            
            {
                this$1 = final__pcls1;
                directMessage = DirectMessage.this;
                super();
            }
                }
);
            }

            final DirectMessageSendApi this$0;
            final OnDirectMessageSentListener val$listener;

            
            {
                this$0 = DirectMessageSendApi.this;
                listener = ondirectmessagesentlistener;
                super();
            }
        }
);
        asynctwitter.sendDirectMessage(l, s);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;

}
