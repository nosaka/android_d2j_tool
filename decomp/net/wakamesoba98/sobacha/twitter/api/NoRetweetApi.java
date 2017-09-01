// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.os.*;
import java.util.HashSet;
import java.util.Set;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.OnNoRetweetsIdsGotListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;

public class NoRetweetApi
{
    private class AsyncGetNoRetweetsFriendshipsTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient IDs doInBackground(Void avoid[])
        {
            avoid = twitterUtils.getTwitterInstance(context, userAccount.getAccessToken(context));
            try
            {
                avoid = avoid.getNoRetweetsFriendships();
            }
            // Misplaced declaration of an exception variable
            catch(Void avoid[])
            {
                avoid.printStackTrace();
                return null;
            }
            return avoid;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((IDs)obj);
        }

        protected void onPostExecute(IDs ids)
        {
            (new Handler(Looper.getMainLooper())).post(ids. new Runnable() {

                public void run()
                {
                    HashSet hashset = new HashSet();
                    if(result != null)
                    {
                        long al[] = result.getIDs();
                        int j = al.length;
                        for(int i = 0; i < j; i++)
                            hashset.add(Long.valueOf(al[i]));

                    }
                    listener.gotNoRetweetsIds(hashset);
                }

                final AsyncGetNoRetweetsFriendshipsTask this$1;
                final IDs val$result;

            
            {
                this$1 = final_asyncgetnoretweetsfriendshipstask;
                result = IDs.this;
                super();
            }
            }
);
        }

        final NoRetweetApi this$0;

        private AsyncGetNoRetweetsFriendshipsTask()
        {
            this$0 = NoRetweetApi.this;
            super();
        }

    }


    public NoRetweetApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    public void getNoRetweetsUsers(OnNoRetweetsIdsGotListener onnoretweetsidsgotlistener)
    {
        listener = onnoretweetsidsgotlistener;
        (new AsyncGetNoRetweetsFriendshipsTask()).execute(new Void[0]);
    }

    public void setNoRetweetsUsers(final OnNoRetweetsIdsGotListener listener, final long userId, final boolean showRetweets)
    {
        asyncTwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asyncTwitter.addListener(new TwitterAdapter() {

            public void gotShowFriendship(Relationship relationship)
            {
                asyncTwitter.updateFriendship(userId, relationship.isSourceNotificationsEnabled(), showRetweets);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070075, twitterexception);
            }

            public void updatedFriendship(Relationship relationship)
            {
                getNoRetweetsUsers(listener);
            }

            final NoRetweetApi this$0;
            final OnNoRetweetsIdsGotListener val$listener;
            final boolean val$showRetweets;
            final long val$userId;

            
            {
                this$0 = NoRetweetApi.this;
                userId = l;
                showRetweets = flag;
                listener = onnoretweetsidsgotlistener;
                super();
            }
        }
);
        long l = userAccount.getId();
        asyncTwitter.showFriendship(l, userId);
    }

    private AsyncTwitter asyncTwitter;
    private Context context;
    private OnNoRetweetsIdsGotListener listener;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;





}
