// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


public interface HttpClientConfiguration
{

    public abstract int getHttpConnectionTimeout();

    public abstract String getHttpProxyHost();

    public abstract String getHttpProxyPassword();

    public abstract int getHttpProxyPort();

    public abstract String getHttpProxyUser();

    public abstract int getHttpReadTimeout();

    public abstract int getHttpRetryCount();

    public abstract int getHttpRetryIntervalSeconds();

    public abstract boolean isGZIPEnabled();

    public abstract boolean isPrettyDebugEnabled();
}
