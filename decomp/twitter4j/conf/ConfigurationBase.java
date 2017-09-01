// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.conf;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import twitter4j.HttpClientConfiguration;
import twitter4j.Logger;

// Referenced classes of package twitter4j.conf:
//            Configuration

class ConfigurationBase
    implements Configuration, Serializable
{
    class MyHttpClientConfiguration
        implements HttpClientConfiguration, Serializable
    {

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(obj == null || getClass() != obj.getClass())
                    return false;
                obj = (MyHttpClientConfiguration)obj;
                if(gzipEnabled != ((MyHttpClientConfiguration) (obj)).gzipEnabled)
                    return false;
                if(httpConnectionTimeout != ((MyHttpClientConfiguration) (obj)).httpConnectionTimeout)
                    return false;
                if(httpProxyPort != ((MyHttpClientConfiguration) (obj)).httpProxyPort)
                    return false;
                if(httpReadTimeout != ((MyHttpClientConfiguration) (obj)).httpReadTimeout)
                    return false;
                if(prettyDebug != ((MyHttpClientConfiguration) (obj)).prettyDebug)
                    return false;
                if(httpProxyHost == null ? ((MyHttpClientConfiguration) (obj)).httpProxyHost != null : !httpProxyHost.equals(((MyHttpClientConfiguration) (obj)).httpProxyHost))
                    return false;
                if(httpProxyPassword == null ? ((MyHttpClientConfiguration) (obj)).httpProxyPassword != null : !httpProxyPassword.equals(((MyHttpClientConfiguration) (obj)).httpProxyPassword))
                    return false;
                if(httpProxyUser == null ? ((MyHttpClientConfiguration) (obj)).httpProxyUser != null : !httpProxyUser.equals(((MyHttpClientConfiguration) (obj)).httpProxyUser))
                    return false;
            }
            return true;
        }

        public int getHttpConnectionTimeout()
        {
            return httpConnectionTimeout;
        }

        public String getHttpProxyHost()
        {
            return httpProxyHost;
        }

        public String getHttpProxyPassword()
        {
            return httpProxyPassword;
        }

        public int getHttpProxyPort()
        {
            return httpProxyPort;
        }

        public String getHttpProxyUser()
        {
            return httpProxyUser;
        }

        public int getHttpReadTimeout()
        {
            return httpReadTimeout;
        }

        public int getHttpRetryCount()
        {
            return httpRetryCount;
        }

        public int getHttpRetryIntervalSeconds()
        {
            return httpRetryIntervalSeconds;
        }

        public int hashCode()
        {
            int i1 = 1;
            int i;
            int j;
            int k;
            int l;
            int j1;
            int k1;
            int l1;
            if(httpProxyHost != null)
                i = httpProxyHost.hashCode();
            else
                i = 0;
            if(httpProxyUser != null)
                j = httpProxyUser.hashCode();
            else
                j = 0;
            if(httpProxyPassword != null)
                k = httpProxyPassword.hashCode();
            else
                k = 0;
            j1 = httpProxyPort;
            k1 = httpConnectionTimeout;
            l1 = httpReadTimeout;
            if(prettyDebug)
                l = 1;
            else
                l = 0;
            if(!gzipEnabled)
                i1 = 0;
            return ((((((i * 31 + j) * 31 + k) * 31 + j1) * 31 + k1) * 31 + l1) * 31 + l) * 31 + i1;
        }

        public boolean isGZIPEnabled()
        {
            return gzipEnabled;
        }

        public boolean isPrettyDebugEnabled()
        {
            return prettyDebug;
        }

        public String toString()
        {
            return (new StringBuilder()).append("MyHttpClientConfiguration{httpProxyHost='").append(httpProxyHost).append('\'').append(", httpProxyUser='").append(httpProxyUser).append('\'').append(", httpProxyPassword='").append(httpProxyPassword).append('\'').append(", httpProxyPort=").append(httpProxyPort).append(", httpConnectionTimeout=").append(httpConnectionTimeout).append(", httpReadTimeout=").append(httpReadTimeout).append(", prettyDebug=").append(prettyDebug).append(", gzipEnabled=").append(gzipEnabled).append('}').toString();
        }

        private static final long serialVersionUID = 0x1b80bc82L;
        private boolean gzipEnabled;
        private int httpConnectionTimeout;
        private String httpProxyHost;
        private String httpProxyPassword;
        private int httpProxyPort;
        private String httpProxyUser;
        private int httpReadTimeout;
        private boolean prettyDebug;
        final ConfigurationBase this$0;

        MyHttpClientConfiguration(String s, String s1, String s2, int i, int j, int k, 
                boolean flag, boolean flag1)
        {
            this$0 = ConfigurationBase.this;
            super();
            httpProxyHost = null;
            httpProxyUser = null;
            httpProxyPassword = null;
            httpProxyPort = -1;
            httpConnectionTimeout = 20000;
            httpReadTimeout = 0x1d4c0;
            prettyDebug = false;
            gzipEnabled = true;
            httpProxyHost = s;
            httpProxyUser = s1;
            httpProxyPassword = s2;
            httpProxyPort = i;
            httpConnectionTimeout = j;
            httpReadTimeout = k;
            prettyDebug = flag;
            gzipEnabled = flag1;
        }
    }


    protected ConfigurationBase()
    {
        debug = false;
        user = null;
        password = null;
        httpStreamingReadTimeout = 40000;
        httpRetryCount = 0;
        httpRetryIntervalSeconds = 5;
        oAuthConsumerKey = null;
        oAuthConsumerSecret = null;
        oAuthAccessToken = null;
        oAuthAccessTokenSecret = null;
        oAuthRequestTokenURL = "https://api.twitter.com/oauth/request_token";
        oAuthAuthorizationURL = "https://api.twitter.com/oauth/authorize";
        oAuthAccessTokenURL = "https://api.twitter.com/oauth/access_token";
        oAuthAuthenticationURL = "https://api.twitter.com/oauth/authenticate";
        oAuth2TokenURL = "https://api.twitter.com/oauth2/token";
        oAuth2InvalidateTokenURL = "https://api.twitter.com/oauth2/invalidate_token";
        restBaseURL = "https://api.twitter.com/1.1/";
        streamBaseURL = "https://stream.twitter.com/1.1/";
        userStreamBaseURL = "https://userstream.twitter.com/1.1/";
        siteStreamBaseURL = "https://sitestream.twitter.com/1.1/";
        uploadBaseURL = "https://upload.twitter.com/1.1/";
        dispatcherImpl = "twitter4j.DispatcherImpl";
        asyncNumThreads = 1;
        loggerFactory = null;
        contributingTo = -1L;
        includeMyRetweetEnabled = true;
        includeEntitiesEnabled = true;
        trimUserEnabled = false;
        includeExtAltTextEnabled = true;
        tweetModeExtended = false;
        includeEmailEnabled = false;
        jsonStoreEnabled = false;
        mbeanEnabled = false;
        userStreamRepliesAllEnabled = false;
        userStreamWithFollowingsEnabled = true;
        stallWarningsEnabled = true;
        applicationOnlyAuthEnabled = false;
        mediaProvider = "TWITTER";
        mediaProviderAPIKey = null;
        mediaProviderParameters = null;
        daemonEnabled = true;
        streamThreadName = "";
        httpConf = new MyHttpClientConfiguration(null, null, null, -1, 20000, 0x1d4c0, false, true);
    }

    private static void cacheInstance(ConfigurationBase configurationbase)
    {
        if(!instances.contains(configurationbase))
            instances.add(configurationbase);
    }

    static String fixURL(boolean flag, String s)
    {
        if(s == null)
            return null;
        int i = s.indexOf("://");
        if(-1 == i)
            throw new IllegalArgumentException("url should contain '://'");
        s = s.substring(i + 3);
        if(flag)
            return (new StringBuilder()).append("https://").append(s).toString();
        else
            return (new StringBuilder()).append("http://").append(s).toString();
    }

    private static ConfigurationBase getInstance(ConfigurationBase configurationbase)
    {
        int i = instances.indexOf(configurationbase);
        if(i == -1)
        {
            instances.add(configurationbase);
            return configurationbase;
        } else
        {
            return (ConfigurationBase)instances.get(i);
        }
    }

    protected void cacheInstance()
    {
        cacheInstance(this);
    }

    public void dumpConfiguration()
    {
        Logger logger = Logger.getLogger(twitter4j/conf/ConfigurationBase);
        if(!debug) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        Field afield[];
        afield = twitter4j/conf/ConfigurationBase.getDeclaredFields();
        j = afield.length;
        i = 0;
_L3:
        Field field;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        field = afield[i];
        String s1;
        Object obj;
        obj = field.get(this);
        s1 = String.valueOf(obj);
        String s;
        s = s1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_93;
        s = s1;
        if(field.getName().matches("oAuthConsumerSecret|oAuthAccessTokenSecret|password"))
            s = String.valueOf(obj).replaceAll(".", "*");
        logger.debug((new StringBuilder()).append(field.getName()).append(": ").append(s).toString());
_L4:
        i++;
        if(true) goto _L3; else goto _L2
_L2:
        return;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
          goto _L4
    }

    public boolean equals(Object obj)
    {
        boolean flag2;
        boolean flag3;
        flag2 = true;
        flag3 = false;
        if(this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag3;
        if(obj == null) goto _L4; else goto _L3
_L3:
        flag = flag3;
        if(getClass() != obj.getClass()) goto _L4; else goto _L5
_L5:
        obj = (ConfigurationBase)obj;
        flag = flag3;
        if(debug != ((ConfigurationBase) (obj)).debug) goto _L4; else goto _L6
_L6:
        flag = flag3;
        if(httpStreamingReadTimeout != ((ConfigurationBase) (obj)).httpStreamingReadTimeout) goto _L4; else goto _L7
_L7:
        flag = flag3;
        if(httpRetryCount != ((ConfigurationBase) (obj)).httpRetryCount) goto _L4; else goto _L8
_L8:
        flag = flag3;
        if(httpRetryIntervalSeconds != ((ConfigurationBase) (obj)).httpRetryIntervalSeconds) goto _L4; else goto _L9
_L9:
        flag = flag3;
        if(asyncNumThreads != ((ConfigurationBase) (obj)).asyncNumThreads) goto _L4; else goto _L10
_L10:
        flag = flag3;
        if(contributingTo != ((ConfigurationBase) (obj)).contributingTo) goto _L4; else goto _L11
_L11:
        flag = flag3;
        if(includeMyRetweetEnabled != ((ConfigurationBase) (obj)).includeMyRetweetEnabled) goto _L4; else goto _L12
_L12:
        flag = flag3;
        if(includeEntitiesEnabled != ((ConfigurationBase) (obj)).includeEntitiesEnabled) goto _L4; else goto _L13
_L13:
        flag = flag3;
        if(trimUserEnabled != ((ConfigurationBase) (obj)).trimUserEnabled) goto _L4; else goto _L14
_L14:
        flag = flag3;
        if(includeExtAltTextEnabled != ((ConfigurationBase) (obj)).includeExtAltTextEnabled) goto _L4; else goto _L15
_L15:
        flag = flag3;
        if(tweetModeExtended != ((ConfigurationBase) (obj)).tweetModeExtended) goto _L4; else goto _L16
_L16:
        flag = flag3;
        if(includeEmailEnabled != ((ConfigurationBase) (obj)).includeEmailEnabled) goto _L4; else goto _L17
_L17:
        flag = flag3;
        if(jsonStoreEnabled != ((ConfigurationBase) (obj)).jsonStoreEnabled) goto _L4; else goto _L18
_L18:
        flag = flag3;
        if(mbeanEnabled != ((ConfigurationBase) (obj)).mbeanEnabled) goto _L4; else goto _L19
_L19:
        flag = flag3;
        if(userStreamRepliesAllEnabled != ((ConfigurationBase) (obj)).userStreamRepliesAllEnabled) goto _L4; else goto _L20
_L20:
        flag = flag3;
        if(userStreamWithFollowingsEnabled != ((ConfigurationBase) (obj)).userStreamWithFollowingsEnabled) goto _L4; else goto _L21
_L21:
        flag = flag3;
        if(stallWarningsEnabled != ((ConfigurationBase) (obj)).stallWarningsEnabled) goto _L4; else goto _L22
_L22:
        flag = flag3;
        if(applicationOnlyAuthEnabled != ((ConfigurationBase) (obj)).applicationOnlyAuthEnabled) goto _L4; else goto _L23
_L23:
        flag = flag3;
        if(daemonEnabled != ((ConfigurationBase) (obj)).daemonEnabled) goto _L4; else goto _L24
_L24:
        if(user == null) goto _L26; else goto _L25
_L25:
        flag = flag3;
        if(!user.equals(((ConfigurationBase) (obj)).user)) goto _L4; else goto _L27
_L27:
        if(password == null) goto _L29; else goto _L28
_L28:
        flag = flag3;
        if(!password.equals(((ConfigurationBase) (obj)).password)) goto _L4; else goto _L30
_L30:
        if(httpConf == null) goto _L32; else goto _L31
_L31:
        flag = flag3;
        if(!httpConf.equals(((ConfigurationBase) (obj)).httpConf)) goto _L4; else goto _L33
_L33:
        if(oAuthConsumerKey == null) goto _L35; else goto _L34
_L34:
        flag = flag3;
        if(!oAuthConsumerKey.equals(((ConfigurationBase) (obj)).oAuthConsumerKey)) goto _L4; else goto _L36
_L36:
        if(oAuthConsumerSecret == null) goto _L38; else goto _L37
_L37:
        flag = flag3;
        if(!oAuthConsumerSecret.equals(((ConfigurationBase) (obj)).oAuthConsumerSecret)) goto _L4; else goto _L39
_L39:
        if(oAuthAccessToken == null) goto _L41; else goto _L40
_L40:
        flag = flag3;
        if(!oAuthAccessToken.equals(((ConfigurationBase) (obj)).oAuthAccessToken)) goto _L4; else goto _L42
_L42:
        if(oAuthAccessTokenSecret == null) goto _L44; else goto _L43
_L43:
        flag = flag3;
        if(!oAuthAccessTokenSecret.equals(((ConfigurationBase) (obj)).oAuthAccessTokenSecret)) goto _L4; else goto _L45
_L45:
        if(oAuth2TokenType == null) goto _L47; else goto _L46
_L46:
        flag = flag3;
        if(!oAuth2TokenType.equals(((ConfigurationBase) (obj)).oAuth2TokenType)) goto _L4; else goto _L48
_L48:
        if(oAuth2AccessToken == null) goto _L50; else goto _L49
_L49:
        flag = flag3;
        if(!oAuth2AccessToken.equals(((ConfigurationBase) (obj)).oAuth2AccessToken)) goto _L4; else goto _L51
_L51:
        if(oAuth2Scope == null) goto _L53; else goto _L52
_L52:
        flag = flag3;
        if(!oAuth2Scope.equals(((ConfigurationBase) (obj)).oAuth2Scope)) goto _L4; else goto _L54
_L54:
        if(oAuthRequestTokenURL == null) goto _L56; else goto _L55
_L55:
        flag = flag3;
        if(!oAuthRequestTokenURL.equals(((ConfigurationBase) (obj)).oAuthRequestTokenURL)) goto _L4; else goto _L57
_L57:
        if(oAuthAuthorizationURL == null) goto _L59; else goto _L58
_L58:
        flag = flag3;
        if(!oAuthAuthorizationURL.equals(((ConfigurationBase) (obj)).oAuthAuthorizationURL)) goto _L4; else goto _L60
_L60:
        if(oAuthAccessTokenURL == null) goto _L62; else goto _L61
_L61:
        flag = flag3;
        if(!oAuthAccessTokenURL.equals(((ConfigurationBase) (obj)).oAuthAccessTokenURL)) goto _L4; else goto _L63
_L63:
        if(oAuthAuthenticationURL == null) goto _L65; else goto _L64
_L64:
        flag = flag3;
        if(!oAuthAuthenticationURL.equals(((ConfigurationBase) (obj)).oAuthAuthenticationURL)) goto _L4; else goto _L66
_L66:
        if(oAuth2TokenURL == null) goto _L68; else goto _L67
_L67:
        flag = flag3;
        if(!oAuth2TokenURL.equals(((ConfigurationBase) (obj)).oAuth2TokenURL)) goto _L4; else goto _L69
_L69:
        if(oAuth2InvalidateTokenURL == null) goto _L71; else goto _L70
_L70:
        flag = flag3;
        if(!oAuth2InvalidateTokenURL.equals(((ConfigurationBase) (obj)).oAuth2InvalidateTokenURL)) goto _L4; else goto _L72
_L72:
        if(restBaseURL == null) goto _L74; else goto _L73
_L73:
        flag = flag3;
        if(!restBaseURL.equals(((ConfigurationBase) (obj)).restBaseURL)) goto _L4; else goto _L75
_L75:
        if(streamBaseURL == null) goto _L77; else goto _L76
_L76:
        flag = flag3;
        if(!streamBaseURL.equals(((ConfigurationBase) (obj)).streamBaseURL)) goto _L4; else goto _L78
_L78:
        if(userStreamBaseURL == null) goto _L80; else goto _L79
_L79:
        flag = flag3;
        if(!userStreamBaseURL.equals(((ConfigurationBase) (obj)).userStreamBaseURL)) goto _L4; else goto _L81
_L81:
        if(siteStreamBaseURL == null) goto _L83; else goto _L82
_L82:
        flag = flag3;
        if(!siteStreamBaseURL.equals(((ConfigurationBase) (obj)).siteStreamBaseURL)) goto _L4; else goto _L84
_L84:
        if(uploadBaseURL == null) goto _L86; else goto _L85
_L85:
        flag = flag3;
        if(!uploadBaseURL.equals(((ConfigurationBase) (obj)).uploadBaseURL)) goto _L4; else goto _L87
_L87:
        if(dispatcherImpl == null) goto _L89; else goto _L88
_L88:
        flag = flag3;
        if(!dispatcherImpl.equals(((ConfigurationBase) (obj)).dispatcherImpl)) goto _L4; else goto _L90
_L90:
        if(loggerFactory == null) goto _L92; else goto _L91
_L91:
        flag = flag3;
        if(!loggerFactory.equals(((ConfigurationBase) (obj)).loggerFactory)) goto _L4; else goto _L93
_L93:
        if(mediaProvider == null) goto _L95; else goto _L94
_L94:
        flag = flag3;
        if(!mediaProvider.equals(((ConfigurationBase) (obj)).mediaProvider)) goto _L4; else goto _L96
_L96:
        if(mediaProviderAPIKey == null) goto _L98; else goto _L97
_L97:
        flag = flag3;
        if(!mediaProviderAPIKey.equals(((ConfigurationBase) (obj)).mediaProviderAPIKey)) goto _L4; else goto _L99
_L99:
        if(mediaProviderParameters == null)
            break MISSING_BLOCK_LABEL_1177;
        flag = flag3;
        if(!mediaProviderParameters.equals(((ConfigurationBase) (obj)).mediaProviderParameters)) goto _L4; else goto _L100
_L100:
        boolean flag1;
        if(streamThreadName != null)
        {
            flag1 = streamThreadName.equals(((ConfigurationBase) (obj)).streamThreadName);
        } else
        {
            flag1 = flag2;
            if(((ConfigurationBase) (obj)).streamThreadName != null)
                flag1 = false;
        }
        return flag1;
_L26:
        if(((ConfigurationBase) (obj)).user != null)
            return false;
          goto _L27
_L29:
        if(((ConfigurationBase) (obj)).password != null)
            return false;
          goto _L30
_L32:
        if(((ConfigurationBase) (obj)).httpConf != null)
            return false;
          goto _L33
_L35:
        if(((ConfigurationBase) (obj)).oAuthConsumerKey != null)
            return false;
          goto _L36
_L38:
        if(((ConfigurationBase) (obj)).oAuthConsumerSecret != null)
            return false;
          goto _L39
_L41:
        if(((ConfigurationBase) (obj)).oAuthAccessToken != null)
            return false;
          goto _L42
_L44:
        if(((ConfigurationBase) (obj)).oAuthAccessTokenSecret != null)
            return false;
          goto _L45
_L47:
        if(((ConfigurationBase) (obj)).oAuth2TokenType != null)
            return false;
          goto _L48
_L50:
        if(((ConfigurationBase) (obj)).oAuth2AccessToken != null)
            return false;
          goto _L51
_L53:
        if(((ConfigurationBase) (obj)).oAuth2Scope != null)
            return false;
          goto _L54
_L56:
        if(((ConfigurationBase) (obj)).oAuthRequestTokenURL != null)
            return false;
          goto _L57
_L59:
        if(((ConfigurationBase) (obj)).oAuthAuthorizationURL != null)
            return false;
          goto _L60
_L62:
        if(((ConfigurationBase) (obj)).oAuthAccessTokenURL != null)
            return false;
          goto _L63
_L65:
        if(((ConfigurationBase) (obj)).oAuthAuthenticationURL != null)
            return false;
          goto _L66
_L68:
        if(((ConfigurationBase) (obj)).oAuth2TokenURL != null)
            return false;
          goto _L69
_L71:
        if(((ConfigurationBase) (obj)).oAuth2InvalidateTokenURL != null)
            return false;
          goto _L72
_L74:
        if(((ConfigurationBase) (obj)).restBaseURL != null)
            return false;
          goto _L75
_L77:
        if(((ConfigurationBase) (obj)).streamBaseURL != null)
            return false;
          goto _L78
_L80:
        if(((ConfigurationBase) (obj)).userStreamBaseURL != null)
            return false;
          goto _L81
_L83:
        if(((ConfigurationBase) (obj)).siteStreamBaseURL != null)
            return false;
          goto _L84
_L86:
        if(((ConfigurationBase) (obj)).uploadBaseURL != null)
            return false;
          goto _L87
_L89:
        if(((ConfigurationBase) (obj)).dispatcherImpl != null)
            return false;
          goto _L90
_L92:
        if(((ConfigurationBase) (obj)).loggerFactory != null)
            return false;
          goto _L93
_L95:
        if(((ConfigurationBase) (obj)).mediaProvider != null)
            return false;
          goto _L96
_L98:
        if(((ConfigurationBase) (obj)).mediaProviderAPIKey != null)
            return false;
          goto _L99
        if(((ConfigurationBase) (obj)).mediaProviderParameters != null)
            return false;
          goto _L100
    }

    public final int getAsyncNumThreads()
    {
        return asyncNumThreads;
    }

    public final long getContributingTo()
    {
        return contributingTo;
    }

    public String getDispatcherImpl()
    {
        return dispatcherImpl;
    }

    public HttpClientConfiguration getHttpClientConfiguration()
    {
        return httpConf;
    }

    public int getHttpStreamingReadTimeout()
    {
        return httpStreamingReadTimeout;
    }

    public String getLoggerFactory()
    {
        return loggerFactory;
    }

    public String getMediaProvider()
    {
        return mediaProvider;
    }

    public String getMediaProviderAPIKey()
    {
        return mediaProviderAPIKey;
    }

    public Properties getMediaProviderParameters()
    {
        return mediaProviderParameters;
    }

    public String getOAuth2AccessToken()
    {
        return oAuth2AccessToken;
    }

    public String getOAuth2InvalidateTokenURL()
    {
        return oAuth2InvalidateTokenURL;
    }

    public String getOAuth2Scope()
    {
        return oAuth2Scope;
    }

    public String getOAuth2TokenType()
    {
        return oAuth2TokenType;
    }

    public String getOAuth2TokenURL()
    {
        return oAuth2TokenURL;
    }

    public String getOAuthAccessToken()
    {
        return oAuthAccessToken;
    }

    public String getOAuthAccessTokenSecret()
    {
        return oAuthAccessTokenSecret;
    }

    public String getOAuthAccessTokenURL()
    {
        return oAuthAccessTokenURL;
    }

    public String getOAuthAuthenticationURL()
    {
        return oAuthAuthenticationURL;
    }

    public String getOAuthAuthorizationURL()
    {
        return oAuthAuthorizationURL;
    }

    public final String getOAuthConsumerKey()
    {
        return oAuthConsumerKey;
    }

    public final String getOAuthConsumerSecret()
    {
        return oAuthConsumerSecret;
    }

    public String getOAuthRequestTokenURL()
    {
        return oAuthRequestTokenURL;
    }

    public final String getPassword()
    {
        return password;
    }

    public String getRestBaseURL()
    {
        return restBaseURL;
    }

    public String getSiteStreamBaseURL()
    {
        return siteStreamBaseURL;
    }

    public String getStreamBaseURL()
    {
        return streamBaseURL;
    }

    public String getStreamThreadName()
    {
        return streamThreadName;
    }

    public String getUploadBaseURL()
    {
        return uploadBaseURL;
    }

    public final String getUser()
    {
        return user;
    }

    public String getUserStreamBaseURL()
    {
        return userStreamBaseURL;
    }

    public int hashCode()
    {
        int l9 = 1;
        int i10 = 0;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        int l5;
        int i6;
        int j6;
        int k6;
        int l6;
        int i7;
        int j7;
        int k7;
        int l7;
        int i8;
        int j8;
        int k8;
        int l8;
        int i9;
        int j9;
        int k9;
        int j10;
        int k10;
        int l10;
        int i11;
        int j11;
        if(debug)
            i = 1;
        else
            i = 0;
        if(user != null)
            j = user.hashCode();
        else
            j = 0;
        if(password != null)
            k = password.hashCode();
        else
            k = 0;
        if(httpConf != null)
            l = httpConf.hashCode();
        else
            l = 0;
        j10 = httpStreamingReadTimeout;
        k10 = httpRetryCount;
        l10 = httpRetryIntervalSeconds;
        if(oAuthConsumerKey != null)
            i1 = oAuthConsumerKey.hashCode();
        else
            i1 = 0;
        if(oAuthConsumerSecret != null)
            j1 = oAuthConsumerSecret.hashCode();
        else
            j1 = 0;
        if(oAuthAccessToken != null)
            k1 = oAuthAccessToken.hashCode();
        else
            k1 = 0;
        if(oAuthAccessTokenSecret != null)
            l1 = oAuthAccessTokenSecret.hashCode();
        else
            l1 = 0;
        if(oAuth2TokenType != null)
            i2 = oAuth2TokenType.hashCode();
        else
            i2 = 0;
        if(oAuth2AccessToken != null)
            j2 = oAuth2AccessToken.hashCode();
        else
            j2 = 0;
        if(oAuth2Scope != null)
            k2 = oAuth2Scope.hashCode();
        else
            k2 = 0;
        if(oAuthRequestTokenURL != null)
            l2 = oAuthRequestTokenURL.hashCode();
        else
            l2 = 0;
        if(oAuthAuthorizationURL != null)
            i3 = oAuthAuthorizationURL.hashCode();
        else
            i3 = 0;
        if(oAuthAccessTokenURL != null)
            j3 = oAuthAccessTokenURL.hashCode();
        else
            j3 = 0;
        if(oAuthAuthenticationURL != null)
            k3 = oAuthAuthenticationURL.hashCode();
        else
            k3 = 0;
        if(oAuth2TokenURL != null)
            l3 = oAuth2TokenURL.hashCode();
        else
            l3 = 0;
        if(oAuth2InvalidateTokenURL != null)
            i4 = oAuth2InvalidateTokenURL.hashCode();
        else
            i4 = 0;
        if(restBaseURL != null)
            j4 = restBaseURL.hashCode();
        else
            j4 = 0;
        if(streamBaseURL != null)
            k4 = streamBaseURL.hashCode();
        else
            k4 = 0;
        if(userStreamBaseURL != null)
            l4 = userStreamBaseURL.hashCode();
        else
            l4 = 0;
        if(siteStreamBaseURL != null)
            i5 = siteStreamBaseURL.hashCode();
        else
            i5 = 0;
        if(uploadBaseURL != null)
            j5 = uploadBaseURL.hashCode();
        else
            j5 = 0;
        if(dispatcherImpl != null)
            k5 = dispatcherImpl.hashCode();
        else
            k5 = 0;
        i11 = asyncNumThreads;
        if(loggerFactory != null)
            l5 = loggerFactory.hashCode();
        else
            l5 = 0;
        j11 = (int)(contributingTo ^ contributingTo >>> 32);
        if(includeMyRetweetEnabled)
            i6 = 1;
        else
            i6 = 0;
        if(includeEntitiesEnabled)
            j6 = 1;
        else
            j6 = 0;
        if(trimUserEnabled)
            k6 = 1;
        else
            k6 = 0;
        if(includeExtAltTextEnabled)
            l6 = 1;
        else
            l6 = 0;
        if(tweetModeExtended)
            i7 = 1;
        else
            i7 = 0;
        if(includeEmailEnabled)
            j7 = 1;
        else
            j7 = 0;
        if(jsonStoreEnabled)
            k7 = 1;
        else
            k7 = 0;
        if(mbeanEnabled)
            l7 = 1;
        else
            l7 = 0;
        if(userStreamRepliesAllEnabled)
            i8 = 1;
        else
            i8 = 0;
        if(userStreamWithFollowingsEnabled)
            j8 = 1;
        else
            j8 = 0;
        if(stallWarningsEnabled)
            k8 = 1;
        else
            k8 = 0;
        if(applicationOnlyAuthEnabled)
            l8 = 1;
        else
            l8 = 0;
        if(mediaProvider != null)
            i9 = mediaProvider.hashCode();
        else
            i9 = 0;
        if(mediaProviderAPIKey != null)
            j9 = mediaProviderAPIKey.hashCode();
        else
            j9 = 0;
        if(mediaProviderParameters != null)
            k9 = mediaProviderParameters.hashCode();
        else
            k9 = 0;
        if(!daemonEnabled)
            l9 = 0;
        if(streamThreadName != null)
            i10 = streamThreadName.hashCode();
        return ((((((((((((((((((((((((((((((((((((((((((((i * 31 + j) * 31 + k) * 31 + l) * 31 + j10) * 31 + k10) * 31 + l10) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + l1) * 31 + i2) * 31 + j2) * 31 + k2) * 31 + l2) * 31 + i3) * 31 + j3) * 31 + k3) * 31 + l3) * 31 + i4) * 31 + j4) * 31 + k4) * 31 + l4) * 31 + i5) * 31 + j5) * 31 + k5) * 31 + i11) * 31 + l5) * 31 + j11) * 31 + i6) * 31 + j6) * 31 + k6) * 31 + l6) * 31 + i7) * 31 + j7) * 31 + k7) * 31 + l7) * 31 + i8) * 31 + j8) * 31 + k8) * 31 + l8) * 31 + i9) * 31 + j9) * 31 + k9) * 31 + l9) * 31 + i10;
    }

    public boolean isApplicationOnlyAuthEnabled()
    {
        return applicationOnlyAuthEnabled;
    }

    public boolean isDaemonEnabled()
    {
        return daemonEnabled;
    }

    public final boolean isDebugEnabled()
    {
        return debug;
    }

    public boolean isIncludeEmailEnabled()
    {
        return includeEmailEnabled;
    }

    public boolean isIncludeEntitiesEnabled()
    {
        return includeEntitiesEnabled;
    }

    public boolean isIncludeExtAltTextEnabled()
    {
        return includeExtAltTextEnabled;
    }

    public boolean isIncludeMyRetweetEnabled()
    {
        return includeMyRetweetEnabled;
    }

    public boolean isJSONStoreEnabled()
    {
        return jsonStoreEnabled;
    }

    public boolean isMBeanEnabled()
    {
        return mbeanEnabled;
    }

    public boolean isStallWarningsEnabled()
    {
        return stallWarningsEnabled;
    }

    public boolean isTrimUserEnabled()
    {
        return trimUserEnabled;
    }

    public boolean isTweetModeExtended()
    {
        return tweetModeExtended;
    }

    public boolean isUserStreamRepliesAllEnabled()
    {
        return userStreamRepliesAllEnabled;
    }

    public boolean isUserStreamWithFollowingsEnabled()
    {
        return userStreamWithFollowingsEnabled;
    }

    protected Object readResolve()
        throws ObjectStreamException
    {
        return getInstance(this);
    }

    protected final void setApplicationOnlyAuthEnabled(boolean flag)
    {
        applicationOnlyAuthEnabled = flag;
    }

    protected final void setAsyncNumThreads(int i)
    {
        asyncNumThreads = i;
    }

    protected final void setContributingTo(long l)
    {
        contributingTo = l;
    }

    protected void setDaemonEnabled(boolean flag)
    {
        daemonEnabled = flag;
    }

    protected final void setDebug(boolean flag)
    {
        debug = flag;
    }

    protected final void setDispatcherImpl(String s)
    {
        dispatcherImpl = s;
    }

    protected final void setGZIPEnabled(boolean flag)
    {
        httpConf = new MyHttpClientConfiguration(httpConf.getHttpProxyHost(), httpConf.getHttpProxyUser(), httpConf.getHttpProxyPassword(), httpConf.getHttpProxyPort(), httpConf.getHttpConnectionTimeout(), httpConf.getHttpReadTimeout(), httpConf.isPrettyDebugEnabled(), flag);
    }

    protected final void setHttpConnectionTimeout(int i)
    {
        httpConf = new MyHttpClientConfiguration(httpConf.getHttpProxyHost(), httpConf.getHttpProxyUser(), httpConf.getHttpProxyPassword(), httpConf.getHttpProxyPort(), i, httpConf.getHttpReadTimeout(), httpConf.isPrettyDebugEnabled(), httpConf.isGZIPEnabled());
    }

    protected final void setHttpProxyHost(String s)
    {
        httpConf = new MyHttpClientConfiguration(s, httpConf.getHttpProxyUser(), httpConf.getHttpProxyPassword(), httpConf.getHttpProxyPort(), httpConf.getHttpConnectionTimeout(), httpConf.getHttpReadTimeout(), httpConf.isPrettyDebugEnabled(), httpConf.isGZIPEnabled());
    }

    protected final void setHttpProxyPassword(String s)
    {
        httpConf = new MyHttpClientConfiguration(httpConf.getHttpProxyHost(), httpConf.getHttpProxyUser(), s, httpConf.getHttpProxyPort(), httpConf.getHttpConnectionTimeout(), httpConf.getHttpReadTimeout(), httpConf.isPrettyDebugEnabled(), httpConf.isGZIPEnabled());
    }

    protected final void setHttpProxyPort(int i)
    {
        httpConf = new MyHttpClientConfiguration(httpConf.getHttpProxyHost(), httpConf.getHttpProxyUser(), httpConf.getHttpProxyPassword(), i, httpConf.getHttpConnectionTimeout(), httpConf.getHttpReadTimeout(), httpConf.isPrettyDebugEnabled(), httpConf.isGZIPEnabled());
    }

    protected final void setHttpProxyUser(String s)
    {
        httpConf = new MyHttpClientConfiguration(httpConf.getHttpProxyHost(), s, httpConf.getHttpProxyPassword(), httpConf.getHttpProxyPort(), httpConf.getHttpConnectionTimeout(), httpConf.getHttpReadTimeout(), httpConf.isPrettyDebugEnabled(), httpConf.isGZIPEnabled());
    }

    protected final void setHttpReadTimeout(int i)
    {
        httpConf = new MyHttpClientConfiguration(httpConf.getHttpProxyHost(), httpConf.getHttpProxyUser(), httpConf.getHttpProxyPassword(), httpConf.getHttpProxyPort(), httpConf.getHttpConnectionTimeout(), i, httpConf.isPrettyDebugEnabled(), httpConf.isGZIPEnabled());
    }

    protected final void setHttpRetryCount(int i)
    {
        httpRetryCount = i;
    }

    protected final void setHttpRetryIntervalSeconds(int i)
    {
        httpRetryIntervalSeconds = i;
    }

    protected final void setHttpStreamingReadTimeout(int i)
    {
        httpStreamingReadTimeout = i;
    }

    protected void setIncludeEmailEnabled(boolean flag)
    {
        includeEmailEnabled = flag;
    }

    protected void setIncludeEntitiesEnabled(boolean flag)
    {
        includeEntitiesEnabled = flag;
    }

    public void setIncludeExtAltTextEnabled(boolean flag)
    {
        includeExtAltTextEnabled = flag;
    }

    public void setIncludeMyRetweetEnabled(boolean flag)
    {
        includeMyRetweetEnabled = flag;
    }

    protected final void setJSONStoreEnabled(boolean flag)
    {
        jsonStoreEnabled = flag;
    }

    protected final void setLoggerFactory(String s)
    {
        loggerFactory = s;
    }

    protected final void setMBeanEnabled(boolean flag)
    {
        mbeanEnabled = flag;
    }

    protected final void setMediaProvider(String s)
    {
        mediaProvider = s;
    }

    protected final void setMediaProviderAPIKey(String s)
    {
        mediaProviderAPIKey = s;
    }

    protected final void setMediaProviderParameters(Properties properties)
    {
        mediaProviderParameters = properties;
    }

    protected final void setOAuth2AccessToken(String s)
    {
        oAuth2AccessToken = s;
    }

    protected final void setOAuth2InvalidateTokenURL(String s)
    {
        oAuth2InvalidateTokenURL = s;
    }

    protected final void setOAuth2Scope(String s)
    {
        oAuth2Scope = s;
    }

    protected final void setOAuth2TokenType(String s)
    {
        oAuth2TokenType = s;
    }

    protected final void setOAuth2TokenURL(String s)
    {
        oAuth2TokenURL = s;
    }

    protected final void setOAuthAccessToken(String s)
    {
        oAuthAccessToken = s;
    }

    protected final void setOAuthAccessTokenSecret(String s)
    {
        oAuthAccessTokenSecret = s;
    }

    protected final void setOAuthAccessTokenURL(String s)
    {
        oAuthAccessTokenURL = s;
    }

    protected final void setOAuthAuthenticationURL(String s)
    {
        oAuthAuthenticationURL = s;
    }

    protected final void setOAuthAuthorizationURL(String s)
    {
        oAuthAuthorizationURL = s;
    }

    protected final void setOAuthConsumerKey(String s)
    {
        oAuthConsumerKey = s;
    }

    protected final void setOAuthConsumerSecret(String s)
    {
        oAuthConsumerSecret = s;
    }

    protected final void setOAuthRequestTokenURL(String s)
    {
        oAuthRequestTokenURL = s;
    }

    protected final void setPassword(String s)
    {
        password = s;
    }

    protected final void setPrettyDebugEnabled(boolean flag)
    {
        httpConf = new MyHttpClientConfiguration(httpConf.getHttpProxyHost(), httpConf.getHttpProxyUser(), httpConf.getHttpProxyPassword(), httpConf.getHttpProxyPort(), httpConf.getHttpConnectionTimeout(), httpConf.getHttpReadTimeout(), flag, httpConf.isGZIPEnabled());
    }

    protected final void setRestBaseURL(String s)
    {
        restBaseURL = s;
    }

    protected final void setSiteStreamBaseURL(String s)
    {
        siteStreamBaseURL = s;
    }

    protected final void setStallWarningsEnabled(boolean flag)
    {
        stallWarningsEnabled = flag;
    }

    protected final void setStreamBaseURL(String s)
    {
        streamBaseURL = s;
    }

    protected final void setStreamThreadName(String s)
    {
        streamThreadName = s;
    }

    public void setTrimUserEnabled(boolean flag)
    {
        trimUserEnabled = flag;
    }

    public void setTweetModeExtended(boolean flag)
    {
        tweetModeExtended = flag;
    }

    protected final void setUploadBaseURL(String s)
    {
        uploadBaseURL = s;
    }

    protected final void setUser(String s)
    {
        user = s;
    }

    protected final void setUserStreamBaseURL(String s)
    {
        userStreamBaseURL = s;
    }

    protected final void setUserStreamRepliesAllEnabled(boolean flag)
    {
        userStreamRepliesAllEnabled = flag;
    }

    protected final void setUserStreamWithFollowingsEnabled(boolean flag)
    {
        userStreamWithFollowingsEnabled = flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ConfigurationBase{debug=").append(debug).append(", user='").append(user).append('\'').append(", password='").append(password).append('\'').append(", httpConf=").append(httpConf).append(", httpStreamingReadTimeout=").append(httpStreamingReadTimeout).append(", httpRetryCount=").append(httpRetryCount).append(", httpRetryIntervalSeconds=").append(httpRetryIntervalSeconds).append(", oAuthConsumerKey='").append(oAuthConsumerKey).append('\'').append(", oAuthConsumerSecret='").append(oAuthConsumerSecret).append('\'').append(", oAuthAccessToken='").append(oAuthAccessToken).append('\'').append(", oAuthAccessTokenSecret='").append(oAuthAccessTokenSecret).append('\'').append(", oAuth2TokenType='").append(oAuth2TokenType).append('\'').append(", oAuth2AccessToken='").append(oAuth2AccessToken).append('\'').append(", oAuth2Scope='").append(oAuth2Scope).append('\'').append(", oAuthRequestTokenURL='").append(oAuthRequestTokenURL).append('\'').append(", oAuthAuthorizationURL='").append(oAuthAuthorizationURL).append('\'').append(", oAuthAccessTokenURL='").append(oAuthAccessTokenURL).append('\'').append(", oAuthAuthenticationURL='").append(oAuthAuthenticationURL).append('\'').append(", oAuth2TokenURL='").append(oAuth2TokenURL).append('\'').append(", oAuth2InvalidateTokenURL='").append(oAuth2InvalidateTokenURL).append('\'').append(", restBaseURL='").append(restBaseURL).append('\'').append(", streamBaseURL='").append(streamBaseURL).append('\'').append(", userStreamBaseURL='").append(userStreamBaseURL).append('\'').append(", siteStreamBaseURL='").append(siteStreamBaseURL).append('\'').append(", uploadBaseURL='").append(uploadBaseURL).append('\'').append(", dispatcherImpl='").append(dispatcherImpl).append('\'').append(", asyncNumThreads=").append(asyncNumThreads).append(", loggerFactory='").append(loggerFactory).append('\'').append(", contributingTo=").append(contributingTo).append(", includeMyRetweetEnabled=").append(includeMyRetweetEnabled).append(", includeEntitiesEnabled=").append(includeEntitiesEnabled).append(", trimUserEnabled=").append(trimUserEnabled).append(", includeExtAltTextEnabled=").append(includeExtAltTextEnabled).append(", tweetModeExtended=").append(tweetModeExtended).append(", includeEmailEnabled=").append(includeEmailEnabled).append(", jsonStoreEnabled=").append(jsonStoreEnabled).append(", mbeanEnabled=").append(mbeanEnabled).append(", userStreamRepliesAllEnabled=").append(userStreamRepliesAllEnabled).append(", userStreamWithFollowingsEnabled=").append(userStreamWithFollowingsEnabled).append(", stallWarningsEnabled=").append(stallWarningsEnabled).append(", applicationOnlyAuthEnabled=").append(applicationOnlyAuthEnabled).append(", mediaProvider='").append(mediaProvider).append('\'').append(", mediaProviderAPIKey='").append(mediaProviderAPIKey).append('\'').append(", mediaProviderParameters=").append(mediaProviderParameters).append(", daemonEnabled=").append(daemonEnabled).append(", streamThreadName='").append(streamThreadName).append('\'').append('}').toString();
    }

    private static final List instances = new ArrayList();
    private static final long serialVersionUID = 0xf8085720L;
    private boolean applicationOnlyAuthEnabled;
    private int asyncNumThreads;
    private long contributingTo;
    private boolean daemonEnabled;
    private boolean debug;
    private String dispatcherImpl;
    private HttpClientConfiguration httpConf;
    private int httpRetryCount;
    private int httpRetryIntervalSeconds;
    private int httpStreamingReadTimeout;
    private boolean includeEmailEnabled;
    private boolean includeEntitiesEnabled;
    private boolean includeExtAltTextEnabled;
    private boolean includeMyRetweetEnabled;
    private boolean jsonStoreEnabled;
    private String loggerFactory;
    private boolean mbeanEnabled;
    private String mediaProvider;
    private String mediaProviderAPIKey;
    private Properties mediaProviderParameters;
    private String oAuth2AccessToken;
    private String oAuth2InvalidateTokenURL;
    private String oAuth2Scope;
    private String oAuth2TokenType;
    private String oAuth2TokenURL;
    private String oAuthAccessToken;
    private String oAuthAccessTokenSecret;
    private String oAuthAccessTokenURL;
    private String oAuthAuthenticationURL;
    private String oAuthAuthorizationURL;
    private String oAuthConsumerKey;
    private String oAuthConsumerSecret;
    private String oAuthRequestTokenURL;
    private String password;
    private String restBaseURL;
    private String siteStreamBaseURL;
    private boolean stallWarningsEnabled;
    private String streamBaseURL;
    private String streamThreadName;
    private boolean trimUserEnabled;
    private boolean tweetModeExtended;
    private String uploadBaseURL;
    private String user;
    private String userStreamBaseURL;
    private boolean userStreamRepliesAllEnabled;
    private boolean userStreamWithFollowingsEnabled;



}
