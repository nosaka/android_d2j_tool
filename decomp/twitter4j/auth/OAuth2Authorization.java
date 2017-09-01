// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import twitter4j.*;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j.auth:
//            Authorization, OAuth2Support, OAuth2Token

public class OAuth2Authorization
    implements Authorization, Serializable, OAuth2Support
{

    public OAuth2Authorization(Configuration configuration)
    {
        conf = configuration;
        setOAuthConsumer(configuration.getOAuthConsumerKey(), configuration.getOAuthConsumerSecret());
        http = HttpClientFactory.getInstance(configuration.getHttpClientConfiguration());
    }

    public boolean equals(Object obj)
    {
        if(obj != null && (obj instanceof OAuth2Authorization)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        obj = (OAuth2Authorization)obj;
        if(consumerKey == null) goto _L4; else goto _L3
_L3:
        if(!consumerKey.equals(((OAuth2Authorization) (obj)).consumerKey)) goto _L1; else goto _L5
_L5:
        if(consumerSecret == null) goto _L7; else goto _L6
_L6:
        if(!consumerSecret.equals(((OAuth2Authorization) (obj)).consumerSecret)) goto _L1; else goto _L8
_L8:
        if(token == null)
            break MISSING_BLOCK_LABEL_101;
        if(!token.equals(((OAuth2Authorization) (obj)).token)) goto _L1; else goto _L9
_L9:
        return true;
_L4:
        if(((OAuth2Authorization) (obj)).consumerKey != null)
            return false;
          goto _L5
_L7:
        if(((OAuth2Authorization) (obj)).consumerSecret != null)
            return false;
          goto _L8
        if(((OAuth2Authorization) (obj)).token != null)
            return false;
          goto _L9
    }

    public String getAuthorizationHeader(HttpRequest httprequest)
    {
        if(token != null) goto _L2; else goto _L1
_L1:
        httprequest = "";
        String s = (new StringBuilder()).append(URLEncoder.encode(consumerKey, "UTF-8")).append(":").append(URLEncoder.encode(consumerSecret, "UTF-8")).toString();
        httprequest = s;
_L4:
        return (new StringBuilder()).append("Basic ").append(BASE64Encoder.encode(httprequest.getBytes())).toString();
_L2:
        return token.generateAuthorizationHeader();
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public OAuth2Token getOAuth2Token()
        throws TwitterException
    {
        if(token != null)
            throw new IllegalStateException("OAuth 2 Bearer Token is already available.");
        int i;
        HttpParameter ahttpparameter[];
        HttpResponse httpresponse;
        if(conf.getOAuth2Scope() == null)
            i = 1;
        else
            i = 2;
        ahttpparameter = new HttpParameter[i];
        ahttpparameter[0] = new HttpParameter("grant_type", "client_credentials");
        if(conf.getOAuth2Scope() != null)
            ahttpparameter[1] = new HttpParameter("scope", conf.getOAuth2Scope());
        httpresponse = http.post(conf.getOAuth2TokenURL(), ahttpparameter, this, null);
        if(httpresponse.getStatusCode() != 200)
        {
            throw new TwitterException("Obtaining OAuth 2 Bearer Token failed.", httpresponse);
        } else
        {
            token = new OAuth2Token(httpresponse);
            return token;
        }
    }

    public int hashCode()
    {
        int k = 0;
        int i;
        int j;
        if(consumerKey != null)
            i = consumerKey.hashCode();
        else
            i = 0;
        if(consumerSecret != null)
            j = consumerSecret.hashCode();
        else
            j = 0;
        if(token != null)
            k = token.hashCode();
        return (i * 31 + j) * 31 + k;
    }

    public void invalidateOAuth2Token()
        throws TwitterException
    {
        OAuth2Token oauth2token;
        Object obj;
        if(token == null)
            throw new IllegalStateException("OAuth 2 Bearer Token is not available.");
        obj = new HttpParameter("access_token", token.getAccessToken());
        oauth2token = token;
        token = null;
        obj = http.post(conf.getOAuth2InvalidateTokenURL(), new HttpParameter[] {
            obj
        }, this, null);
        if(((HttpResponse) (obj)).getStatusCode() != 200)
            throw new TwitterException("Invalidating OAuth 2 Bearer Token failed.", ((HttpResponse) (obj)));
        break MISSING_BLOCK_LABEL_106;
        Exception exception;
        exception;
        if(true)
            token = oauth2token;
        throw exception;
        if(false)
            token = oauth2token;
        return;
    }

    public boolean isEnabled()
    {
        return token != null;
    }

    public void setOAuth2Token(OAuth2Token oauth2token)
    {
        token = oauth2token;
    }

    public void setOAuthConsumer(String s, String s1)
    {
        if(s == null)
            s = "";
        consumerKey = s;
        if(s1 == null)
            s1 = "";
        consumerSecret = s1;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("OAuth2Authorization{consumerKey='").append(consumerKey).append('\'').append(", consumerSecret='******************************************', token=");
        String s;
        if(token == null)
            s = "null";
        else
            s = token.toString();
        return stringbuilder.append(s).append('}').toString();
    }

    private static final long serialVersionUID = 0x94e99869L;
    private final Configuration conf;
    private String consumerKey;
    private String consumerSecret;
    private final HttpClient http;
    private OAuth2Token token;
}
