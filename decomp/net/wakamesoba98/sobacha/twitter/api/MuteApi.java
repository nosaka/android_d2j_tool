// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.HashSet;
import java.util.Set;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.OnMuteUsersGotListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

public class MuteApi
{

    public MuteApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    public void getMutesIds(final OnMuteUsersGotListener listener)
    {
        final HashSet mutesIds = new HashSet();
        final AsyncTwitter twitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        twitter.addListener(new TwitterAdapter() {

            public void gotMuteIDs(IDs ids)
            {
                long al[] = ids.getIDs();
                int j = al.length;
                for(int i = 0; i < j; i++)
                {
                    long l = al[i];
                    mutesIds.add(Long.valueOf(l));
                }

                if(ids.hasNext())
                {
                    twitter.getMutesIDs(ids.getNextCursor());
                    return;
                } else
                {
                    (new Handler(Looper.getMainLooper())).post(new Runnable() {

                        public void run()
                        {
                            listener.gotMuteUsers(mutesIds);
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
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
                Notificator.toast(context, 0x7f07008d, twitterexception);
            }

            final MuteApi this$0;
            final OnMuteUsersGotListener val$listener;
            final Set val$mutesIds;
            final AsyncTwitter val$twitter;

            
            {
                this$0 = MuteApi.this;
                mutesIds = set;
                twitter = asynctwitter;
                listener = onmuteusersgotlistener;
                super();
            }
        }
);
        twitter.getMutesIDs(-1L);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;

}
