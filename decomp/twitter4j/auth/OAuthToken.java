// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;

import java.io.Serializable;
import javax.crypto.spec.SecretKeySpec;
import twitter4j.HttpResponse;
import twitter4j.TwitterException;

abstract class OAuthToken
    implements Serializable
{

    OAuthToken(String s)
    {
        responseStr = null;
        responseStr = s.split("&");
        tokenSecret = getParameter("oauth_token_secret");
        token = getParameter("oauth_token");
    }

    public OAuthToken(String s, String s1)
    {
        responseStr = null;
        if(s == null)
            throw new IllegalArgumentException("Token can't be null");
        if(s1 == null)
        {
            throw new IllegalArgumentException("TokenSecret can't be null");
        } else
        {
            token = s;
            tokenSecret = s1;
            return;
        }
    }

    OAuthToken(HttpResponse httpresponse)
        throws TwitterException
    {
        this(httpresponse.asString());
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(!(obj instanceof OAuthToken))
                return false;
            obj = (OAuthToken)obj;
            if(!token.equals(((OAuthToken) (obj)).token))
                return false;
            if(!tokenSecret.equals(((OAuthToken) (obj)).tokenSecret))
                return false;
        }
        return true;
    }

    public String getParameter(String s)
    {
        Object obj = null;
        String as[] = responseStr;
        int j = as.length;
        int i = 0;
        do
        {
label0:
            {
                String s1 = obj;
                if(i < j)
                {
                    s1 = as[i];
                    if(!s1.startsWith((new StringBuilder()).append(s).append('=').toString()))
                        break label0;
                    s1 = s1.split("=")[1].trim();
                }
                return s1;
            }
            i++;
        } while(true);
    }

    SecretKeySpec getSecretKeySpec()
    {
        return secretKeySpec;
    }

    public String getToken()
    {
        return token;
    }

    public String getTokenSecret()
    {
        return tokenSecret;
    }

    public int hashCode()
    {
        return token.hashCode() * 31 + tokenSecret.hashCode();
    }

    void setSecretKeySpec(SecretKeySpec secretkeyspec)
    {
        secretKeySpec = secretkeyspec;
    }

    public String toString()
    {
        return (new StringBuilder()).append("OAuthToken{token='").append(token).append('\'').append(", tokenSecret='").append(tokenSecret).append('\'').append(", secretKeySpec=").append(secretKeySpec).append('}').toString();
    }

    private static final long serialVersionUID = 0x22220bc8L;
    private String responseStr[];
    private transient SecretKeySpec secretKeySpec;
    private final String token;
    private final String tokenSecret;
}
