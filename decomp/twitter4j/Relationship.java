// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            TwitterResponse

public interface Relationship
    extends TwitterResponse, Serializable
{

    public abstract boolean canSourceDm();

    public abstract long getSourceUserId();

    public abstract String getSourceUserScreenName();

    public abstract long getTargetUserId();

    public abstract String getTargetUserScreenName();

    public abstract boolean isSourceBlockingTarget();

    public abstract boolean isSourceFollowedByTarget();

    public abstract boolean isSourceFollowingTarget();

    public abstract boolean isSourceMutingTarget();

    public abstract boolean isSourceNotificationsEnabled();

    public abstract boolean isSourceWantRetweets();

    public abstract boolean isTargetFollowedBySource();

    public abstract boolean isTargetFollowingSource();
}
