// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.auth;

import java.io.Serializable;
import twitter4j.HttpRequest;

public interface Authorization
    extends Serializable
{

    public abstract String getAuthorizationHeader(HttpRequest httprequest);

    public abstract boolean isEnabled();
}
