// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import twitter4j.auth.OAuthSupport;
import twitter4j.util.function.Consumer;

// Referenced classes of package twitter4j:
//            TwitterBase, ConnectionLifeCycleListener, StreamListener, FilterQuery, 
//            StreamController

public interface TwitterStream
    extends OAuthSupport, TwitterBase
{

    public abstract void addConnectionLifeCycleListener(ConnectionLifeCycleListener connectionlifecyclelistener);

    public abstract void addListener(StreamListener streamlistener);

    public abstract void cleanUp();

    public abstract void clearListeners();

    public abstract void filter(FilterQuery filterquery);

    public transient abstract void filter(String as[]);

    public abstract void firehose(int i);

    public abstract void links(int i);

    public abstract TwitterStream onException(Consumer consumer);

    public abstract TwitterStream onStatus(Consumer consumer);

    public abstract void removeListener(StreamListener streamlistener);

    public abstract void replaceListener(StreamListener streamlistener, StreamListener streamlistener1);

    public abstract void retweet();

    public abstract void sample();

    public abstract void sample(String s);

    public abstract void shutdown();

    public transient abstract StreamController site(boolean flag, long al[]);

    public abstract void user();

    public transient abstract void user(String as[]);
}
