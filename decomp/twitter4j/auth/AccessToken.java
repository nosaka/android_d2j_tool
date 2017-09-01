// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;

import java.io.Serializable;
import twitter4j.HttpResponse;
import twitter4j.TwitterException;

// Referenced classes of package twitter4j.auth:
//            OAuthToken

public class AccessToken extends OAuthToken
    implements Serializable
{

    AccessToken(String s)
    {
        super(s);
        userId = -1L;
        screenName = getParameter("screen_name");
        s = getParameter("user_id");
        if(s != null)
            userId = Long.parseLong(s);
    }

    public AccessToken(String s, String s1)
    {
        super(s, s1);
        userId = -1L;
        int i = s.indexOf("-");
        if(i == -1)
            break MISSING_BLOCK_LABEL_40;
        s = s.substring(0, i);
        userId = Long.parseLong(s);
        return;
        s;
    }

    public AccessToken(String s, String s1, long l)
    {
        super(s, s1);
        userId = -1L;
        userId = l;
    }

    AccessToken(HttpResponse httpresponse)
        throws TwitterException
    {
        this(httpresponse.asString());
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            if(!super.equals(obj))
                return false;
            obj = (AccessToken)obj;
            if(userId != ((AccessToken) (obj)).userId)
                return false;
            if(screenName == null ? ((AccessToken) (obj)).screenName != null : !screenName.equals(((AccessToken) (obj)).screenName))
                return false;
        }
        return true;
    }

    public volatile String getParameter(String s)
    {
        return super.getParameter(s);
    }

    public String getScreenName()
    {
        return screenName;
    }

    public volatile String getToken()
    {
        return super.getToken();
    }

    public volatile String getTokenSecret()
    {
        return super.getTokenSecret();
    }

    public long getUserId()
    {
        return userId;
    }

    public int hashCode()
    {
        int j = super.hashCode();
        int i;
        if(screenName != null)
            i = screenName.hashCode();
        else
            i = 0;
        return (j * 31 + i) * 31 + (int)(userId ^ userId >>> 32);
    }

    public String toString()
    {
        return (new StringBuilder()).append("AccessToken{screenName='").append(screenName).append('\'').append(", userId=").append(userId).append('}').toString();
    }

    private static final long serialVersionUID = 0x408bacb4L;
    private String screenName;
    private long userId;
}
