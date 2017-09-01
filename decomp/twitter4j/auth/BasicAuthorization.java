// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;

import java.io.Serializable;
import twitter4j.BASE64Encoder;
import twitter4j.HttpRequest;

// Referenced classes of package twitter4j.auth:
//            Authorization

public class BasicAuthorization
    implements Authorization, Serializable
{

    public BasicAuthorization(String s, String s1)
    {
        userId = s;
        password = s1;
    }

    private String encodeBasicAuthenticationString()
    {
        if(userId != null && password != null)
            return (new StringBuilder()).append("Basic ").append(BASE64Encoder.encode((new StringBuilder()).append(userId).append(":").append(password).toString().getBytes())).toString();
        else
            return null;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof BasicAuthorization))
        {
            return false;
        } else
        {
            obj = (BasicAuthorization)obj;
            return basic.equals(((BasicAuthorization) (obj)).basic);
        }
    }

    public String getAuthorizationHeader(HttpRequest httprequest)
    {
        return basic;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserId()
    {
        return userId;
    }

    public int hashCode()
    {
        return basic.hashCode();
    }

    public boolean isEnabled()
    {
        return true;
    }

    public String toString()
    {
        return (new StringBuilder()).append("BasicAuthorization{userId='").append(userId).append('\'').append(", password='**********''").append('}').toString();
    }

    private static final long serialVersionUID = 0xfb5b7207L;
    private final String basic = encodeBasicAuthenticationString();
    private final String password;
    private final String userId;
}
