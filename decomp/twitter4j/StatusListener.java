// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            StreamListener, StatusDeletionNotice, StallWarning, Status

public interface StatusListener
    extends StreamListener
{

    public abstract void onDeletionNotice(StatusDeletionNotice statusdeletionnotice);

    public abstract void onScrubGeo(long l, long l1);

    public abstract void onStallWarning(StallWarning stallwarning);

    public abstract void onStatus(Status status);

    public abstract void onTrackLimitationNotice(int i);
}
