// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

// Referenced classes of package twitter4j:
//            TwitterResponse, User

public interface UserList
    extends Comparable, TwitterResponse, Serializable
{

    public abstract Date getCreatedAt();

    public abstract String getDescription();

    public abstract String getFullName();

    public abstract long getId();

    public abstract int getMemberCount();

    public abstract String getName();

    public abstract String getSlug();

    public abstract int getSubscriberCount();

    public abstract URI getURI();

    public abstract User getUser();

    public abstract boolean isFollowing();

    public abstract boolean isPublic();
}
