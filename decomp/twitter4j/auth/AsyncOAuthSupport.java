// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;


// Referenced classes of package twitter4j.auth:
//            RequestToken

public interface AsyncOAuthSupport
{

    public abstract void getOAuthAccessTokenAsync();

    public abstract void getOAuthAccessTokenAsync(String s);

    public abstract void getOAuthAccessTokenAsync(String s, String s1);

    public abstract void getOAuthAccessTokenAsync(RequestToken requesttoken);

    public abstract void getOAuthAccessTokenAsync(RequestToken requesttoken, String s);

    public abstract void getOAuthRequestTokenAsync();

    public abstract void getOAuthRequestTokenAsync(String s);

    public abstract void getOAuthRequestTokenAsync(String s, String s1);

    public abstract void getOAuthRequestTokenAsync(String s, String s1, String s2);
}
