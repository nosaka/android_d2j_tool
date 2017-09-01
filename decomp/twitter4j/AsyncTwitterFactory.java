// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

// Referenced classes of package twitter4j:
//            AsyncTwitterImpl, TwitterFactory, Twitter, AsyncTwitter

public final class AsyncTwitterFactory
    implements Serializable
{

    public AsyncTwitterFactory()
    {
        this(ConfigurationContext.getInstance());
    }

    public AsyncTwitterFactory(String s)
    {
        conf = ConfigurationContext.getInstance(s);
    }

    public AsyncTwitterFactory(Configuration configuration)
    {
        if(configuration == null)
        {
            throw new NullPointerException("configuration cannot be null");
        } else
        {
            conf = configuration;
            return;
        }
    }

    public static AsyncTwitter getSingleton()
    {
        return SINGLETON;
    }

    public AsyncTwitter getInstance()
    {
        return getInstance(AuthorizationFactory.getInstance(conf));
    }

    public AsyncTwitter getInstance(Twitter twitter)
    {
        return new AsyncTwitterImpl(twitter.getConfiguration(), twitter.getAuthorization());
    }

    public AsyncTwitter getInstance(AccessToken accesstoken)
    {
        String s = conf.getOAuthConsumerKey();
        String s1 = conf.getOAuthConsumerSecret();
        if(s == null && s1 == null)
        {
            throw new IllegalStateException("Consumer key and Consumer secret not supplied.");
        } else
        {
            OAuthAuthorization oauthauthorization = new OAuthAuthorization(conf);
            oauthauthorization.setOAuthConsumer(s, s1);
            oauthauthorization.setOAuthAccessToken(accesstoken);
            return new AsyncTwitterImpl(conf, oauthauthorization);
        }
    }

    public AsyncTwitter getInstance(Authorization authorization)
    {
        return new AsyncTwitterImpl(conf, authorization);
    }

    private static final AsyncTwitter SINGLETON;
    private static final long serialVersionUID = 0x1afbbedeL;
    private final Configuration conf;

    static 
    {
        SINGLETON = new AsyncTwitterImpl(ConfigurationContext.getInstance(), TwitterFactory.DEFAULT_AUTHORIZATION);
    }
}
