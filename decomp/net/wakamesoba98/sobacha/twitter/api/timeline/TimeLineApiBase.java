// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api.timeline;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.OnTimeLineLoadedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import twitter4j.*;

class TimeLineApiBase
{

    TimeLineApiBase(Context context1, StatusAdapter statusadapter, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        adapter = statusadapter;
        userAccount = useraccount;
        itemsPerPage = PreferenceUtil.getIntPreference(context1, EnumPrefs.ITEMS_PER_PAGE);
    }

    private void addAdapter(final OnTimeLineLoadedListener listener, final List itemList, final long sinceId, final int position)
    {
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                if(itemList.size() > 0)
                {
                    addItem(adapter, itemList, sinceId, position);
                    listener.loadedTimeLine(true, -1L);
                    return;
                }
                if(sinceId > 0L)
                {
                    listener.loadedTimeLine(true, -1L);
                    return;
                } else
                {
                    listener.loadedTimeLine(false, -1L);
                    return;
                }
            }

            final TimeLineApiBase this$0;
            final List val$itemList;
            final OnTimeLineLoadedListener val$listener;
            final int val$position;
            final long val$sinceId;

            
            {
                this$0 = TimeLineApiBase.this;
                itemList = list;
                sinceId = l;
                position = i;
                listener = ontimelineloadedlistener;
                super();
            }
        }
);
    }

    private void addItem(StatusAdapter statusadapter, List list, long l, int i)
    {
        if(l > 0L)
            for(list = list.iterator(); list.hasNext(); statusadapter.insert((StatusItem)list.next(), i));
        else
            for(list = list.iterator(); list.hasNext(); statusadapter.add((StatusItem)list.next()));
        statusadapter.notifyDataSetChanged();
    }

    private List createMessageList(List list, long l, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        if(l > 0L)
            for(list = list.iterator(); list.hasNext(); arraylist.add(0, new StatusItem((DirectMessage)list.next(), flag)));
        else
            for(list = list.iterator(); list.hasNext(); arraylist.add(new StatusItem((DirectMessage)list.next(), flag)));
        return arraylist;
    }

    private List createStatusList(List list, long l)
    {
        ArrayList arraylist = new ArrayList();
        if(l > 0L)
        {
            list = list.iterator();
            do
            {
                if(!list.hasNext())
                    break;
                Object obj = (Status)list.next();
                obj = new StatusItem(userAccount, ((Status) (obj)));
                if(!((StatusItem) (obj)).isMuteTarget())
                    arraylist.add(0, obj);
            } while(true);
        } else
        {
            list = list.iterator();
            do
            {
                if(!list.hasNext())
                    break;
                Object obj1 = (Status)list.next();
                obj1 = new StatusItem(userAccount, ((Status) (obj1)));
                if(!((StatusItem) (obj1)).isMuteTarget())
                    arraylist.add(obj1);
            } while(true);
        }
        return arraylist;
    }

    private List createUsersList(List list)
    {
        ArrayList arraylist = new ArrayList();
        for(list = list.iterator(); list.hasNext(); arraylist.add(new StatusItem((User)list.next())));
        return arraylist;
    }

    public Paging getDefaultPaging()
    {
        return new Paging(1, itemsPerPage);
    }

    void gotMessageConversations(final OnTimeLineLoadedListener listener, final List itemList, final int position)
    {
        itemList = createMessageList(itemList, -1L, true);
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                if(itemList.size() > 0)
                    addItem(adapter, itemList, -1L, position);
                listener.loadedTimeLine(false, -1L);
            }

            final TimeLineApiBase this$0;
            final List val$itemList;
            final OnTimeLineLoadedListener val$listener;
            final int val$position;

            
            {
                this$0 = TimeLineApiBase.this;
                itemList = list;
                position = i;
                listener = ontimelineloadedlistener;
                super();
            }
        }
);
    }

    void gotMessages(OnTimeLineLoadedListener ontimelineloadedlistener, List list, long l, int i)
    {
        addAdapter(ontimelineloadedlistener, createMessageList(list, l, false), l, i);
    }

    void gotTimeLine(OnTimeLineLoadedListener ontimelineloadedlistener, List list, long l, int i)
    {
        addAdapter(ontimelineloadedlistener, createStatusList(list, l), l, i);
    }

    void gotUsers(final OnTimeLineLoadedListener listener, final PagableResponseList users, final int position)
    {
        final List itemList = createUsersList(users);
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                addItem(adapter, itemList, -1L, position);
                listener.loadedTimeLine(users.hasNext(), users.getNextCursor());
            }

            final TimeLineApiBase this$0;
            final List val$itemList;
            final OnTimeLineLoadedListener val$listener;
            final int val$position;
            final PagableResponseList val$users;

            
            {
                this$0 = TimeLineApiBase.this;
                itemList = list;
                position = i;
                listener = ontimelineloadedlistener;
                users = pagableresponselist;
                super();
            }
        }
);
    }

    void gotUsers(final OnTimeLineLoadedListener listener, final ResponseList users, final int position, final int page, final long lastUserId)
    {
        final List itemList = createUsersList(users);
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                if(users.size() > 0)
                {
                    int i = users.size();
                    User user = (User)users.get(i - 1);
                    if(user == null)
                    {
                        listener.loadedTimeLine(false, page);
                        return;
                    }
                    if(user.getId() == lastUserId)
                    {
                        listener.loadedTimeLine(false, page);
                        return;
                    } else
                    {
                        addItem(adapter, itemList, -1L, position);
                        listener.loadedTimeLine(true, page + 1);
                        return;
                    }
                } else
                {
                    listener.loadedTimeLine(false, page);
                    return;
                }
            }

            final TimeLineApiBase this$0;
            final List val$itemList;
            final long val$lastUserId;
            final OnTimeLineLoadedListener val$listener;
            final int val$page;
            final int val$position;
            final ResponseList val$users;

            
            {
                this$0 = TimeLineApiBase.this;
                users = responselist;
                listener = ontimelineloadedlistener;
                page = i;
                lastUserId = l;
                itemList = list;
                position = j;
                super();
            }
        }
);
    }

    void insertReadMore(List list, Paging paging, final int position)
    {
        if(paging.getSinceId() > 0L && adapter.getCount() > 0)
        {
            final int size = list.size();
            (new Handler(Looper.getMainLooper())).post(new Runnable() {

                public void run()
                {
                    StatusItem statusitem = (StatusItem)adapter.getItem(position);
                    if(statusitem != null && statusitem.getStatusType() == EnumStatusType.READ_MORE)
                        adapter.remove(statusitem);
                    if(size >= itemsPerPage - 1)
                    {
                        StatusItem statusitem1 = new StatusItem();
                        adapter.insert(statusitem1, position);
                    }
                }

                final TimeLineApiBase this$0;
                final int val$position;
                final int val$size;

            
            {
                this$0 = TimeLineApiBase.this;
                position = i;
                size = j;
                super();
            }
            }
);
        }
    }

    void showExceptionMessage(final OnTimeLineLoadedListener listener, TwitterException twitterexception, int i)
    {
        twitterexception.printStackTrace();
        twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
        Notificator.toast(context, i, twitterexception);
        (new Handler(Looper.getMainLooper())).post(new Runnable() {

            public void run()
            {
                listener.loadedTimeLine(false, -1L);
            }

            final TimeLineApiBase this$0;
            final OnTimeLineLoadedListener val$listener;

            
            {
                this$0 = TimeLineApiBase.this;
                listener = ontimelineloadedlistener;
                super();
            }
        }
);
    }

    StatusAdapter adapter;
    Context context;
    private int itemsPerPage;
    TwitterUtils twitterUtils;
    UserAccount userAccount;


}
