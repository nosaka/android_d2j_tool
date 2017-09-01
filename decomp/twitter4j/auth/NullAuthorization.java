// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;

import java.io.ObjectStreamException;
import java.io.Serializable;
import twitter4j.HttpRequest;

// Referenced classes of package twitter4j.auth:
//            Authorization

public class NullAuthorization
    implements Authorization, Serializable
{

    private NullAuthorization()
    {
    }

    public static NullAuthorization getInstance()
    {
        return SINGLETON;
    }

    private Object readResolve()
        throws ObjectStreamException
    {
        return SINGLETON;
    }

    public boolean equals(Object obj)
    {
        return SINGLETON == obj;
    }

    public String getAuthorizationHeader(HttpRequest httprequest)
    {
        return null;
    }

    public boolean isEnabled()
    {
        return false;
    }

    public String toString()
    {
        return "NullAuthentication{SINGLETON}";
    }

    private static final NullAuthorization SINGLETON = new NullAuthorization();
    private static final long serialVersionUID = 0x91fa32aaL;

}
