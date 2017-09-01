// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import java.util.*;
import net.wakamesoba98.sobacha.database.FriendsIdDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.util.*;
import twitter4j.*;

public class FriendsListApi
{

    public FriendsListApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    private void initializeDatabase(long l)
    {
        FriendsIdDatabase friendsiddatabase = new FriendsIdDatabase(context, l);
        friendsiddatabase.openDatabase();
        friendsiddatabase.deleteAllData();
        friendsiddatabase.closeDatabase();
    }

    private void writeDatabase(long l, List list)
    {
        final SpinnerDialog dialog = new SpinnerDialog(context);
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                dialog.show(0x7f070140);
            }

            final FriendsListApi this$0;
            final SpinnerDialog val$dialog;

            
            {
                this$0 = FriendsListApi.this;
                dialog = spinnerdialog;
                super();
            }
        }
);
        FriendsIdDatabase friendsiddatabase = new FriendsIdDatabase(context, l);
        friendsiddatabase.openDatabase();
        for(list = list.iterator(); list.hasNext(); friendsiddatabase.putAccount(new AccountData((User)list.next())));
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                dialog.dismiss();
            }

            final FriendsListApi this$0;
            final SpinnerDialog val$dialog;

            
            {
                this$0 = FriendsListApi.this;
                dialog = spinnerdialog;
                super();
            }
        }
);
    }

    public void getFriendsList(final long userId)
    {
        isCancelled = false;
        final SpinnerDialog dialog = new SpinnerDialog(context);
        dialog.showCancellable(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                isCancelled = true;
            }

            final FriendsListApi this$0;

            
            {
                this$0 = FriendsListApi.this;
                super();
            }
        }
);
        initializeDatabase(userId);
        idList = new ArrayList();
        userList = new ArrayList();
        final AsyncTwitter twitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        twitter.addListener(new TwitterAdapter() {

            public void gotFriendsIDs(IDs ids)
            {
label0:
                {
                    if(!isCancelled)
                    {
                        long al[] = ids.getIDs();
                        int j = al.length;
                        for(int i = 0; i < j; i++)
                        {
                            long l = al[i];
                            idList.add(Long.valueOf(l));
                        }

                        if(!ids.hasNext())
                            break label0;
                        twitter.getFriendsIDs(userId, ids.getNextCursor());
                    }
                    return;
                }
                ids = new ArrayUtils();
                splitIdsQueue = ids.splitList(idList, 100);
                ids = (LongArray)splitIdsQueue.poll();
                if(ids != null)
                {
                    twitter.lookupUsers(ids.getLongArray());
                    return;
                } else
                {
                    dialog.dismiss();
                    return;
                }
            }

            public void lookedupUsers(ResponseList responselist)
            {
label0:
                {
                    if(!isCancelled)
                    {
                        User user;
                        for(responselist = responselist.iterator(); responselist.hasNext(); userList.add(user))
                            user = (User)responselist.next();

                        responselist = (LongArray)splitIdsQueue.poll();
                        if(responselist == null)
                            break label0;
                        twitter.lookupUsers(responselist.getLongArray());
                    }
                    return;
                }
                dialog.dismiss();
                writeDatabase(userId, userList);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070087, twitterexception);
                dialog.dismiss();
            }

            final FriendsListApi this$0;
            final SpinnerDialog val$dialog;
            final AsyncTwitter val$twitter;
            final long val$userId;

            
            {
                this$0 = FriendsListApi.this;
                twitter = asynctwitter;
                userId = l;
                dialog = spinnerdialog;
                super();
            }
        }
);
        twitter.getFriendsIDs(userId, -1L);
    }

    private static final int SPLIT = 100;
    private Context context;
    private List idList;
    private boolean isCancelled;
    private Queue splitIdsQueue;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;
    private List userList;



/*
    static boolean access$002(FriendsListApi friendslistapi, boolean flag)
    {
        friendslistapi.isCancelled = flag;
        return flag;
    }

*/




/*
    static Queue access$202(FriendsListApi friendslistapi, Queue queue)
    {
        friendslistapi.splitIdsQueue = queue;
        return queue;
    }

*/



}
