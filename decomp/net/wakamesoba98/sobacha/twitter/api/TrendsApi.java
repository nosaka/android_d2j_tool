// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.menu.LocationMenu;
import net.wakamesoba98.sobacha.menu.TrendsMenu;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.fragment.pulltorefresh.SearchFragment;
import twitter4j.*;

public class TrendsApi
{

    public TrendsApi(Activity activity1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        activity = activity1;
        userAccount = useraccount;
    }

    public void getAvailableTrends(final View anchor)
    {
        isCancelled = false;
        final SpinnerDialog dialog = new SpinnerDialog(activity);
        dialog.showCancellable(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                isCancelled = true;
            }

            final TrendsApi this$0;

            
            {
                this$0 = TrendsApi.this;
                super();
            }
        }
);
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(activity, userAccount.getAccessToken(activity));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotAvailableTrends(ResponseList responselist)
            {
                if(!isCancelled)
                {
                    dialog.dismiss();
                    (new Handler(Looper.getMainLooper())).post(responselist. new Runnable() {

                        public void run()
                        {
                            (new LocationMenu(activity, locations)).show(anchor);
                        }

                        final _cls4 this$1;
                        final ResponseList val$locations;

            
            {
                this$1 = final__pcls4;
                locations = ResponseList.this;
                super();
            }
                    }
);
                }
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(activity, twitterexception);
                Notificator.toast(activity, 0x7f070089, twitterexception);
            }

            final TrendsApi this$0;
            final View val$anchor;
            final SpinnerDialog val$dialog;

            
            {
                this$0 = TrendsApi.this;
                dialog = spinnerdialog;
                anchor = view;
                super();
            }
        }
);
        asynctwitter.getAvailableTrends();
    }

    public void getTrend(final SearchFragment fragment, final View anchor)
    {
        isCancelled = false;
        final SpinnerDialog dialog = new SpinnerDialog(activity);
        dialog.showCancellable(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                isCancelled = true;
            }

            final TrendsApi this$0;

            
            {
                this$0 = TrendsApi.this;
                super();
            }
        }
);
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(activity, userAccount.getAccessToken(activity));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotPlaceTrends(Trends trends)
            {
                if(!isCancelled)
                {
                    dialog.dismiss();
                    (new Handler(Looper.getMainLooper())).post(trends. new Runnable() {

                        public void run()
                        {
                            (new TrendsMenu(activity, trends, fragment)).show(anchor);
                        }

                        final _cls2 this$1;
                        final Trends val$trends;

            
            {
                this$1 = final__pcls2;
                trends = Trends.this;
                super();
            }
                    }
);
                }
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(activity, twitterexception);
                Notificator.toast(activity, 0x7f07008c, twitterexception);
            }

            final TrendsApi this$0;
            final View val$anchor;
            final SpinnerDialog val$dialog;
            final SearchFragment val$fragment;

            
            {
                this$0 = TrendsApi.this;
                dialog = spinnerdialog;
                fragment = searchfragment;
                anchor = view;
                super();
            }
        }
);
        int j = PreferenceUtil.getIntPreference(activity, EnumPrefs.TREND_LOCATION);
        int i = j;
        if(j < 0)
            i = ResourceHelper.getInteger(activity, 0x7f0b0000);
        asynctwitter.getPlaceTrends(i);
    }

    private Activity activity;
    private boolean isCancelled;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;



/*
    static boolean access$002(TrendsApi trendsapi, boolean flag)
    {
        trendsapi.isCancelled = flag;
        return flag;
    }

*/

}
