// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.os.*;
import android.view.View;
import net.wakamesoba98.sobacha.database.SavedSearchesDatabase;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.menu.SavedSearchesMenu;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.fragment.pulltorefresh.SearchFragment;
import twitter4j.*;

public class SavedSearchApi
{

    public SavedSearchApi(ViewPagerActivity viewpageractivity, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        activity = viewpageractivity;
        userAccount = useraccount;
    }

    public void createSavedSearch(String s)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(activity, userAccount.getAccessToken(activity));
        asynctwitter.addListener(new TwitterAdapter() {

            public void createdSavedSearch(SavedSearch savedsearch)
            {
                SavedSearchesDatabase savedsearchesdatabase = new SavedSearchesDatabase(activity, userAccount.getId());
                savedsearchesdatabase.openDatabase();
                savedsearchesdatabase.putSavedSearch(savedsearch);
                savedsearchesdatabase.closeDatabase();
                Notificator.toast(activity, 0x7f07001c);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(activity, twitterexception);
                Notificator.toast(activity, 0x7f070077, twitterexception);
            }

            final SavedSearchApi this$0;

            
            {
                this$0 = SavedSearchApi.this;
                super();
            }
        }
);
        asynctwitter.createSavedSearch(s);
    }

    public void destroySavedSearch(long l)
    {
        (new AsyncTask() {

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Long[])aobj);
            }

            protected SavedSearch doInBackground(Long along[])
            {
                long l1 = along[0].longValue();
                along = twitterUtils.getTwitterInstance(activity, userAccount.getAccessToken(activity));
                try
                {
                    along = along.destroySavedSearch(l1);
                }
                // Misplaced declaration of an exception variable
                catch(Long along[])
                {
                    along.printStackTrace();
                    along = TwitterErrorMessage.getErrorMessage(activity, along);
                    Notificator.toast(activity, 0x7f07007a, along);
                    return null;
                }
                return along;
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((SavedSearch)obj);
            }

            protected void onPostExecute(SavedSearch savedsearch)
            {
                if(savedsearch == null)
                {
                    return;
                } else
                {
                    SavedSearchesDatabase savedsearchesdatabase = new SavedSearchesDatabase(activity, userAccount.getId());
                    savedsearchesdatabase.openDatabase();
                    savedsearchesdatabase.deleteSavedSearch(savedsearch.getId());
                    savedsearchesdatabase.closeDatabase();
                    Notificator.toast(activity, 0x7f070042);
                    return;
                }
            }

            final SavedSearchApi this$0;

            
            {
                this$0 = SavedSearchApi.this;
                super();
            }
        }
).execute(new Long[] {
            Long.valueOf(l)
        });
    }

    public void getSavedSearches(final SearchFragment fragment, final String text, final View anchor)
    {
        final SpinnerDialog dialog = new SpinnerDialog(activity);
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(activity, userAccount.getAccessToken(activity));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotSavedSearches(ResponseList responselist)
            {
                dialog.dismiss();
                SavedSearchesDatabase savedsearchesdatabase = new SavedSearchesDatabase(activity, userAccount.getId());
                savedsearchesdatabase.openDatabase();
                savedsearchesdatabase.deleteAllData();
                savedsearchesdatabase.putSavedSearches(responselist);
                savedsearchesdatabase.closeDatabase();
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        (new SavedSearchesMenu(activity, userAccount, fragment, text)).show(anchor);
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(activity, twitterexception);
                Notificator.toast(activity, 0x7f07008b, twitterexception);
            }

            final SavedSearchApi this$0;
            final View val$anchor;
            final SpinnerDialog val$dialog;
            final SearchFragment val$fragment;
            final String val$text;

            
            {
                this$0 = SavedSearchApi.this;
                dialog = spinnerdialog;
                fragment = searchfragment;
                text = s;
                anchor = view;
                super();
            }
        }
);
        dialog.show();
        asynctwitter.getSavedSearches();
    }

    private ViewPagerActivity activity;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;



}
