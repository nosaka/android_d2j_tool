// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.*;
import java.util.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.auth.BasicAuthorization;
import twitter4j.auth.NullAuthorization;
import twitter4j.auth.OAuth2Authorization;
import twitter4j.auth.OAuth2Support;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.OAuthSupport;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.util.function.Consumer;

// Referenced classes of package twitter4j:
//            TwitterBase, HttpResponseListener, HttpClientFactory, RateLimitStatusListener, 
//            TwitterException, UserJSONImpl, HttpClient, User, 
//            XAuthAuthorization, HttpResponseEvent, RateLimitStatusEvent, JSONImplFactory, 
//            HttpResponse, ObjectFactory, HttpParameter

abstract class TwitterBaseImpl
    implements TwitterBase, Serializable, OAuthSupport, OAuth2Support, HttpResponseListener
{

    TwitterBaseImpl(Configuration configuration, Authorization authorization)
    {
        screenName = null;
        id = 0L;
        rateLimitStatusListeners = new ArrayList(0);
        conf = configuration;
        auth = authorization;
        init();
    }

    private OAuthSupport getOAuth()
    {
        if(!(auth instanceof OAuthSupport))
            throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
        else
            return (OAuthSupport)auth;
    }

    private OAuth2Support getOAuth2()
    {
        if(!(auth instanceof OAuth2Support))
            throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
        else
            return (OAuth2Support)auth;
    }

    private void init()
    {
        if(auth == null)
        {
            String s = conf.getOAuthConsumerKey();
            String s1 = conf.getOAuthConsumerSecret();
            if(s != null && s1 != null)
            {
                if(conf.isApplicationOnlyAuthEnabled())
                {
                    OAuth2Authorization oauth2authorization = new OAuth2Authorization(conf);
                    String s2 = conf.getOAuth2TokenType();
                    String s4 = conf.getOAuth2AccessToken();
                    if(s2 != null && s4 != null)
                        oauth2authorization.setOAuth2Token(new OAuth2Token(s2, s4));
                    auth = oauth2authorization;
                } else
                {
                    OAuthAuthorization oauthauthorization = new OAuthAuthorization(conf);
                    String s3 = conf.getOAuthAccessToken();
                    String s5 = conf.getOAuthAccessTokenSecret();
                    if(s3 != null && s5 != null)
                        oauthauthorization.setOAuthAccessToken(new AccessToken(s3, s5));
                    auth = oauthauthorization;
                }
            } else
            {
                auth = NullAuthorization.getInstance();
            }
        }
        http = HttpClientFactory.getInstance(conf.getHttpClientConfiguration());
        setFactory();
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.readFields();
        conf = (Configuration)objectinputstream.readObject();
        auth = (Authorization)objectinputstream.readObject();
        rateLimitStatusListeners = (List)objectinputstream.readObject();
        http = HttpClientFactory.getInstance(conf.getHttpClientConfiguration());
        setFactory();
    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.putFields();
        objectoutputstream.writeFields();
        objectoutputstream.writeObject(conf);
        objectoutputstream.writeObject(auth);
        ArrayList arraylist = new ArrayList(0);
        Iterator iterator = rateLimitStatusListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            RateLimitStatusListener ratelimitstatuslistener = (RateLimitStatusListener)iterator.next();
            if(ratelimitstatuslistener instanceof Serializable)
                arraylist.add(ratelimitstatuslistener);
        } while(true);
        objectoutputstream.writeObject(arraylist);
    }

    public void addRateLimitStatusListener(RateLimitStatusListener ratelimitstatuslistener)
    {
        rateLimitStatusListeners.add(ratelimitstatuslistener);
    }

    final void ensureAuthorizationEnabled()
    {
        if(!auth.isEnabled())
            throw new IllegalStateException("Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for details. See and register at http://apps.twitter.com/");
        else
            return;
    }

    final void ensureOAuthEnabled()
    {
        if(!(auth instanceof OAuthAuthorization))
            throw new IllegalStateException("OAuth required. Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for details. See and register at http://apps.twitter.com/");
        else
            return;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(!(obj instanceof TwitterBaseImpl))
                return false;
            obj = (TwitterBaseImpl)obj;
            if(auth == null ? ((TwitterBaseImpl) (obj)).auth != null : !auth.equals(((TwitterBaseImpl) (obj)).auth))
                return false;
            if(!conf.equals(((TwitterBaseImpl) (obj)).conf))
                return false;
            if(http == null ? ((TwitterBaseImpl) (obj)).http != null : !http.equals(((TwitterBaseImpl) (obj)).http))
                return false;
            if(!rateLimitStatusListeners.equals(((TwitterBaseImpl) (obj)).rateLimitStatusListeners))
                return false;
        }
        return true;
    }

    User fillInIDAndScreenName()
        throws TwitterException
    {
        return fillInIDAndScreenName(null);
    }

    User fillInIDAndScreenName(HttpParameter ahttpparameter[])
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        ahttpparameter = new UserJSONImpl(http.get((new StringBuilder()).append(conf.getRestBaseURL()).append("account/verify_credentials.json").toString(), ahttpparameter, auth, this), conf);
        screenName = ahttpparameter.getScreenName();
        id = ahttpparameter.getId();
        return ahttpparameter;
    }

    public final Authorization getAuthorization()
    {
        return auth;
    }

    public Configuration getConfiguration()
    {
        return conf;
    }

    public long getId()
        throws TwitterException, IllegalStateException
    {
        if(!auth.isEnabled())
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
        if(0L == id)
            fillInIDAndScreenName();
        return id;
    }

    public OAuth2Token getOAuth2Token()
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        OAuth2Token oauth2token = getOAuth2().getOAuth2Token();
        this;
        JVM INSTR monitorexit ;
        return oauth2token;
        Exception exception;
        exception;
        throw exception;
    }

    public AccessToken getOAuthAccessToken()
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = getAuthorization();
        if(!(obj instanceof BasicAuthorization)) goto _L2; else goto _L1
_L1:
        Authorization authorization;
        obj = (BasicAuthorization)obj;
        authorization = AuthorizationFactory.getInstance(conf);
        if(!(authorization instanceof OAuthAuthorization)) goto _L4; else goto _L3
_L3:
        auth = authorization;
        obj = ((OAuthAuthorization)authorization).getOAuthAccessToken(((BasicAuthorization) (obj)).getUserId(), ((BasicAuthorization) (obj)).getPassword());
_L5:
        screenName = ((AccessToken) (obj)).getScreenName();
        id = ((AccessToken) (obj)).getUserId();
        this;
        JVM INSTR monitorexit ;
        return ((AccessToken) (obj));
_L4:
        throw new IllegalStateException("consumer key / secret combination not supplied.");
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
label0:
        {
            if(!(obj instanceof XAuthAuthorization))
                break label0;
            obj = (XAuthAuthorization)obj;
            auth = ((Authorization) (obj));
            OAuthAuthorization oauthauthorization = new OAuthAuthorization(conf);
            oauthauthorization.setOAuthConsumer(((XAuthAuthorization) (obj)).getConsumerKey(), ((XAuthAuthorization) (obj)).getConsumerSecret());
            obj = oauthauthorization.getOAuthAccessToken(((XAuthAuthorization) (obj)).getUserId(), ((XAuthAuthorization) (obj)).getPassword());
        }
          goto _L5
        obj = getOAuth().getOAuthAccessToken();
          goto _L5
    }

    public AccessToken getOAuthAccessToken(String s)
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        s = getOAuth().getOAuthAccessToken(s);
        screenName = s.getScreenName();
        this;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public AccessToken getOAuthAccessToken(String s, String s1)
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        s = getOAuth().getOAuthAccessToken(s, s1);
        this;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public AccessToken getOAuthAccessToken(RequestToken requesttoken)
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        requesttoken = getOAuth().getOAuthAccessToken(requesttoken);
        screenName = requesttoken.getScreenName();
        this;
        JVM INSTR monitorexit ;
        return requesttoken;
        requesttoken;
        throw requesttoken;
    }

    public AccessToken getOAuthAccessToken(RequestToken requesttoken, String s)
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        requesttoken = getOAuth().getOAuthAccessToken(requesttoken, s);
        this;
        JVM INSTR monitorexit ;
        return requesttoken;
        requesttoken;
        throw requesttoken;
    }

    public RequestToken getOAuthRequestToken()
        throws TwitterException
    {
        return getOAuthRequestToken(null);
    }

    public RequestToken getOAuthRequestToken(String s)
        throws TwitterException
    {
        return getOAuth().getOAuthRequestToken(s);
    }

    public RequestToken getOAuthRequestToken(String s, String s1)
        throws TwitterException
    {
        return getOAuth().getOAuthRequestToken(s, s1);
    }

    public RequestToken getOAuthRequestToken(String s, String s1, String s2)
        throws TwitterException
    {
        return getOAuth().getOAuthRequestToken(s, s1, s2);
    }

    public String getScreenName()
        throws TwitterException, IllegalStateException
    {
        if(!auth.isEnabled())
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
        if(screenName == null)
        {
            if(auth instanceof BasicAuthorization)
            {
                screenName = ((BasicAuthorization)auth).getUserId();
                if(screenName.contains("@"))
                    screenName = null;
            }
            if(screenName == null)
                fillInIDAndScreenName();
        }
        return screenName;
    }

    public int hashCode()
    {
        int j = 0;
        int k = conf.hashCode();
        int i;
        int l;
        if(http != null)
            i = http.hashCode();
        else
            i = 0;
        l = rateLimitStatusListeners.hashCode();
        if(auth != null)
            j = auth.hashCode();
        return ((k * 31 + i) * 31 + l) * 31 + j;
    }

    public void httpResponseReceived(HttpResponseEvent httpresponseevent)
    {
        if(rateLimitStatusListeners.size() != 0)
        {
            Object obj1 = httpresponseevent.getResponse();
            TwitterException twitterexception = httpresponseevent.getTwitterException();
            int i;
            Object obj;
            if(twitterexception != null)
            {
                obj = twitterexception.getRateLimitStatus();
                i = twitterexception.getStatusCode();
            } else
            {
                obj = JSONImplFactory.createRateLimitStatusFromResponseHeader(((HttpResponse) (obj1)));
                i = ((HttpResponse) (obj1)).getStatusCode();
            }
            if(obj != null)
            {
                httpresponseevent = new RateLimitStatusEvent(this, ((RateLimitStatus) (obj)), httpresponseevent.isAuthenticated());
                if(i == 420 || i == 503 || i == 429)
                {
                    for(obj = rateLimitStatusListeners.iterator(); ((Iterator) (obj)).hasNext(); ((RateLimitStatusListener) (obj1)).onRateLimitReached(httpresponseevent))
                    {
                        obj1 = (RateLimitStatusListener)((Iterator) (obj)).next();
                        ((RateLimitStatusListener) (obj1)).onRateLimitStatus(httpresponseevent);
                    }

                } else
                {
                    for(Iterator iterator = rateLimitStatusListeners.iterator(); iterator.hasNext(); ((RateLimitStatusListener)iterator.next()).onRateLimitStatus(httpresponseevent));
                }
            }
        }
    }

    public void invalidateOAuth2Token()
        throws TwitterException
    {
        this;
        JVM INSTR monitorenter ;
        getOAuth2().invalidateOAuth2Token();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onRateLimitReached(final Consumer action)
    {
        rateLimitStatusListeners.add(new RateLimitStatusListener() {

            public void onRateLimitReached(RateLimitStatusEvent ratelimitstatusevent)
            {
                action.accept(ratelimitstatusevent);
            }

            public void onRateLimitStatus(RateLimitStatusEvent ratelimitstatusevent)
            {
            }

            final TwitterBaseImpl this$0;
            final Consumer val$action;

            
            {
                this$0 = TwitterBaseImpl.this;
                action = consumer;
                super();
            }
        }
);
    }

    public void onRateLimitStatus(final Consumer action)
    {
        rateLimitStatusListeners.add(new RateLimitStatusListener() {

            public void onRateLimitReached(RateLimitStatusEvent ratelimitstatusevent)
            {
            }

            public void onRateLimitStatus(RateLimitStatusEvent ratelimitstatusevent)
            {
                action.accept(ratelimitstatusevent);
            }

            final TwitterBaseImpl this$0;
            final Consumer val$action;

            
            {
                this$0 = TwitterBaseImpl.this;
                action = consumer;
                super();
            }
        }
);
    }

    void setFactory()
    {
        factory = new JSONImplFactory(conf);
    }

    public void setOAuth2Token(OAuth2Token oauth2token)
    {
        getOAuth2().setOAuth2Token(oauth2token);
    }

    public void setOAuthAccessToken(AccessToken accesstoken)
    {
        this;
        JVM INSTR monitorenter ;
        getOAuth().setOAuthAccessToken(accesstoken);
        this;
        JVM INSTR monitorexit ;
        return;
        accesstoken;
        throw accesstoken;
    }

    public void setOAuthConsumer(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        if(s != null)
            break MISSING_BLOCK_LABEL_22;
        throw new NullPointerException("consumer key is null");
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        if(s1 != null)
            break MISSING_BLOCK_LABEL_37;
        throw new NullPointerException("consumer secret is null");
        if(!(auth instanceof NullAuthorization)) goto _L2; else goto _L1
_L1:
        if(!conf.isApplicationOnlyAuthEnabled()) goto _L4; else goto _L3
_L3:
        OAuth2Authorization oauth2authorization = new OAuth2Authorization(conf);
        oauth2authorization.setOAuthConsumer(s, s1);
        auth = oauth2authorization;
_L5:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        OAuthAuthorization oauthauthorization = new OAuthAuthorization(conf);
        oauthauthorization.setOAuthConsumer(s, s1);
        auth = oauthauthorization;
          goto _L5
_L2:
        if(!(auth instanceof BasicAuthorization))
            continue; /* Loop/switch isn't completed */
        XAuthAuthorization xauthauthorization = new XAuthAuthorization((BasicAuthorization)auth);
        xauthauthorization.setOAuthConsumer(s, s1);
        auth = xauthauthorization;
          goto _L5
        if(!(auth instanceof OAuthAuthorization) && !(auth instanceof OAuth2Authorization)) goto _L5; else goto _L6
_L6:
        throw new IllegalStateException("consumer key/secret pair already set.");
    }

    public String toString()
    {
        return (new StringBuilder()).append("TwitterBase{conf=").append(conf).append(", http=").append(http).append(", rateLimitStatusListeners=").append(rateLimitStatusListeners).append(", auth=").append(auth).append('}').toString();
    }

    private static final String WWW_DETAILS = "See http://twitter4j.org/en/configuration.html for details. See and register at http://apps.twitter.com/";
    private static final long serialVersionUID = 0x97d56116L;
    Authorization auth;
    Configuration conf;
    ObjectFactory factory;
    transient HttpClient http;
    private transient long id;
    private List rateLimitStatusListeners;
    private transient String screenName;
}
