// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import twitter4j.auth.Authorization;
import twitter4j.auth.BasicAuthorization;

// Referenced classes of package twitter4j:
//            HttpRequest

public class XAuthAuthorization
    implements Authorization, Serializable
{

    public XAuthAuthorization(BasicAuthorization basicauthorization)
    {
        basic = basicauthorization;
    }

    public String getAuthorizationHeader(HttpRequest httprequest)
    {
        return basic.getAuthorizationHeader(httprequest);
    }

    public String getConsumerKey()
    {
        return consumerKey;
    }

    public String getConsumerSecret()
    {
        return consumerSecret;
    }

    public String getPassword()
    {
        return basic.getPassword();
    }

    public String getUserId()
    {
        return basic.getUserId();
    }

    public boolean isEnabled()
    {
        return basic.isEnabled();
    }

    public void setOAuthConsumer(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        consumerKey = s;
        consumerSecret = s1;
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    private static final long serialVersionUID = 0xa35519eaL;
    private final BasicAuthorization basic;
    private String consumerKey;
    private String consumerSecret;
}
