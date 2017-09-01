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
//            TwitterStreamImpl, TwitterFactory, TwitterStream

public final class TwitterStreamFactory
    implements Serializable
{

    public TwitterStreamFactory()
    {
        this(ConfigurationContext.getInstance());
    }

    public TwitterStreamFactory(String s)
    {
        this(ConfigurationContext.getInstance(s));
    }

    public TwitterStreamFactory(Configuration configuration)
    {
        conf = configuration;
    }

    private TwitterStream getInstance(Configuration configuration, Authorization authorization)
    {
        return new TwitterStreamImpl(configuration, authorization);
    }

    public static TwitterStream getSingleton()
    {
        return SINGLETON;
    }

    public TwitterStream getInstance()
    {
        return getInstance(AuthorizationFactory.getInstance(conf));
    }

    public TwitterStream getInstance(AccessToken accesstoken)
    {
        String s = conf.getOAuthConsumerKey();
        String s1 = conf.getOAuthConsumerSecret();
        if(s == null && s1 == null)
        {
            throw new IllegalStateException("Consumer key and Consumer secret not supplied.");
        } else
        {
            OAuthAuthorization oauthauthorization = new OAuthAuthorization(conf);
            oauthauthorization.setOAuthAccessToken(accesstoken);
            return getInstance(conf, ((Authorization) (oauthauthorization)));
        }
    }

    public TwitterStream getInstance(Authorization authorization)
    {
        return getInstance(conf, authorization);
    }

    private static final TwitterStream SINGLETON;
    private static final long serialVersionUID = 0xc9713487L;
    private final Configuration conf;

    static 
    {
        SINGLETON = new TwitterStreamImpl(ConfigurationContext.getInstance(), TwitterFactory.DEFAULT_AUTHORIZATION);
    }
}
