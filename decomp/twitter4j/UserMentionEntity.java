// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            TweetEntity

public interface UserMentionEntity
    extends TweetEntity, Serializable
{

    public abstract int getEnd();

    public abstract long getId();

    public abstract String getName();

    public abstract String getScreenName();

    public abstract int getStart();

    public abstract String getText();
}
