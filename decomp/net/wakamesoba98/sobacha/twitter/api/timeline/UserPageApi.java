// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api.timeline;

import android.content.Context;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnTimeLineLoadedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.api.timeline:
//            TimeLineApiBase

public class UserPageApi extends TimeLineApiBase
{

    public UserPageApi(Context context, StatusAdapter statusadapter, UserAccount useraccount)
    {
        super(context, statusadapter, useraccount);
    }

    public volatile Paging getDefaultPaging()
    {
        return super.getDefaultPaging();
    }

    public void getFollowers(OnTimeLineLoadedListener ontimelineloadedlistener, long l, int i)
    {
        getFollowers(ontimelineloadedlistener, l, -1L, i);
    }

    public void getFollowers(final OnTimeLineLoadedListener listener, long l, long l1, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotFollowersList(PagableResponseList pagableresponselist)
            {
                gotUsers(listener, pagableresponselist, position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f070086);
            }

            final UserPageApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final int val$position;

            
            {
                this$0 = UserPageApi.this;
                listener = ontimelineloadedlistener;
                position = i;
                super();
            }
        }
);
        asynctwitter.getFollowersList(l, l1);
    }

    public void getFriends(OnTimeLineLoadedListener ontimelineloadedlistener, long l, int i)
    {
        getFriends(ontimelineloadedlistener, l, -1L, i);
    }

    public void getFriends(final OnTimeLineLoadedListener listener, long l, long l1, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotFriendsList(PagableResponseList pagableresponselist)
            {
                gotUsers(listener, pagableresponselist, position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f070087);
            }

            final UserPageApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final int val$position;

            
            {
                this$0 = UserPageApi.this;
                listener = ontimelineloadedlistener;
                position = i;
                super();
            }
        }
);
        asynctwitter.getFriendsList(l, l1);
    }
}
