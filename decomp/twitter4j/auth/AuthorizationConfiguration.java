// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;


public interface AuthorizationConfiguration
{

    public abstract String getOAuth2AccessToken();

    public abstract String getOAuth2TokenType();

    public abstract String getOAuthAccessToken();

    public abstract String getOAuthAccessTokenSecret();

    public abstract String getOAuthConsumerKey();

    public abstract String getOAuthConsumerSecret();

    public abstract String getPassword();

    public abstract String getUser();
}
