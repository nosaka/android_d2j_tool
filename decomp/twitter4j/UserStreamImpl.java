// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            StatusStreamImpl, UserStream, TwitterException, StreamListener, 
//            UserStreamListener, JSONException, Logger, DirectMessageJSONImpl, 
//            Dispatcher, HttpResponse, JSONObject

final class UserStreamImpl extends StatusStreamImpl
    implements UserStream
{

    UserStreamImpl(Dispatcher dispatcher, InputStream inputstream, Configuration configuration)
        throws IOException
    {
        super(dispatcher, inputstream, configuration);
    }

    UserStreamImpl(Dispatcher dispatcher, HttpResponse httpresponse, Configuration configuration)
        throws IOException
    {
        super(dispatcher, httpresponse, configuration);
    }

    public void next(UserStreamListener userstreamlistener)
        throws TwitterException
    {
        RawStreamListener arawstreamlistener[] = EMPTY;
        handleNextElement(new StreamListener[] {
            userstreamlistener
        }, arawstreamlistener);
    }

    protected void onBlock(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onBlock(asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onDirectMessage(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        jsonobject = asDirectMessage(jsonobject);
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onDirectMessage(jsonobject);

    }

    protected void onFavorite(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onFavorite(asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    void onFavoritedRetweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onFavoritedRetweet(asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    protected void onFollow(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onFollow(asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onFriends(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        jsonobject = asFriendList(jsonobject);
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onFriendList(jsonobject);

    }

    protected void onQuotedTweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onQuotedTweet(asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    void onRetweetedRetweet(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onRetweetedRetweet(asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    protected void onScrubGeo(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException
    {
        logger.info((new StringBuilder()).append("Geo-tagging deletion notice (not implemented yet): ").append(line).toString());
    }

    protected void onSender(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onDirectMessage(new DirectMessageJSONImpl(jsonobject));

    }

    protected void onUnblock(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUnblock(asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onUnfavorite(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUnfavorite(asUser(jsonobject), asUser(jsonobject1), asStatus(jsonobject2));

    }

    protected void onUnfollow(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUnfollow(asUser(jsonobject), asUser(jsonobject1));

    }

    protected void onUserDeletion(long l, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserDeletion(l);

    }

    protected void onUserListCreation(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserListCreation(asUser(jsonobject), asUserList(jsonobject1));

    }

    protected void onUserListDestroyed(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserListDeletion(asUser(jsonobject), asUserList(jsonobject1));

    }

    protected void onUserListMemberAddition(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserListMemberAddition(asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListMemberDeletion(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserListMemberDeletion(asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListSubscription(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserListSubscription(asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListUnsubscription(JSONObject jsonobject, JSONObject jsonobject1, JSONObject jsonobject2, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserListUnsubscription(asUser(jsonobject), asUser(jsonobject1), asUserList(jsonobject2));

    }

    protected void onUserListUpdated(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserListUpdate(asUser(jsonobject), asUserList(jsonobject1));

    }

    protected void onUserSuspension(long l, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserSuspension(l);

    }

    protected void onUserUpdate(JSONObject jsonobject, JSONObject jsonobject1, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((UserStreamListener)astreamlistener[i]).onUserProfileUpdate(asUser(jsonobject));

    }
}
