// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            StatusStreamBase, RawStreamListener, TwitterException, StatusListener, 
//            SiteStreamsListener, StreamController, JSONException, JSONObject, 
//            StatusDeletionNoticeImpl, ParseUtil, StreamListener, Logger, 
//            Dispatcher, HttpResponse

final class SiteStreamsImpl extends StatusStreamBase
{

    SiteStreamsImpl(Dispatcher dispatcher, InputStream inputstream, Configuration configuration, StreamController streamcontroller)
        throws IOException
    {
        super(dispatcher, inputstream, configuration);
        cs = streamcontroller;
    }

    SiteStreamsImpl(Dispatcher dispatcher, HttpResponse httpresponse, Configuration configuration, StreamController streamcontroller)
        throws IOException
    {
        super(dispatcher, httpresponse, configuration);
        cs = streamcontroller;
    }

    public void next(StatusListener statuslistener)
        throws TwitterException
    {
        RawStreamListener arawstreamlistener[] = EMPTY;
        handleNextElement(new StatusListener[] {
            statuslistener
        }, arawstreamlistener);
    }

    public void next(StreamListener astreamlistener[], RawStreamListener arawstreamlistener[])
        throws TwitterException
    {
        handleNextElement(astreamlistener, arawstreamlistener);
    }

    protected void onBlock(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onBlock(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onClose()
    {
        cs.setControlURI(null);
    }

    protected void onDelete(JSONObject jsonobject, StreamListener astreamlistener[])
        throws JSONException
    {
        jsonobject = jsonobject.getJSONObject("delete");
        if(jsonobject.has("status"))
        {
            int k = astreamlistener.length;
            for(int i = 0; i < k; i++)
                ((SiteStreamsListener)astreamlistener[i]).onDeletionNotice(((Long)forUser.get()).longValue(), new StatusDeletionNoticeImpl(jsonobject.getJSONObject("status")));

        } else
        {
            jsonobject = jsonobject.getJSONObject("direct_message");
            int l = astreamlistener.length;
            for(int j = 0; j < l; j++)
                ((SiteStreamsListener)astreamlistener[j]).onDeletionNotice(((Long)forUser.get()).longValue(), ParseUtil.getInt("id", jsonobject), ParseUtil.getLong("user_id", jsonobject));

        }
    }

    protected void onDirectMessage(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onDirectMessage(((Long)forUser.get()).longValue(), asDirectMessage(jsonobject));

    }

    public void onDisconnectionNotice(String s, StreamListener astreamlistener[])
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onDisconnectionNotice(s);

    }

    public void onException(Exception exception, StreamListener astreamlistener[])
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            astreamlistener[i].onException(exception);

    }

    protected void onFavorite(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onFavorite(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    void onFavoritedRetweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onFavoritedRetweet(asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    protected void onFollow(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onFollow(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onFriends(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onFriendList(((Long)forUser.get()).longValue(), asFriendList(jsonobject));

    }

    protected void onMessage(String s, RawStreamListener arawstreamlistener[])
        throws TwitterException
    {
        int j = arawstreamlistener.length;
        for(int i = 0; i < j; i++)
            arawstreamlistener[i].onMessage(s);

    }

    void onRetweetedRetweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onRetweetedRetweet(asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    protected void onStatus(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onStatus(((Long)forUser.get()).longValue(), asStatus(jsonobject));

    }

    protected void onUnblock(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUnblock(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onUnfavorite(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUnfavorite(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    protected void onUnfollow(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUnfollow(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onUserDeletion(long l, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserDeletion(((Long)forUser.get()).longValue(), l);

    }

    protected void onUserListCreation(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserListCreation(((Long)forUser.get()).longValue(), asUser(jsonobject), asUserList(jsonobject1));

    }

    protected void onUserListDestroyed(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserListDeletion(((Long)forUser.get()).longValue(), asUser(jsonobject), asUserList(jsonobject1));

    }

    protected void onUserListMemberAddition(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserListMemberAddition(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListMemberDeletion(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserListMemberDeletion(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListSubscription(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserListSubscription(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListUnsubscription(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserListUnsubscription(((Long)forUser.get()).longValue(), asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListUpdated(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserListUpdate(((Long)forUser.get()).longValue(), asUser(jsonobject), asUserList(jsonobject1));

    }

    protected void onUserSuspension(long l, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserSuspension(((Long)forUser.get()).longValue(), l);

    }

    protected void onUserUpdate(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((SiteStreamsListener)astreamlistener[i]).onUserProfileUpdate(((Long)forUser.get()).longValue(), asUser(jsonobject));

    }

    protected String parseLine(String s)
    {
        if(!"".equals(s) && s != null) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        int i;
        i = s.indexOf(',', 12);
        if(cs.getControlURI() != null || s.charAt(2) != 'c' || s.charAt(3) != 'o' || s.charAt(4) != 'n')
            continue; /* Loop/switch isn't completed */
        JSONObject jsonobject = new JSONObject(s);
        cs.setControlURI((new StringBuilder()).append(CONF.getSiteStreamBaseURL()).append(jsonobject.getJSONObject("control").getString("control_uri")).toString());
        logger.info((new StringBuilder()).append("control_uri: ").append(cs.getControlURI()).toString());
_L4:
        return null;
        JSONException jsonexception;
        jsonexception;
_L6:
        logger.warn((new StringBuilder()).append("received unexpected event:").append(s).toString());
        if(true) goto _L4; else goto _L3
_L3:
        if(s.charAt(2) == 'd') goto _L1; else goto _L5
_L5:
        if(s.charAt(12) == '"')
            forUser.set(Long.valueOf(Long.parseLong(s.substring(13, i - 1))));
        else
            forUser.set(Long.valueOf(Long.parseLong(s.substring(12, i))));
        return s.substring(i + 11, s.length() - 1);
        jsonexception;
          goto _L6
    }

    protected static final RawStreamListener EMPTY[] = new RawStreamListener[0];
    private static final ThreadLocal forUser = new ThreadLocal() {

        protected Long initialValue()
        {
            return Long.valueOf(0L);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    private final StreamController cs;

}
