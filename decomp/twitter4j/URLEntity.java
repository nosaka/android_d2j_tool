// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            TweetEntity

public interface URLEntity
    extends TweetEntity, Serializable
{

    public abstract String getDisplayURL();

    public abstract int getEnd();

    public abstract String getExpandedURL();

    public abstract int getStart();

    public abstract String getText();

    public abstract String getURL();
}
