// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.*;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            StatusStream, StatusStreamImpl, Logger, JSONImplFactory, 
//            HttpResponse, TwitterException, JSONException, JSONObject, 
//            DirectMessageJSONImpl, TwitterObjectFactory, JSONArray, StatusJSONImpl, 
//            UserJSONImpl, UserListJSONImpl, Dispatcher, StreamListener, 
//            RawStreamListener, ObjectFactory, DirectMessage, Status, 
//            User, UserList, StatusListener, JSONObjectType, 
//            HttpClientConfiguration

abstract class StatusStreamBase
    implements StatusStream
{
    abstract class StreamEvent
        implements Runnable
    {

        String line;
        final StatusStreamBase this$0;

        StreamEvent(String s)
        {
            this$0 = StatusStreamBase.this;
            super();
            line = s;
        }
    }


    StatusStreamBase(Dispatcher dispatcher1, InputStream inputstream, Configuration configuration)
        throws IOException
    {
        streamAlive = true;
        is = inputstream;
        br = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
        dispatcher = dispatcher1;
        CONF = configuration;
        factory = new JSONImplFactory(configuration);
    }

    StatusStreamBase(Dispatcher dispatcher1, HttpResponse httpresponse, Configuration configuration)
        throws IOException
    {
        this(dispatcher1, httpresponse.asStream(), configuration);
        response = httpresponse;
    }

    DirectMessage asDirectMessage(JSONObject jsonobject)
        throws TwitterException
    {
        DirectMessageJSONImpl directmessagejsonimpl;
        try
        {
            jsonobject = jsonobject.getJSONObject("direct_message");
            directmessagejsonimpl = new DirectMessageJSONImpl(jsonobject);
            if(CONF.isJSONStoreEnabled())
                TwitterObjectFactory.registerJSONObject(directmessagejsonimpl, jsonobject);
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject)
        {
            throw new TwitterException(jsonobject);
        }
        return directmessagejsonimpl;
    }

    long[] asFriendList(JSONObject jsonobject)
        throws TwitterException
    {
        long al[];
        int i;
        try
        {
            jsonobject = jsonobject.getJSONArray("friends");
            al = new long[jsonobject.length()];
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject)
        {
            throw new TwitterException(jsonobject);
        }
        i = 0;
        if(i >= al.length)
            break; /* Loop/switch isn't completed */
        al[i] = Long.parseLong(jsonobject.getString(i));
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_16;
_L1:
        return al;
    }

    Status asStatus(JSONObject jsonobject)
        throws TwitterException
    {
        StatusJSONImpl statusjsonimpl = new StatusJSONImpl(jsonobject);
        if(CONF.isJSONStoreEnabled())
            TwitterObjectFactory.registerJSONObject(statusjsonimpl, jsonobject);
        return statusjsonimpl;
    }

    User asUser(JSONObject jsonobject)
        throws TwitterException
    {
        UserJSONImpl userjsonimpl = new UserJSONImpl(jsonobject);
        if(CONF.isJSONStoreEnabled())
            TwitterObjectFactory.registerJSONObject(userjsonimpl, jsonobject);
        return userjsonimpl;
    }

    UserList asUserList(JSONObject jsonobject)
        throws TwitterException
    {
        UserListJSONImpl userlistjsonimpl = new UserListJSONImpl(jsonobject);
        if(CONF.isJSONStoreEnabled())
            TwitterObjectFactory.registerJSONObject(userlistjsonimpl, jsonobject);
        return userlistjsonimpl;
    }

    public void close()
        throws IOException
    {
        streamAlive = false;
        is.close();
        br.close();
        if(response != null)
            response.disconnect();
        onClose();
    }

    void handleNextElement(StreamListener astreamlistener[], final RawStreamListener rawStreamListeners[])
        throws TwitterException
    {
        if(!streamAlive)
            throw new IllegalStateException("Stream already closed.");
        final String final_s = br.readLine();
        if(final_s == null)
        {
            try
            {
                throw new IOException("the end of the stream has been reached");
            }
            // Misplaced declaration of an exception variable
            catch(StreamListener astreamlistener[]) { }
            boolean flag;
            try
            {
                is.close();
            }
            // Misplaced declaration of an exception variable
            catch(final RawStreamListener rawStreamListeners[]) { }
            flag = streamAlive;
            streamAlive = false;
            onClose();
            if(flag)
                throw new TwitterException("Stream closed.", astreamlistener);
            break MISSING_BLOCK_LABEL_99;
        }
        dispatcher.invokeLater(new StreamEvent(astreamlistener) {

            public void run()
            {
                if(rawStreamListeners.length > 0)
                    onMessage(line, rawStreamListeners);
                line = parseLine(line);
                if(line == null || line.length() <= 0 || listeners.length <= 0) goto _L2; else goto _L1
_L1:
                JSONObject jsonobject;
                Object obj1;
                if(CONF.isJSONStoreEnabled())
                    TwitterObjectFactory.clearThreadLocalMap();
                jsonobject = new JSONObject(line);
                obj1 = JSONObjectType.determine(jsonobject);
                if(!StatusStreamBase.logger.isDebugEnabled()) goto _L4; else goto _L3
_L3:
                Logger logger1 = StatusStreamBase.logger;
                if(!CONF.getHttpClientConfiguration().isPrettyDebugEnabled()) goto _L6; else goto _L5
_L5:
                Object obj = jsonobject.toString(1);
_L40:
                logger1.debug("Received:", ((String) (obj)));
_L4:
                static class _cls2
                {

                    static final int $SwitchMap$twitter4j$JSONObjectType$Type[];

                    static 
                    {
                        $SwitchMap$twitter4j$JSONObjectType$Type = new int[JSONObjectType.Type.values().length];
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.SENDER.ordinal()] = 1;
                        }
                        catch(NoSuchFieldError nosuchfielderror28) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.STATUS.ordinal()] = 2;
                        }
                        catch(NoSuchFieldError nosuchfielderror27) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.DIRECT_MESSAGE.ordinal()] = 3;
                        }
                        catch(NoSuchFieldError nosuchfielderror26) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.DELETE.ordinal()] = 4;
                        }
                        catch(NoSuchFieldError nosuchfielderror25) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.LIMIT.ordinal()] = 5;
                        }
                        catch(NoSuchFieldError nosuchfielderror24) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.STALL_WARNING.ordinal()] = 6;
                        }
                        catch(NoSuchFieldError nosuchfielderror23) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.SCRUB_GEO.ordinal()] = 7;
                        }
                        catch(NoSuchFieldError nosuchfielderror22) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.FRIENDS.ordinal()] = 8;
                        }
                        catch(NoSuchFieldError nosuchfielderror21) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.FAVORITE.ordinal()] = 9;
                        }
                        catch(NoSuchFieldError nosuchfielderror20) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.UNFAVORITE.ordinal()] = 10;
                        }
                        catch(NoSuchFieldError nosuchfielderror19) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.FOLLOW.ordinal()] = 11;
                        }
                        catch(NoSuchFieldError nosuchfielderror18) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.UNFOLLOW.ordinal()] = 12;
                        }
                        catch(NoSuchFieldError nosuchfielderror17) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_LIST_MEMBER_ADDED.ordinal()] = 13;
                        }
                        catch(NoSuchFieldError nosuchfielderror16) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_LIST_MEMBER_DELETED.ordinal()] = 14;
                        }
                        catch(NoSuchFieldError nosuchfielderror15) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_LIST_SUBSCRIBED.ordinal()] = 15;
                        }
                        catch(NoSuchFieldError nosuchfielderror14) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_LIST_UNSUBSCRIBED.ordinal()] = 16;
                        }
                        catch(NoSuchFieldError nosuchfielderror13) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_LIST_CREATED.ordinal()] = 17;
                        }
                        catch(NoSuchFieldError nosuchfielderror12) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_LIST_UPDATED.ordinal()] = 18;
                        }
                        catch(NoSuchFieldError nosuchfielderror11) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_LIST_DESTROYED.ordinal()] = 19;
                        }
                        catch(NoSuchFieldError nosuchfielderror10) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_UPDATE.ordinal()] = 20;
                        }
                        catch(NoSuchFieldError nosuchfielderror9) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_DELETE.ordinal()] = 21;
                        }
                        catch(NoSuchFieldError nosuchfielderror8) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.USER_SUSPEND.ordinal()] = 22;
                        }
                        catch(NoSuchFieldError nosuchfielderror7) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.BLOCK.ordinal()] = 23;
                        }
                        catch(NoSuchFieldError nosuchfielderror6) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.UNBLOCK.ordinal()] = 24;
                        }
                        catch(NoSuchFieldError nosuchfielderror5) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.RETWEETED_RETWEET.ordinal()] = 25;
                        }
                        catch(NoSuchFieldError nosuchfielderror4) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.FAVORITED_RETWEET.ordinal()] = 26;
                        }
                        catch(NoSuchFieldError nosuchfielderror3) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.QUOTED_TWEET.ordinal()] = 27;
                        }
                        catch(NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.DISCONNECTION.ordinal()] = 28;
                        }
                        catch(NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.Type.UNKNOWN.ordinal()] = 29;
                        }
                        catch(NoSuchFieldError nosuchfielderror)
                        {
                            return;
                        }
                    }
                }

                _cls2..SwitchMap.twitter4j.JSONObjectType.Type[((JSONObjectType.Type) (obj1)).ordinal()];
                JVM INSTR tableswitch 1 28: default 965
            //                           1 326
            //                           2 353
            //                           3 366
            //                           4 379
            //                           5 392
            //                           6 405
            //                           7 418
            //                           8 431
            //                           9 444
            //                           10 474
            //                           11 504
            //                           12 528
            //                           13 552
            //                           14 582
            //                           15 612
            //                           16 642
            //                           17 672
            //                           18 696
            //                           19 720
            //                           20 744
            //                           21 768
            //                           22 786
            //                           23 804
            //                           24 828
            //                           25 852
            //                           26 882
            //                           27 912
            //                           28 941;
                   goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35
_L7:
                obj1 = StatusStreamBase.logger;
                if(!CONF.getHttpClientConfiguration().isPrettyDebugEnabled()) goto _L37; else goto _L36
_L36:
                obj = jsonobject.toString(1);
_L38:
                ((Logger) (obj1)).warn("Received unknown event:", ((String) (obj)));
                return;
_L6:
                obj = jsonobject.toString();
                continue; /* Loop/switch isn't completed */
_L8:
                try
                {
                    onSender(jsonobject, listeners);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    onException(((Exception) (obj)), listeners);
                }
                return;
_L9:
                onStatus(jsonobject, listeners);
                return;
_L10:
                onDirectMessage(jsonobject, listeners);
                return;
_L11:
                onDelete(jsonobject, listeners);
                return;
_L12:
                onLimit(jsonobject, listeners);
                return;
_L13:
                onStallWarning(jsonobject, listeners);
                return;
_L14:
                onScrubGeo(jsonobject, listeners);
                return;
_L15:
                onFriends(jsonobject, listeners);
                return;
_L16:
                onFavorite(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L17:
                onUnfavorite(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L18:
                onFollow(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), listeners);
                return;
_L19:
                onUnfollow(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), listeners);
                return;
_L20:
                onUserListMemberAddition(jsonobject.getJSONObject("target"), jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L21:
                onUserListMemberDeletion(jsonobject.getJSONObject("target"), jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L22:
                onUserListSubscription(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L23:
                onUserListUnsubscription(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L24:
                onUserListCreation(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L25:
                onUserListUpdated(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L26:
                onUserListDestroyed(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L27:
                onUserUpdate(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), listeners);
                return;
_L28:
                onUserDeletion(jsonobject.getLong("target"), listeners);
                return;
_L29:
                onUserSuspension(jsonobject.getLong("target"), listeners);
                return;
_L30:
                onBlock(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), listeners);
                return;
_L31:
                onUnblock(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), listeners);
                return;
_L32:
                onRetweetedRetweet(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L33:
                onFavoritedRetweet(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), jsonobject.getJSONObject("target_object"), listeners);
                return;
_L34:
                onQuotedTweet(jsonobject.getJSONObject("source"), jsonobject.getJSONObject("target"), jsonobject.getJSONObject("target_object"), listeners);
_L35:
                onDisconnectionNotice(line, listeners);
                return;
_L37:
                obj = jsonobject.toString();
                  goto _L38
_L2:
                return;
                if(true) goto _L40; else goto _L39
_L39:
            }

            final StatusStreamBase this$0;
            final StreamListener val$listeners[];
            final RawStreamListener val$rawStreamListeners[];

            
            {
                this$0 = StatusStreamBase.this;
                rawStreamListeners = arawstreamlistener;
                listeners = astreamlistener;
                super(final_s);
            }
        }
);
    }

    public abstract void next(StatusListener statuslistener)
        throws TwitterException;

    public abstract void next(StreamListener astreamlistener[], RawStreamListener arawstreamlistener[])
        throws TwitterException;

    void onBlock(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onBlock");
    }

    protected abstract void onClose();

    void onDelete(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onDelete");
    }

    void onDirectMessage(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onDirectMessage");
    }

    void onDisconnectionNotice(String s, StreamListener astreamlistener[])
    {
        logger.warn("Unhandled event: ", s);
    }

    void onException(Exception exception, StreamListener astreamlistener[])
    {
        logger.warn("Unhandled event: ", exception.getMessage());
    }

    public void onException(Exception exception, StreamListener astreamlistener[], RawStreamListener arawstreamlistener[])
    {
        boolean flag = false;
        int k = astreamlistener.length;
        for(int i = 0; i < k; i++)
            astreamlistener[i].onException(exception);

        k = arawstreamlistener.length;
        for(int j = ((flag) ? 1 : 0); j < k; j++)
            arawstreamlistener[j].onException(exception);

    }

    void onFavorite(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onFavorite");
    }

    void onFavoritedRetweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onFavoritedRetweet");
    }

    void onFollow(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onFollow");
    }

    void onFriends(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onFriends");
    }

    void onLimit(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onLimit");
    }

    void onMessage(String s, RawStreamListener arawstreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onMessage");
    }

    void onQuotedTweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onQuotedTweet");
    }

    void onRetweetedRetweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onRetweetedRetweet");
    }

    void onScrubGeo(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onScrubGeo");
    }

    void onSender(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onSender");
    }

    void onStallWarning(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onStallWarning");
    }

    void onStatus(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onStatus");
    }

    void onUnblock(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onUnblock");
    }

    void onUnfavorite(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onUnfavorite");
    }

    void onUnfollow(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onUnfollow");
    }

    void onUserDeletion(long l, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onUserDeletion");
    }

    void onUserListCreation(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onUserListCreation");
    }

    void onUserListDestroyed(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onUserListDestroyed");
    }

    void onUserListMemberAddition(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onUserListMemberAddition");
    }

    void onUserListMemberDeletion(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onUserListMemberDeletion");
    }

    void onUserListSubscription(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onUserListSubscription");
    }

    void onUserListUnsubscription(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onUserListUnsubscription");
    }

    void onUserListUpdated(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        logger.warn("Unhandled event: onUserListUpdated");
    }

    void onUserSuspension(long l, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onUserSuspension");
    }

    void onUserUpdate(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.warn("Unhandled event: onUserUpdate");
    }

    String parseLine(String s)
    {
        return s;
    }

    static final Logger logger = Logger.getLogger(twitter4j/StatusStreamImpl);
    final Configuration CONF;
    private BufferedReader br;
    private final Dispatcher dispatcher;
    private ObjectFactory factory;
    private InputStream is;
    private HttpResponse response;
    private boolean streamAlive;

}
