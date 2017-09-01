// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.*;
import net.wakamesoba98.sobacha.database.AccountsIdDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnLookupFinishedListener;
import net.wakamesoba98.sobacha.twitter.util.*;
import twitter4j.*;

public class LookupApi
{

    public LookupApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    private void writeDatabase(OnLookupFinishedListener onlookupfinishedlistener, List list)
    {
        AccountsIdDatabase accountsiddatabase = new AccountsIdDatabase(context);
        accountsiddatabase.openDatabase();
        for(list = list.iterator(); list.hasNext(); accountsiddatabase.putAccount(new AccountData((User)list.next())));
        accountsiddatabase.closeDatabase();
        onlookupfinishedlistener.finishedLookup();
    }

    public void backgroundLookup(OnLookupFinishedListener onlookupfinishedlistener, long l)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(Long.valueOf(l));
        backgroundLookup(onlookupfinishedlistener, ((List) (arraylist)));
    }

    public void backgroundLookup(final OnLookupFinishedListener listener, List list)
    {
        userList = new ArrayList();
        final AsyncTwitter twitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        twitter.addListener(new TwitterAdapter() {

            public void lookedupUsers(ResponseList responselist)
            {
                User user;
                for(responselist = responselist.iterator(); responselist.hasNext(); userList.add(user))
                    user = (User)responselist.next();

                responselist = (LongArray)splitIdsQueue.poll();
                if(responselist != null)
                {
                    twitter.lookupUsers(responselist.getLongArray());
                    return;
                } else
                {
                    (new Handler(Looper.getMainLooper())).post(new Runnable() {

                        public void run()
                        {
                            writeDatabase(listener, userList);
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

            final LookupApi this$0;
            final OnLookupFinishedListener val$listener;
            final AsyncTwitter val$twitter;

            
            {
                this$0 = LookupApi.this;
                twitter = asynctwitter;
                listener = onlookupfinishedlistener;
                super();
            }
        }
);
        splitIdsQueue = (new ArrayUtils()).splitList(list, 100);
        listener = (LongArray)splitIdsQueue.poll();
        if(listener != null)
            twitter.lookupUsers(listener.getLongArray());
    }

    private static final int SPLIT = 100;
    private Context context;
    private Queue splitIdsQueue;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;
    private List userList;



}
