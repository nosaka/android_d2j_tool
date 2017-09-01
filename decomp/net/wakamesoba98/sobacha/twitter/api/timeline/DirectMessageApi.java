// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api.timeline;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnTimeLineLoadedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.api.timeline:
//            TimeLineApiBase

public class DirectMessageApi extends TimeLineApiBase
{

    public DirectMessageApi(Context context, StatusAdapter statusadapter, UserAccount useraccount)
    {
        super(context, statusadapter, useraccount);
    }

    private void showConversation(OnTimeLineLoadedListener ontimelineloadedlistener, Map map)
    {
        ArrayList arraylist = new ArrayList();
        for(map = map.values().iterator(); map.hasNext(); arraylist.add((DirectMessage)map.next()));
        gotMessageConversations(ontimelineloadedlistener, arraylist, 0);
    }

    public volatile Paging getDefaultPaging()
    {
        return super.getDefaultPaging();
    }

    public void getDirectMessageConversation(final OnTimeLineLoadedListener listener, final long targetUserId)
    {
        final TreeMap messageMap = new TreeMap();
        final AsyncTwitter twitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        twitter.addListener(new TwitterAdapter() {

            public void gotDirectMessages(ResponseList responselist)
            {
                responselist = responselist.iterator();
                do
                {
                    if(!responselist.hasNext())
                        break;
                    DirectMessage directmessage = (DirectMessage)responselist.next();
                    if(directmessage.getSenderId() == targetUserId)
                        messageMap.put(Long.valueOf(directmessage.getId()), directmessage);
                } while(true);
                twitter.getSentDirectMessages(new Paging(1, 50));
            }

            public void gotSentDirectMessages(ResponseList responselist)
            {
                responselist = responselist.iterator();
                do
                {
                    if(!responselist.hasNext())
                        break;
                    DirectMessage directmessage = (DirectMessage)responselist.next();
                    if(directmessage.getRecipientId() == targetUserId)
                        messageMap.put(Long.valueOf(directmessage.getId()), directmessage);
                } while(true);
                showConversation(listener, messageMap);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008a);
            }

            final DirectMessageApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final TreeMap val$messageMap;
            final long val$targetUserId;
            final AsyncTwitter val$twitter;

            
            {
                this$0 = DirectMessageApi.this;
                targetUserId = l;
                messageMap = treemap;
                twitter = asynctwitter;
                listener = ontimelineloadedlistener;
                super();
            }
        }
);
        twitter.getDirectMessages(new Paging(1, 50));
    }

    public void getDirectMessages(final OnTimeLineLoadedListener listener, final Paging paging)
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void gotDirectMessages(ResponseList responselist)
            {
                gotMessages(listener, responselist, paging.getSinceId(), 0);
            }

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                showExceptionMessage(listener, twitterexception, 0x7f07008a);
            }

            final DirectMessageApi this$0;
            final OnTimeLineLoadedListener val$listener;
            final Paging val$paging;

            
            {
                this$0 = DirectMessageApi.this;
                listener = ontimelineloadedlistener;
                paging = paging1;
                super();
            }
        }
);
        asynctwitter.getDirectMessages(paging);
    }

    private static final int GET_MESSAGES = 50;

}
