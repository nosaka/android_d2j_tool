// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Date;

// Referenced classes of package twitter4j:
//            TwitterResponse, EntitySupport, User

public interface DirectMessage
    extends TwitterResponse, EntitySupport, Serializable
{

    public abstract Date getCreatedAt();

    public abstract long getId();

    public abstract User getRecipient();

    public abstract long getRecipientId();

    public abstract String getRecipientScreenName();

    public abstract User getSender();

    public abstract long getSenderId();

    public abstract String getSenderScreenName();

    public abstract String getText();
}
