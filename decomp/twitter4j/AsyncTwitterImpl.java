// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            TwitterBaseImpl, AsyncTwitter, TwitterFactory, DispatcherFactory, 
//            TwitterMethod, Dispatcher, TwitterException, Twitter, 
//            Paging, TwitterListener, GeoLocation, OEmbedRequest, 
//            GeoQuery, Query, StatusUpdate

class AsyncTwitterImpl extends TwitterBaseImpl
    implements AsyncTwitter
{
    abstract class AsyncTask
        implements Runnable
    {

        abstract void invoke(List list)
            throws TwitterException;

        public void run()
        {
            invoke(listeners);
_L1:
            return;
            TwitterException twitterexception;
            twitterexception;
            if(listeners != null)
            {
                Iterator iterator = listeners.iterator();
                while(iterator.hasNext()) 
                {
                    TwitterListener twitterlistener = (TwitterListener)iterator.next();
                    try
                    {
                        twitterlistener.onException(twitterexception, method);
                    }
                    catch(Exception exception) { }
                }
            }
              goto _L1
        }

        final List listeners;
        final TwitterMethod method;
        final AsyncTwitterImpl this$0;

        AsyncTask(TwitterMethod twittermethod, List list)
        {
            this$0 = AsyncTwitterImpl.this;
            super();
            method = twittermethod;
            listeners = list;
        }
    }


    AsyncTwitterImpl(Configuration configuration, Authorization authorization)
    {
        super(configuration, authorization);
        twitter = (new TwitterFactory(configuration)).getInstance(authorization);
    }

    private Dispatcher getDispatcher()
    {
        if(dispatcher != null) goto _L2; else goto _L1
_L1:
        twitter4j/AsyncTwitterImpl;
        JVM INSTR monitorenter ;
        if(dispatcher == null)
            dispatcher = (new DispatcherFactory(conf)).getInstance();
        twitter4j/AsyncTwitterImpl;
        JVM INSTR monitorexit ;
_L2:
        return dispatcher;
        Exception exception;
        exception;
        twitter4j/AsyncTwitterImpl;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addListener(TwitterListener twitterlistener)
    {
        listeners.add(twitterlistener);
    }

    public void createBlock(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createBlock(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdBlock(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createBlock(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createBlock(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdBlock(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createFavorite(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                Status status = twitter.createFavorite(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdFavorite(status);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createFriendship(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createFriendship(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdFriendship(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createFriendship(long l, boolean flag)
    {
        getDispatcher().invokeLater(new AsyncTask(l, flag) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createFriendship(userId, follow);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdFriendship(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final boolean val$follow;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                follow = flag;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void createFriendship(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createFriendship(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdFriendship(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createFriendship(String s, boolean flag)
    {
        getDispatcher().invokeLater(new AsyncTask(s, flag) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createFriendship(screenName, follow);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdFriendship(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final boolean val$follow;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                follow = flag;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void createMute(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createMute(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdMute(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createMute(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.createMute(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdMute(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createSavedSearch(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                SavedSearch savedsearch = twitter.createSavedSearch(query);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdSavedSearch(savedsearch);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$query;

            
            {
                this$0 = AsyncTwitterImpl.this;
                query = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createUserList(String s, boolean flag, String s1)
    {
        getDispatcher().invokeLater(new AsyncTask(flag, s1) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserList(listName, isPublicList, description);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$description;
            final boolean val$isPublicList;
            final String val$listName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listName = s;
                isPublicList = flag;
                description = s1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void createUserListMember(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListMember(listId, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdUserListMember(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void createUserListMember(long l, String s, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListMember(ownerId, slug, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdUserListMember(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public transient void createUserListMembers(long l, String s, long al[])
    {
        getDispatcher().invokeLater(new AsyncTask(s, al) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListMembers(ownerId, slug, userIds);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdUserListMembers(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;
            final long val$userIds[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                userIds = al;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public transient void createUserListMembers(long l, String s, String as[])
    {
        getDispatcher().invokeLater(new AsyncTask(s, as) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListMembers(ownerId, slug, screenNames);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdUserListMembers(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$screenNames[];
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                screenNames = as;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public transient void createUserListMembers(long l, long al[])
    {
        getDispatcher().invokeLater(new AsyncTask(l, al) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListMembers(listId, userIds);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdUserListMembers(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;
            final long val$userIds[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                userIds = al;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public transient void createUserListMembers(long l, String as[])
    {
        getDispatcher().invokeLater(new AsyncTask(l, as) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListMembers(listId, screenNames);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.createdUserListMembers(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;
            final String val$screenNames[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                screenNames = as;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void createUserListSubscription(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListSubscription(listId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.subscribedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void createUserListSubscription(long l, String s)
    {
        getDispatcher().invokeLater(new AsyncTask(l, s) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.createUserListSubscription(ownerId, slug);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.subscribedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void destroyBlock(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.destroyBlock(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedBlock(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyBlock(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.destroyBlock(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedBlock(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyDirectMessage(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                DirectMessage directmessage = twitter.destroyDirectMessage(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedDirectMessage(directmessage);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyFavorite(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                Status status = twitter.destroyFavorite(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedFavorite(status);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyFriendship(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.destroyFriendship(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedFriendship(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyFriendship(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.destroyFriendship(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedFriendship(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyMute(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.destroyMute(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedMute(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyMute(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.destroyMute(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedMute(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroySavedSearch(int i)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, i) {

            public void invoke(List list)
                throws TwitterException
            {
                SavedSearch savedsearch = twitter.destroySavedSearch(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedSavedSearch(savedsearch);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final int val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = i;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyStatus(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                Status status = twitter.destroyStatus(statusId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedStatus(status);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$statusId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                statusId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyUserList(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.destroyUserList(listId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyUserList(long l, String s)
    {
        getDispatcher().invokeLater(new AsyncTask(l, s) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.destroyUserList(ownerId, slug);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void destroyUserListMember(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.destroyUserListMember(listId, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedUserListMember(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void destroyUserListMember(long l, String s, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.destroyUserListMember(ownerId, slug, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.destroyedUserListMember(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void destroyUserListSubscription(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.destroyUserListSubscription(listId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.unsubscribedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void destroyUserListSubscription(long l, String s)
    {
        getDispatcher().invokeLater(new AsyncTask(l, s) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.destroyUserListSubscription(ownerId, slug);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.unsubscribedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            if(!super.equals(obj))
                return false;
            obj = (AsyncTwitterImpl)obj;
            if(listeners == null ? ((AsyncTwitterImpl) (obj)).listeners != null : !listeners.equals(((AsyncTwitterImpl) (obj)).listeners))
                return false;
            if(twitter == null ? ((AsyncTwitterImpl) (obj)).twitter != null : !twitter.equals(((AsyncTwitterImpl) (obj)).twitter))
                return false;
        }
        return true;
    }

    public void getAPIConfiguration()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.CONFIGURATION, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                TwitterAPIConfiguration twitterapiconfiguration = twitter.getAPIConfiguration();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotAPIConfiguration(twitterapiconfiguration);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getAccountSettings()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.ACCOUNT_SETTINGS, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                AccountSettings accountsettings = twitter.getAccountSettings();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotAccountSettings(accountsettings);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getAvailableTrends()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.AVAILABLE_TRENDS, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getAvailableTrends();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotAvailableTrends(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getBlocksIDs()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.BLOCK_LIST_IDS, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getBlocksIDs();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotBlockIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getBlocksIDs(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getBlocksIDs(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotBlockIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getBlocksList()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.BLOCK_LIST, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getBlocksList();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotBlocksList(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getBlocksList(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getBlocksList(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotBlocksList(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getClosestTrends(GeoLocation geolocation)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, geolocation) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getClosestTrends(location);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotClosestTrends(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final GeoLocation val$location;

            
            {
                this$0 = AsyncTwitterImpl.this;
                location = geolocation;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getContributees(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getContributors(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotContributees(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getContributees(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getContributors(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotContributees(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getContributors(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getContributors(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotContributors(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getContributors(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getContributors(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotContributors(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getDirectMessages()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.DIRECT_MESSAGES, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getDirectMessages();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotDirectMessages(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getDirectMessages(Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getDirectMessages(paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotDirectMessages(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                paging = paging1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getFavorites()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.FAVORITES, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getFavorites();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFavorites(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getFavorites(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getFavorites(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFavorites(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getFavorites(long l, Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(l, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getFavorites(userId, paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFavorites(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                paging = paging1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFavorites(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getFavorites(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFavorites(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getFavorites(String s, Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(s, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getFavorites(screenName, paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFavorites(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                paging = paging1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFavorites(Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getFavorites(paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFavorites(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                paging = paging1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getFollowersIDs(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getFollowersIDs(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFollowersIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getFollowersIDs(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getFollowersIDs(userId, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFollowersIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFollowersIDs(String s, long l)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getFollowersIDs(screenName, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFollowersIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                cursor = l;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFollowersList(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getFollowersList(userId, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFollowersList(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFollowersList(String s, long l)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getFollowersList(screenName, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFollowersList(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                cursor = l;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFriendsIDs(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getFriendsIDs(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFriendsIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getFriendsIDs(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getFriendsIDs(userId, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFriendsIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFriendsIDs(String s, long l)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getFriendsIDs(screenName, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFriendsIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                cursor = l;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFriendsList(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getFriendsList(userId, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFriendsList(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getFriendsList(String s, long l)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getFriendsList(screenName, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotFriendsList(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                cursor = l;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getGeoDetails(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                Place place = twitter.getGeoDetails(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotGeoDetails(place);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getHomeTimeline()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.HOME_TIMELINE, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getHomeTimeline();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotHomeTimeline(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getHomeTimeline(Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getHomeTimeline(paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotHomeTimeline(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                paging = paging1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getIncomingFriendships(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getIncomingFriendships(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotIncomingFriendships(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getLanguages()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.LANGUAGES, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getLanguages();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotLanguages(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getMemberSuggestions(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getMemberSuggestions(categorySlug);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotMemberSuggestions(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$categorySlug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                categorySlug = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getMentions()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.MENTIONS_TIMELINE, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getMentionsTimeline();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotMentions(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getMentions(Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getMentionsTimeline(paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotMentions(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                paging = paging1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getMutesIDs(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getMutesIDs(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotMuteIDs(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getMutesList(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getMutesList(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotMutesList(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public OAuth2Token getOAuth2Token()
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        OAuth2Token oauth2token = twitter.getOAuth2Token();
        this;
        JVM INSTR monitorexit ;
        return oauth2token;
        Exception exception;
        exception;
        throw exception;
    }

    public void getOAuth2TokenAsync()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.OAUTH_ACCESS_TOKEN, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                OAuth2Token oauth2token = twitter.getOAuth2Token();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuth2Token(oauth2token);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public AccessToken getOAuthAccessToken()
        throws TwitterException
    {
        return twitter.getOAuthAccessToken();
    }

    public AccessToken getOAuthAccessToken(String s)
        throws TwitterException
    {
        return twitter.getOAuthAccessToken(s);
    }

    public AccessToken getOAuthAccessToken(String s, String s1)
        throws TwitterException
    {
        return twitter.getOAuthAccessToken(s, s1);
    }

    public AccessToken getOAuthAccessToken(RequestToken requesttoken)
        throws TwitterException
    {
        return twitter.getOAuthAccessToken(requesttoken);
    }

    public AccessToken getOAuthAccessToken(RequestToken requesttoken, String s)
        throws TwitterException
    {
        return twitter.getOAuthAccessToken(requesttoken, s);
    }

    public void getOAuthAccessTokenAsync()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.OAUTH_ACCESS_TOKEN, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                AccessToken accesstoken = twitter.getOAuthAccessToken();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthAccessToken(accesstoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getOAuthAccessTokenAsync(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                AccessToken accesstoken = twitter.getOAuthAccessToken(oauthVerifier);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthAccessToken(accesstoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$oauthVerifier;

            
            {
                this$0 = AsyncTwitterImpl.this;
                oauthVerifier = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getOAuthAccessTokenAsync(String s, String s1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, s1) {

            public void invoke(List list)
                throws TwitterException
            {
                AccessToken accesstoken = twitter.getOAuthAccessToken(screenName, password);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthAccessToken(accesstoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$password;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                password = s1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getOAuthAccessTokenAsync(RequestToken requesttoken)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, requesttoken) {

            public void invoke(List list)
                throws TwitterException
            {
                AccessToken accesstoken = twitter.getOAuthAccessToken(requestToken);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthAccessToken(accesstoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final RequestToken val$requestToken;

            
            {
                this$0 = AsyncTwitterImpl.this;
                requestToken = requesttoken;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getOAuthAccessTokenAsync(RequestToken requesttoken, String s)
    {
        getDispatcher().invokeLater(new AsyncTask(requesttoken, s) {

            public void invoke(List list)
                throws TwitterException
            {
                AccessToken accesstoken = twitter.getOAuthAccessToken(requestToken, oauthVerifier);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthAccessToken(accesstoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$oauthVerifier;
            final RequestToken val$requestToken;

            
            {
                this$0 = AsyncTwitterImpl.this;
                requestToken = requesttoken;
                oauthVerifier = s;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public RequestToken getOAuthRequestToken()
        throws TwitterException
    {
        return twitter.getOAuthRequestToken();
    }

    public RequestToken getOAuthRequestToken(String s)
        throws TwitterException
    {
        return twitter.getOAuthRequestToken(s);
    }

    public void getOAuthRequestTokenAsync()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.OAUTH_REQUEST_TOKEN, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                RequestToken requesttoken = twitter.getOAuthRequestToken();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthRequestToken(requesttoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getOAuthRequestTokenAsync(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                RequestToken requesttoken = twitter.getOAuthRequestToken(callbackURL);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthRequestToken(requesttoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$callbackURL;

            
            {
                this$0 = AsyncTwitterImpl.this;
                callbackURL = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getOAuthRequestTokenAsync(String s, String s1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, s1) {

            public void invoke(List list)
                throws TwitterException
            {
                RequestToken requesttoken = twitter.getOAuthRequestToken(callbackURL, xAuthAccessType);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthRequestToken(requesttoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$callbackURL;
            final String val$xAuthAccessType;

            
            {
                this$0 = AsyncTwitterImpl.this;
                callbackURL = s;
                xAuthAccessType = s1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getOAuthRequestTokenAsync(String s, String s1, String s2)
    {
        getDispatcher().invokeLater(new AsyncTask(s1, s2) {

            public void invoke(List list)
                throws TwitterException
            {
                RequestToken requesttoken = twitter.getOAuthRequestToken(callbackURL, xAuthAccessType, xAuthMode);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOAuthRequestToken(requesttoken);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$callbackURL;
            final String val$xAuthAccessType;
            final String val$xAuthMode;

            
            {
                this$0 = AsyncTwitterImpl.this;
                callbackURL = s;
                xAuthAccessType = s1;
                xAuthMode = s2;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getOEmbed(OEmbedRequest oembedrequest)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, oembedrequest) {

            public void invoke(List list)
                throws TwitterException
            {
                OEmbed oembed = twitter.getOEmbed(req);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOEmbed(oembed);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final OEmbedRequest val$req;

            
            {
                this$0 = AsyncTwitterImpl.this;
                req = oembedrequest;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getOutgoingFriendships(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                IDs ids = twitter.getOutgoingFriendships(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotOutgoingFriendships(ids);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getPlaceTrends(int i)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, i) {

            public void invoke(List list)
                throws TwitterException
            {
                Trends trends = twitter.getPlaceTrends(woeid);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotPlaceTrends(trends);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final int val$woeid;

            
            {
                this$0 = AsyncTwitterImpl.this;
                woeid = i;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getPrivacyPolicy()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.PRIVACY_POLICY, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                String s = twitter.getPrivacyPolicy();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotPrivacyPolicy(s);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getRateLimitStatus()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.RATE_LIMIT_STATUS, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                java.util.Map map = twitter.getRateLimitStatus();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotRateLimitStatus(map);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public transient void getRateLimitStatus(String as[])
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, as) {

            public void invoke(List list)
                throws TwitterException
            {
                java.util.Map map = twitter.getRateLimitStatus(resources);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotRateLimitStatus(map);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$resources[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                resources = as;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getRetweets(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getRetweets(statusId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotRetweets(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$statusId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                statusId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getRetweetsOfMe()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.RETWEETS_OF_ME, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getRetweetsOfMe();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotRetweetsOfMe(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getRetweetsOfMe(Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getRetweetsOfMe(paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotRetweetsOfMe(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                paging = paging1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getSavedSearches()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.SAVED_SEARCHES, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getSavedSearches();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotSavedSearches(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getSentDirectMessages()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.SENT_DIRECT_MESSAGES, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getSentDirectMessages();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotSentDirectMessages(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getSentDirectMessages(Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getSentDirectMessages(paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotSentDirectMessages(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                paging = paging1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getSimilarPlaces(GeoLocation geolocation, String s, String s1, String s2)
    {
        getDispatcher().invokeLater(new AsyncTask(s1, s2) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getSimilarPlaces(location, name, containedWithin, streetAddress);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotSimilarPlaces(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$containedWithin;
            final GeoLocation val$location;
            final String val$name;
            final String val$streetAddress;

            
            {
                this$0 = AsyncTwitterImpl.this;
                location = geolocation;
                name = s;
                containedWithin = s1;
                streetAddress = s2;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getSuggestedUserCategories()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.SUGGESTED_USER_CATEGORIES, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getSuggestedUserCategories();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotSuggestedUserCategories(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getTermsOfService()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.TERMS_OF_SERVICE, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                String s = twitter.getTermsOfService();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotTermsOfService(s);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getUserListMembers(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListMembers(listId, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListMembers(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$listId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListMembers(long l, String s, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListMembers(ownerId, slug, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListMembers(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$ownerId;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListMemberships(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListMemberships(cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListMemberships(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;

            
            {
                this$0 = AsyncTwitterImpl.this;
                cursor = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getUserListMemberships(long l, long l1)
    {
        getUserListMemberships(l, l1, false);
    }

    public void getUserListMemberships(long l, long l1, boolean flag)
    {
        getDispatcher().invokeLater(new AsyncTask(l1, flag) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListMemberships(listMemberId, cursor, filterToOwnedLists);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListMemberships(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final boolean val$filterToOwnedLists;
            final long val$listMemberId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listMemberId = l;
                cursor = l1;
                filterToOwnedLists = flag;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListMemberships(String s, long l)
    {
        getUserListMemberships(s, l, false);
    }

    public void getUserListMemberships(String s, long l, boolean flag)
    {
        getDispatcher().invokeLater(new AsyncTask(l, flag) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListMemberships(listMemberScreenName, cursor, filterToOwnedLists);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListMemberships(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final boolean val$filterToOwnedLists;
            final String val$listMemberScreenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listMemberScreenName = s;
                cursor = l;
                filterToOwnedLists = flag;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListStatuses(long l, String s, Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(s, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserListStatuses(ownerId, slug, paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListStatuses(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final Paging val$paging;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                paging = paging1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListStatuses(long l, Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(l, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserListStatuses(listId, paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListStatuses(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                paging = paging1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListSubscribers(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListSubscribers(listId, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListSubscribers(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$listId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListSubscribers(long l, String s, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListSubscribers(ownerId, slug, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListSubscribers(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final long val$ownerId;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                cursor = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserListSubscriptions(String s, long l)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l) {

            public void invoke(List list)
                throws TwitterException
            {
                PagableResponseList pagableresponselist = twitter.getUserListSubscriptions(listOwnerScreenName, cursor);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserListSubscriptions(pagableresponselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$cursor;
            final String val$listOwnerScreenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listOwnerScreenName = s;
                cursor = l;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserLists(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserLists(listOwnerUserId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserLists(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listOwnerUserId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listOwnerUserId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getUserLists(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserLists(listOwnerScreenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserLists(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$listOwnerScreenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listOwnerScreenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getUserSuggestions(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserSuggestions(categorySlug);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserSuggestions(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$categorySlug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                categorySlug = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void getUserTimeline()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.USER_TIMELINE, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserTimeline();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserTimeline(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void getUserTimeline(long l)
    {
        getUserTimeline(l, new Paging());
    }

    public void getUserTimeline(long l, Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(l, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserTimeline(userId, paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserTimeline(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                paging = paging1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserTimeline(String s)
    {
        getUserTimeline(s, new Paging());
    }

    public void getUserTimeline(String s, Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(s, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserTimeline(screenName, paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserTimeline(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                paging = paging1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void getUserTimeline(Paging paging)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, paging) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.getUserTimeline(paging);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserTimeline(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Paging val$paging;

            
            {
                this$0 = AsyncTwitterImpl.this;
                paging = paging1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public int hashCode()
    {
        int j = 0;
        int k = super.hashCode();
        int i;
        if(twitter != null)
            i = twitter.hashCode();
        else
            i = 0;
        if(listeners != null)
            j = listeners.hashCode();
        return (k * 31 + i) * 31 + j;
    }

    public void invalidateOAuth2Token()
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        twitter.invalidateOAuth2Token();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public transient void lookup(long al[])
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, al) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.lookup(ids);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.lookedup(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ids[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                ids = al;
                super(final_twittermethod, list);
            }
        }
);
    }

    public transient void lookupFriendships(long al[])
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, al) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.lookupFriendships(ids);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.lookedUpFriendships(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ids[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                ids = al;
                super(final_twittermethod, list);
            }
        }
);
    }

    public transient void lookupFriendships(String as[])
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, as) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.lookupFriendships(screenNames);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.lookedUpFriendships(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenNames[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenNames = as;
                super(final_twittermethod, list);
            }
        }
);
    }

    public transient void lookupUsers(long al[])
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, al) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.lookupUsers(ids);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.lookedupUsers(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ids[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                ids = al;
                super(final_twittermethod, list);
            }
        }
);
    }

    public transient void lookupUsers(String as[])
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, as) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.lookupUsers(screenNames);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.lookedupUsers(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenNames[];

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenNames = as;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void removeProfileBanner()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.REMOVE_PROFILE_BANNER, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                twitter.removeProfileBanner();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.removedProfileBanner();
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    public void reportSpam(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.reportSpam(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.reportedSpam(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void reportSpam(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.reportSpam(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.reportedSpam(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void retweetStatus(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                Status status = twitter.retweetStatus(statusId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.retweetedStatus(status);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$statusId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                statusId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void reverseGeoCode(GeoQuery geoquery)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, geoquery) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.reverseGeoCode(query);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotReverseGeoCode(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final GeoQuery val$query;

            
            {
                this$0 = AsyncTwitterImpl.this;
                query = geoquery;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void search(Query query)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, query) {

            public void invoke(List list)
                throws TwitterException
            {
                QueryResult queryresult = twitter.search(query);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.searched(queryresult);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final Query val$query;

            
            {
                this$0 = AsyncTwitterImpl.this;
                query = query1;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void searchPlaces(GeoQuery geoquery)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, geoquery) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.searchPlaces(query);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.searchedPlaces(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final GeoQuery val$query;

            
            {
                this$0 = AsyncTwitterImpl.this;
                query = geoquery;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void searchUsers(String s, int i)
    {
        getDispatcher().invokeLater(new AsyncTask(s, i) {

            public void invoke(List list)
                throws TwitterException
            {
                ResponseList responselist = twitter.searchUsers(query, page);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.searchedUser(responselist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final int val$page;
            final String val$query;

            
            {
                this$0 = AsyncTwitterImpl.this;
                query = s;
                page = i;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void sendDirectMessage(long l, String s)
    {
        getDispatcher().invokeLater(new AsyncTask(l, s) {

            public void invoke(List list)
                throws TwitterException
            {
                DirectMessage directmessage = twitter.sendDirectMessage(userId, text);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.sentDirectMessage(directmessage);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$text;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                text = s;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void sendDirectMessage(String s, String s1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, s1) {

            public void invoke(List list)
                throws TwitterException
            {
                DirectMessage directmessage = twitter.sendDirectMessage(screenName, text);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.sentDirectMessage(directmessage);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;
            final String val$text;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                text = s1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void setOAuth2Token(OAuth2Token oauth2token)
    {
        twitter.setOAuth2Token(oauth2token);
    }

    public void setOAuthAccessToken(AccessToken accesstoken)
    {
        twitter.setOAuthAccessToken(accesstoken);
    }

    public void setOAuthConsumer(String s, String s1)
    {
        twitter.setOAuthConsumer(s, s1);
    }

    public void showDirectMessage(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                DirectMessage directmessage = twitter.showDirectMessage(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotDirectMessage(directmessage);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void showFriendship(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                Relationship relationship = twitter.showFriendship(sourceId, targetId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotShowFriendship(relationship);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                sourceId = l;
                targetId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void showFriendship(String s, String s1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, s1) {

            public void invoke(List list)
                throws TwitterException
            {
                Relationship relationship = twitter.showFriendship(sourceScreenName, targetScreenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotShowFriendship(relationship);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$sourceScreenName;
            final String val$targetScreenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                sourceScreenName = s;
                targetScreenName = s1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void showSavedSearch(int i)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, i) {

            public void invoke(List list)
                throws TwitterException
            {
                SavedSearch savedsearch = twitter.showSavedSearch(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotSavedSearch(savedsearch);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final int val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = i;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void showStatus(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                Status status = twitter.showStatus(id);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotShowStatus(status);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$id;

            
            {
                this$0 = AsyncTwitterImpl.this;
                id = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void showUser(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.showUser(userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserDetail(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void showUser(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.showUser(screenName);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotUserDetail(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void showUserList(long l)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, l) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.showUserList(listId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotShowUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void showUserList(long l, String s)
    {
        getDispatcher().invokeLater(new AsyncTask(l, s) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.showUserList(ownerId, slug);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.gotShowUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void showUserListMembership(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.showUserListMembership(listId, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.checkedUserListMembership(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void showUserListMembership(long l, String s, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.showUserListMembership(ownerId, slug, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.checkedUserListMembership(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void showUserListSubscription(long l, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(l, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.showUserListSubscription(listId, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.checkedUserListSubscription(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$listId;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void showUserListSubscription(long l, String s, long l1)
    {
        getDispatcher().invokeLater(new AsyncTask(s, l1) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.showUserListSubscription(ownerId, slug, userId);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.checkedUserListSubscription(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final long val$ownerId;
            final String val$slug;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                userId = l1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void shutdown()
    {
        twitter4j/AsyncTwitterImpl;
        JVM INSTR monitorenter ;
        if(dispatcher != null)
        {
            dispatcher.shutdown();
            dispatcher = null;
        }
        twitter4j/AsyncTwitterImpl;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        twitter4j/AsyncTwitterImpl;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String toString()
    {
        return (new StringBuilder()).append("AsyncTwitterImpl{twitter=").append(twitter).append(", listeners=").append(listeners).append('}').toString();
    }

    public void updateAccountSettings(Integer integer, Boolean boolean1, String s, String s1, String s2, String s3)
    {
        getDispatcher().invokeLater(new AsyncTask(s2, s3) {

            public void invoke(List list)
                throws TwitterException
            {
                AccountSettings accountsettings = twitter.updateAccountSettings(trend_locationWoeid, sleep_timeEnabled, start_sleepTime, end_sleepTime, time_zone, lang);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedAccountSettings(accountsettings);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$end_sleepTime;
            final String val$lang;
            final Boolean val$sleep_timeEnabled;
            final String val$start_sleepTime;
            final String val$time_zone;
            final Integer val$trend_locationWoeid;

            
            {
                this$0 = AsyncTwitterImpl.this;
                trend_locationWoeid = integer;
                sleep_timeEnabled = boolean1;
                start_sleepTime = s;
                end_sleepTime = s1;
                time_zone = s2;
                lang = s3;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void updateFriendship(long l, boolean flag, boolean flag1)
    {
        getDispatcher().invokeLater(new AsyncTask(flag, flag1) {

            public void invoke(List list)
                throws TwitterException
            {
                Relationship relationship = twitter.updateFriendship(userId, enableDeviceNotification, retweet);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedFriendship(relationship);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final boolean val$enableDeviceNotification;
            final boolean val$retweet;
            final long val$userId;

            
            {
                this$0 = AsyncTwitterImpl.this;
                userId = l;
                enableDeviceNotification = flag;
                retweet = flag1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void updateFriendship(String s, boolean flag, boolean flag1)
    {
        getDispatcher().invokeLater(new AsyncTask(flag, flag1) {

            public void invoke(List list)
                throws TwitterException
            {
                Relationship relationship = twitter.updateFriendship(screenName, enableDeviceNotification, retweet);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedFriendship(relationship);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final boolean val$enableDeviceNotification;
            final boolean val$retweet;
            final String val$screenName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                screenName = s;
                enableDeviceNotification = flag;
                retweet = flag1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void updateProfile(String s, String s1, String s2, String s3)
    {
        getDispatcher().invokeLater(new AsyncTask(s2, s3) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.updateProfile(name, url, location, description);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedProfile(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$description;
            final String val$location;
            final String val$name;
            final String val$url;

            
            {
                this$0 = AsyncTwitterImpl.this;
                name = s;
                url = s1;
                location = s2;
                description = s3;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void updateProfileBackgroundImage(File file, boolean flag)
    {
        getDispatcher().invokeLater(new AsyncTask(file, flag) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.updateProfileBackgroundImage(image, tile);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedProfileBackgroundImage(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final File val$image;
            final boolean val$tile;

            
            {
                this$0 = AsyncTwitterImpl.this;
                image = file;
                tile = flag;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void updateProfileBackgroundImage(InputStream inputstream, boolean flag)
    {
        getDispatcher().invokeLater(new AsyncTask(inputstream, flag) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.updateProfileBackgroundImage(image, tile);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedProfileBackgroundImage(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final InputStream val$image;
            final boolean val$tile;

            
            {
                this$0 = AsyncTwitterImpl.this;
                image = inputstream;
                tile = flag;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void updateProfileBanner(File file)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, file) {

            public void invoke(List list)
                throws TwitterException
            {
                twitter.updateProfileBanner(image);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedProfileBanner();
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final File val$image;

            
            {
                this$0 = AsyncTwitterImpl.this;
                image = file;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void updateProfileBanner(InputStream inputstream)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, inputstream) {

            public void invoke(List list)
                throws TwitterException
            {
                twitter.updateProfileBanner(image);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedProfileBanner();
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final InputStream val$image;

            
            {
                this$0 = AsyncTwitterImpl.this;
                image = inputstream;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void updateProfileColors(String s, String s1, String s2, String s3, String s4)
    {
        throw new UnsupportedOperationException("this API is no longer supported. https://twittercommunity.com/t/deprecation-of-account-update-profile-colors/28692");
    }

    public void updateProfileImage(File file)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, file) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.updateProfileImage(image);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedProfileImage(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final File val$image;

            
            {
                this$0 = AsyncTwitterImpl.this;
                image = file;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void updateProfileImage(InputStream inputstream)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, inputstream) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.updateProfileImage(image);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedProfileImage(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final InputStream val$image;

            
            {
                this$0 = AsyncTwitterImpl.this;
                image = inputstream;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void updateStatus(String s)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, s) {

            public void invoke(List list)
                throws TwitterException
            {
                Status status = twitter.updateStatus(statusText);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedStatus(status);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final String val$statusText;

            
            {
                this$0 = AsyncTwitterImpl.this;
                statusText = s;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void updateStatus(StatusUpdate statusupdate)
    {
        getDispatcher().invokeLater(new AsyncTask(listeners, statusupdate) {

            public void invoke(List list)
                throws TwitterException
            {
                Status status = twitter.updateStatus(latestStatus);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedStatus(status);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final StatusUpdate val$latestStatus;

            
            {
                this$0 = AsyncTwitterImpl.this;
                latestStatus = statusupdate;
                super(final_twittermethod, list);
            }
        }
);
    }

    public void updateUserList(long l, String s, String s1, boolean flag, String s2)
    {
        getDispatcher().invokeLater(new AsyncTask(flag, s2) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.updateUserList(ownerId, slug, newListName, isPublicList, newDescription);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final boolean val$isPublicList;
            final String val$newDescription;
            final String val$newListName;
            final long val$ownerId;
            final String val$slug;

            
            {
                this$0 = AsyncTwitterImpl.this;
                ownerId = l;
                slug = s;
                newListName = s1;
                isPublicList = flag;
                newDescription = s2;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void updateUserList(long l, String s, boolean flag, String s1)
    {
        getDispatcher().invokeLater(new AsyncTask(flag, s1) {

            public void invoke(List list)
                throws TwitterException
            {
                UserList userlist = twitter.updateUserList(listId, newListName, isPublicList, newDescription);
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.updatedUserList(userlist);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;
            final boolean val$isPublicList;
            final long val$listId;
            final String val$newDescription;
            final String val$newListName;

            
            {
                this$0 = AsyncTwitterImpl.this;
                listId = l;
                newListName = s;
                isPublicList = flag;
                newDescription = s1;
                super(final_twittermethod, final_list);
            }
        }
);
    }

    public void verifyCredentials()
    {
        getDispatcher().invokeLater(new AsyncTask(TwitterMethod.VERIFY_CREDENTIALS, listeners) {

            public void invoke(List list)
                throws TwitterException
            {
                User user = twitter.verifyCredentials();
                for(list = list.iterator(); list.hasNext();)
                {
                    TwitterListener twitterlistener = (TwitterListener)list.next();
                    try
                    {
                        twitterlistener.verifiedCredentials(user);
                    }
                    catch(Exception exception) { }
                }

            }

            final AsyncTwitterImpl this$0;

            
            {
                this$0 = AsyncTwitterImpl.this;
                super(twittermethod, list);
            }
        }
);
    }

    private static volatile transient Dispatcher dispatcher;
    private static final long serialVersionUID = 0x96bbb6bbL;
    private final List listeners = new ArrayList();
    private final Twitter twitter;

}
