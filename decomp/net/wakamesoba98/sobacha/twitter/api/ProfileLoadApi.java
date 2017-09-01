// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.OnUserProfileGotListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

public class ProfileLoadApi
{

    public ProfileLoadApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    public void getUser(final OnUserProfileGotListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        final SpinnerDialog dialog = new SpinnerDialog(context);
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotUserDetail(User user)
            {
                dialog.dismiss();
                (new Handler(Looper.getMainLooper())).post(user. new Runnable() {

                    public void run()
                    {
                        listener.gotUserProfile(user);
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
                dialog.dismiss();
                Notificator.toast(context, 0x7f07008d, twitterexception);
            }

            final ProfileLoadApi this$0;
            final SpinnerDialog val$dialog;
            final OnUserProfileGotListener val$listener;

            
            {
                this$0 = ProfileLoadApi.this;
                dialog = spinnerdialog;
                listener = onuserprofilegotlistener;
                super();
            }
        }
);
        dialog.show();
        asynctwitter.showUser(l);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;

}
