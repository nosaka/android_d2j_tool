// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            TwitterResponse

public interface OEmbed
    extends TwitterResponse, Serializable
{

    public abstract String getAuthorName();

    public abstract String getAuthorURL();

    public abstract long getCacheAge();

    public abstract String getHtml();

    public abstract String getURL();

    public abstract String getVersion();

    public abstract int getWidth();
}
