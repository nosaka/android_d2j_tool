// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            HttpClientConfiguration

class StreamingReadTimeoutConfiguration
    implements HttpClientConfiguration
{

    StreamingReadTimeoutConfiguration(Configuration configuration)
    {
        nestedConf = configuration;
    }

    public int getHttpConnectionTimeout()
    {
        return nestedConf.getHttpClientConfiguration().getHttpConnectionTimeout();
    }

    public String getHttpProxyHost()
    {
        return nestedConf.getHttpClientConfiguration().getHttpProxyHost();
    }

    public String getHttpProxyPassword()
    {
        return nestedConf.getHttpClientConfiguration().getHttpProxyPassword();
    }

    public int getHttpProxyPort()
    {
        return nestedConf.getHttpClientConfiguration().getHttpProxyPort();
    }

    public String getHttpProxyUser()
    {
        return nestedConf.getHttpClientConfiguration().getHttpProxyUser();
    }

    public int getHttpReadTimeout()
    {
        return nestedConf.getHttpStreamingReadTimeout();
    }

    public int getHttpRetryCount()
    {
        return nestedConf.getHttpClientConfiguration().getHttpRetryCount();
    }

    public int getHttpRetryIntervalSeconds()
    {
        return nestedConf.getHttpClientConfiguration().getHttpRetryIntervalSeconds();
    }

    public boolean isGZIPEnabled()
    {
        return nestedConf.getHttpClientConfiguration().isGZIPEnabled();
    }

    public boolean isPrettyDebugEnabled()
    {
        return nestedConf.getHttpClientConfiguration().isPrettyDebugEnabled();
    }

    final Configuration nestedConf;
}
