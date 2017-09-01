// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api.timeline;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.*;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnTimeLineLoadedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.api.timeline:
//            TimeLineApiBase

public class TimeLineApi extends TimeLineApiBase
{

    public TimeLineApi(Context context, StatusAdapter statusadapter, UserAccount useraccount)
    {
        super(context, statusadapter, useraccount);
    }

    private void lookup(final OnTimeLineLoadedListener listener, long al[], final long sinceId, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void lookedup(ResponseList responselist)
            {
                HashMap hashmap = new HashMap();
                Object obj = new ArrayList();
                Status status;
                for(responselist = responselist.iterator(); responselist.hasNext(); ((List) (obj)).add(Long.valueOf(status.getId())))
                {
                    status = (Status)responselist.next();
                    hashmap.put(Long.valueOf(status.getId()), status);
                }

                Collections.sort(((List) (obj)), Collections.reverseOrder());
                responselist = new ArrayList();
                for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); responselist.add(hashmap.get(Long.valueOf(((Long)((Iterator) (obj)).next()).longValue()))));
                gotTimeLine(listener, responselist, sinceId, position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final int val$position;
            final long val$sinceId;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                sinceId = l;
                position = i;
                super();
            }
        }
);
        asynctwitter.lookup(al);
    }

    public volatile Paging getDefaultPaging()
    {
        return super.getDefaultPaging();
    }

    public void getFavorites(final OnTimeLineLoadedListener listener, long l, final Paging paging, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotFavorites(ResponseList responselist)
            {
                gotTimeLine(listener, responselist, paging.getSinceId(), position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f070088);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final Paging val$paging;
            final int val$position;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                paging = paging1;
                position = i;
                super();
            }
        }
);
        asynctwitter.getFavorites(l, paging);
    }

    public void getHomeTimeLine(final OnTimeLineLoadedListener listener, final Paging paging, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotHomeTimeline(ResponseList responselist)
            {
                insertReadMore(responselist, paging, position);
                gotTimeLine(listener, responselist, paging.getSinceId(), position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final Paging val$paging;
            final int val$position;

            
            {
                this$0 = TimeLineApi.this;
                paging = paging1;
                position = i;
                listener = ontimelineloadedlistener;
                super();
            }
        }
);
        asynctwitter.getHomeTimeline(paging);
    }

    public void getMentions(final OnTimeLineLoadedListener listener, final Paging paging, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotMentions(ResponseList responselist)
            {
                gotTimeLine(listener, responselist, paging.getSinceId(), position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final Paging val$paging;
            final int val$position;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                paging = paging1;
                position = i;
                super();
            }
        }
);
        asynctwitter.getMentions(paging);
    }

    public void getUserList(final OnTimeLineLoadedListener listener, long l, final Paging paging, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotUserListStatuses(ResponseList responselist)
            {
                gotTimeLine(listener, responselist, paging.getSinceId(), position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final Paging val$paging;
            final int val$position;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                paging = paging1;
                position = i;
                super();
            }
        }
);
        asynctwitter.getUserListStatuses(l, paging);
    }

    public void getUserTimeLine(final OnTimeLineLoadedListener listener, long l, final Paging paging, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotUserTimeline(ResponseList responselist)
            {
                gotTimeLine(listener, responselist, paging.getSinceId(), position);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final Paging val$paging;
            final int val$position;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                paging = paging1;
                position = i;
                super();
            }
        }
);
        asynctwitter.getUserTimeline(l, paging);
    }

    public void search(OnTimeLineLoadedListener ontimelineloadedlistener, String s, int i)
    {
        search(ontimelineloadedlistener, new Query(s), i);
    }

    public void search(final OnTimeLineLoadedListener listener, final Query query, final int position)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            public void searched(QueryResult queryresult)
            {
                queryresult = queryresult.getTweets();
                long al[] = new long[queryresult.size()];
                for(int i = 0; i < queryresult.size(); i++)
                    al[i] = ((Status)queryresult.get(i)).getId();

                lookup(listener, al, query.getSinceId(), position);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final int val$position;
            final Query val$query;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                query = query1;
                position = i;
                super();
            }
        }
);
        asynctwitter.search(query);
    }

    public void searchUsers(final OnTimeLineLoadedListener listener, String s, final int position, final int page, final long lastUserId)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            public void searchedUser(ResponseList responselist)
            {
                gotUsers(listener, responselist, position, page, lastUserId);
            }

            final TimeLineApi this$0;
            final long val$lastUserId;
            final OnTimeLineLoadedListener val$listener;
            final int val$page;
            final int val$position;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                position = i;
                page = j;
                lastUserId = l;
                super();
            }
        }
);
        asynctwitter.searchUsers(s, page);
    }

    public void showConversation(final OnTimeLineLoadedListener listener, long l)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotShowStatus(Status status)
            {
                StatusItem statusitem = new StatusItem(userAccount, status);
                (new Handler(Looper.getMainLooper())).post(statusitem. new Runnable() {

                    public void run()
                    {
                        if(!item.isMuteTarget())
                            adapter.add(item);
                    }

                    final _cls9 this$1;
                    final StatusItem val$item;

            
            {
                this$1 = final__pcls9;
                item = StatusItem.this;
                super();
            }
                }
);
                long l1 = status.getInReplyToStatusId();
                if(l1 != -1L)
                {
                    showConversation(listener, l1);
                    return;
                } else
                {
                    (new Handler(Looper.getMainLooper())).post(new Runnable() {

                        public void run()
                        {
                            listener.loadedTimeLine(false, -1L);
                        }

                        final _cls9 this$1;

            
            {
                this$1 = _cls9.this;
                super();
            }
                    }
);
                    return;
                }
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008b);
            }

            final TimeLineApi this$0;
            final OnTimeLineLoadedListener val$listener;

            
            {
                this$0 = TimeLineApi.this;
                listener = ontimelineloadedlistener;
                super();
            }
        }
);
        asynctwitter.showStatus(l);
    }

}
