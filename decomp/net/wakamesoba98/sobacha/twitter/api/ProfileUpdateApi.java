// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.io.FileNotFoundException;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.OnProfileUpdatedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.activity.data.Profile;
import twitter4j.*;

public class ProfileUpdateApi
{

    public ProfileUpdateApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    public void updateProfile(final OnProfileUpdatedListener listener, Profile profile)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070095, twitterexception);
            }

            public void updatedProfile(User user)
            {
                Notificator.toast(context, 0x7f070134);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.updatedProfile();
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            final ProfileUpdateApi this$0;
            final OnProfileUpdatedListener val$listener;

            
            {
                this$0 = ProfileUpdateApi.this;
                listener = onprofileupdatedlistener;
                super();
            }
        }
);
        asynctwitter.updateProfile(profile.getName(), profile.getUrl(), profile.getLocation(), profile.getDescription());
    }

    public void updateProfileWithImage(final OnProfileUpdatedListener listener, Uri uri, final Profile profile, final boolean isProfileChanged)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        final SpinnerDialog dialog = new SpinnerDialog(context);
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070096, twitterexception);
            }

            public void updatedProfileImage(User user)
            {
                Notificator.toast(context, 0x7f070134);
                dialog.dismiss();
                if(isProfileChanged)
                {
                    updateProfile(listener, profile);
                    return;
                } else
                {
                    (new Handler(Looper.getMainLooper())).post(new Runnable() {

                        public void run()
                        {
                            listener.updatedProfile();
                        }

                        final _cls2 this$1;

            
            {
                this$1 = _cls2.this;
                super();
            }
                    }
);
                    return;
                }
            }

            final ProfileUpdateApi this$0;
            final SpinnerDialog val$dialog;
            final boolean val$isProfileChanged;
            final OnProfileUpdatedListener val$listener;
            final Profile val$profile;

            
            {
                this$0 = ProfileUpdateApi.this;
                dialog = spinnerdialog;
                isProfileChanged = flag;
                listener = onprofileupdatedlistener;
                profile = profile1;
                super();
            }
        }
);
        try
        {
            listener = context.getContentResolver().openInputStream(uri);
            dialog.show();
            asynctwitter.updateProfileImage(listener);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(final OnProfileUpdatedListener listener)
        {
            listener.printStackTrace();
        }
        Notificator.toast(context, 0x7f070096);
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;

}
