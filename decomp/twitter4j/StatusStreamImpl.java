// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            StatusStreamBase, RawStreamListener, TwitterException, StatusListener, 
//            JSONException, JSONObject, StatusDeletionNoticeImpl, UserStreamListener, 
//            ParseUtil, StreamListener, StallWarning, Dispatcher, 
//            HttpResponse

class StatusStreamImpl extends StatusStreamBase
{

    StatusStreamImpl(Dispatcher dispatcher, InputStream inputstream, Configuration configuration)
        throws IOException
    {
        super(dispatcher, inputstream, configuration);
    }

    StatusStreamImpl(Dispatcher dispatcher, HttpResponse httpresponse, Configuration configuration)
        throws IOException
    {
        super(dispatcher, httpresponse, configuration);
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

    protected void onClose()
    {
    }

    protected void onDelete(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        int i = 0;
        while(i < j) 
        {
            StreamListener streamlistener = astreamlistener[i];
            JSONObject jsonobject1 = jsonobject.getJSONObject("delete");
            if(jsonobject1.has("status"))
            {
                ((StatusListener)streamlistener).onDeletionNotice(new StatusDeletionNoticeImpl(jsonobject1.getJSONObject("status")));
            } else
            {
                jsonobject1 = jsonobject1.getJSONObject("direct_message");
                ((UserStreamListener)streamlistener).onDeletionNotice(ParseUtil.getLong("id", jsonobject1), ParseUtil.getLong("user_id", jsonobject1));
            }
            i++;
        }
    }

    public void onException(Exception exception, StreamListener astreamlistener[])
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            astreamlistener[i].onException(exception);

    }

    protected void onLimit(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((StatusListener)astreamlistener[i]).onTrackLimitationNotice(ParseUtil.getInt("track", jsonobject.getJSONObject("limit")));

    }

    protected void onMessage(String s, RawStreamListener arawstreamlistener[])
        throws TwitterException
    {
        int j = arawstreamlistener.length;
        for(int i = 0; i < j; i++)
            arawstreamlistener[i].onMessage(s);

    }

    protected void onScrubGeo(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        jsonobject = jsonobject.getJSONObject("scrub_geo");
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((StatusListener)astreamlistener[i]).onScrubGeo(ParseUtil.getLong("user_id", jsonobject), ParseUtil.getLong("up_to_status_id", jsonobject));

    }

    protected void onStallWarning(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException, JSONException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((StatusListener)astreamlistener[i]).onStallWarning(new StallWarning(jsonobject));

    }

    protected void onStatus(JSONObject jsonobject, StreamListener astreamlistener[])
        throws TwitterException
    {
        int j = astreamlistener.length;
        for(int i = 0; i < j; i++)
            ((StatusListener)astreamlistener[i]).onStatus(asStatus(jsonobject));

    }

    protected String parseLine(String s)
    {
        line = s;
        return s;
    }

    static final RawStreamListener EMPTY[] = new RawStreamListener[0];
    String line;

}
