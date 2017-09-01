// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import twitter4j.auth.Authorization;
import twitter4j.conf.Configuration;
import twitter4j.util.function.Consumer;

// Referenced classes of package twitter4j:
//            TwitterBaseImpl, TwitterStream, Logger, StreamingReadTimeoutConfiguration, 
//            HttpClientFactory, HttpClient, HttpParameter, TwitterException, 
//            StatusStreamImpl, DispatcherFactory, StreamListener, RawStreamListener, 
//            SiteStreamsListener, StatusListener, FilterQuery, StringUtil, 
//            HttpResponse, UserStreamImpl, Dispatcher, StreamController, 
//            StatusStream, ConnectionLifeCycleListener, UserStream, StatusStreamBase, 
//            StatusAdapter, SiteStreamsImpl, Status

class TwitterStreamImpl extends TwitterBaseImpl
    implements TwitterStream
{
    static final class Mode extends Enum
    {

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(twitter4j/TwitterStreamImpl$Mode, s);
        }

        public static Mode[] values()
        {
            return (Mode[])$VALUES.clone();
        }

        private static final Mode $VALUES[];
        public static final Mode site;
        public static final Mode status;
        public static final Mode user;

        static 
        {
            user = new Mode("user", 0);
            status = new Mode("status", 1);
            site = new Mode("site", 2);
            $VALUES = (new Mode[] {
                user, status, site
            });
        }

        private Mode(String s, int i)
        {
            super(s, i);
        }
    }

    abstract class TwitterStreamConsumer extends Thread
    {

        private void setStatus(String s)
        {
            s = (new StringBuilder()).append(NAME).append(s).toString();
            setName(s);
            TwitterStreamImpl.logger.debug(s);
        }

        public void close()
        {
            this;
            JVM INSTR monitorenter ;
            StatusStreamBase statusstreambase;
            setStatus("[Disposing thread]");
            closed = true;
            statusstreambase = stream;
            if(statusstreambase == null)
                break MISSING_BLOCK_LABEL_29;
            stream.close();
_L2:
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            ((Exception) (obj)).printStackTrace();
            TwitterStreamImpl.logger.warn(((Exception) (obj)).getMessage());
            continue; /* Loop/switch isn't completed */
            obj;
            throw obj;
            obj;
            if(true) goto _L2; else goto _L1
_L1:
        }

        abstract StatusStream getStream()
            throws TwitterException;

        public void run()
        {
            int i;
            int k;
            boolean flag;
            boolean flag1;
            flag1 = false;
            flag = false;
            k = 0;
            i = 0;
_L14:
            int j;
            int l;
            l = i;
            if(closed)
                break MISSING_BLOCK_LABEL_979;
            j = i;
            l = k;
            if(closed)
                continue; /* Loop/switch isn't completed */
            j = i;
            l = k;
            if(stream != null)
                continue; /* Loop/switch isn't completed */
            j = i;
            l = k;
            TwitterStreamImpl.logger.info("Establishing connection.");
            j = i;
            l = k;
            setStatus("[Establishing connection]");
            j = i;
            l = k;
            stream = (StatusStreamBase)getStream();
            boolean flag2;
            boolean flag4;
            flag4 = true;
            flag2 = true;
            j = ((flag4) ? 1 : 0);
            l = k;
            TwitterStreamImpl.logger.info("Connection established.");
            j = ((flag4) ? 1 : 0);
            l = k;
            Iterator iterator = lifeCycleListeners.iterator();
_L3:
            j = ((flag4) ? 1 : 0);
            l = k;
            if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            j = ((flag4) ? 1 : 0);
            l = k;
            ConnectionLifeCycleListener connectionlifecyclelistener = (ConnectionLifeCycleListener)iterator.next();
            j = ((flag4) ? 1 : 0);
            l = k;
            connectionlifecyclelistener.onConnect();
              goto _L3
            Exception exception;
            exception;
            j = ((flag4) ? 1 : 0);
            l = k;
            TwitterStreamImpl.logger.warn(exception.getMessage());
              goto _L3
            Object obj;
            obj;
            TwitterStreamImpl.logger.info(((TwitterException) (obj)).getMessage());
            i = j;
            k = l;
            if(closed)
                continue; /* Loop/switch isn't completed */
            k = j;
            i = l;
            if(l != 0) goto _L5; else goto _L4
_L4:
            boolean flag3;
            boolean flag5;
            boolean flag6;
            if(((TwitterException) (obj)).getStatusCode() == 403)
            {
                TwitterStreamImpl.logger.warn("This account is not in required role. ", ((TwitterException) (obj)).getMessage());
                closed = true;
                StreamListener astreamlistener[] = streamListeners;
                k = astreamlistener.length;
                i = ((flag) ? 1 : 0);
                do
                {
                    l = j;
                    if(i >= k)
                        break;
                    astreamlistener[i].onException(((Exception) (obj)));
                    i++;
                } while(true);
                break MISSING_BLOCK_LABEL_979;
            }
            if(((TwitterException) (obj)).getStatusCode() == 406)
            {
                TwitterStreamImpl.logger.warn("Parameter not accepted with the role. ", ((TwitterException) (obj)).getMessage());
                closed = true;
                StreamListener astreamlistener1[] = streamListeners;
                k = astreamlistener1.length;
                i = ((flag1) ? 1 : 0);
                do
                {
                    l = j;
                    if(i >= k)
                        break;
                    astreamlistener1[i].onException(((Exception) (obj)));
                    i++;
                } while(true);
                break MISSING_BLOCK_LABEL_979;
            }
            j = 0;
            for(Iterator iterator2 = lifeCycleListeners.iterator(); iterator2.hasNext();)
            {
                ConnectionLifeCycleListener connectionlifecyclelistener1 = (ConnectionLifeCycleListener)iterator2.next();
                try
                {
                    connectionlifecyclelistener1.onDisconnect();
                }
                catch(Exception exception2)
                {
                    TwitterStreamImpl.logger.warn(exception2.getMessage());
                }
            }

              goto _L6
_L2:
            flag5 = false;
            flag3 = false;
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            TwitterStreamImpl.logger.info("Receiving status stream.");
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            setStatus("[Receiving stream]");
_L7:
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            flag6 = closed;
            i = ((flag2) ? 1 : 0);
            k = ((flag3) ? 1 : 0);
            if(flag6)
                continue; /* Loop/switch isn't completed */
            stream.next(streamListeners, rawStreamListeners);
              goto _L7
            obj;
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            TwitterStreamImpl.logger.warn(((IllegalStateException) (obj)).getMessage());
            i = ((flag2) ? 1 : 0);
            k = ((flag3) ? 1 : 0);
            continue; /* Loop/switch isn't completed */
            obj;
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            TwitterStreamImpl.logger.info(((TwitterException) (obj)).getMessage());
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            stream.onException(((Exception) (obj)), streamListeners, rawStreamListeners);
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            throw obj;
            obj;
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            if(obj instanceof NullPointerException) goto _L7; else goto _L8
_L8:
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            if("Inflater has been closed".equals(((Exception) (obj)).getMessage())) goto _L7; else goto _L9
_L9:
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            TwitterStreamImpl.logger.info(((Exception) (obj)).getMessage());
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            stream.onException(((Exception) (obj)), streamListeners, rawStreamListeners);
            j = ((flag4) ? 1 : 0);
            l = ((flag5) ? 1 : 0);
            closed = true;
            i = ((flag2) ? 1 : 0);
            k = ((flag3) ? 1 : 0);
            continue; /* Loop/switch isn't completed */
_L6:
            if(((TwitterException) (obj)).getStatusCode() <= 200) goto _L11; else goto _L10
_L10:
            i = 10000;
            k = j;
_L5:
            j = i;
            if(((TwitterException) (obj)).getStatusCode() > 200)
            {
                j = i;
                if(i < 10000)
                    j = 10000;
            }
            if(k != 0)
            {
                for(Iterator iterator3 = lifeCycleListeners.iterator(); iterator3.hasNext();)
                {
                    ConnectionLifeCycleListener connectionlifecyclelistener2 = (ConnectionLifeCycleListener)iterator3.next();
                    try
                    {
                        connectionlifecyclelistener2.onDisconnect();
                    }
                    catch(Exception exception3)
                    {
                        TwitterStreamImpl.logger.warn(exception3.getMessage());
                    }
                }

            }
            break; /* Loop/switch isn't completed */
_L11:
            k = j;
            i = l;
            if(l == 0)
            {
                i = 250;
                k = j;
            }
            if(true) goto _L5; else goto _L12
_L12:
            StreamListener astreamlistener2[] = streamListeners;
            k = astreamlistener2.length;
            for(i = 0; i < k; i++)
                astreamlistener2[i].onException(((Exception) (obj)));

            k = j;
            if(!closed)
            {
                TwitterStreamImpl.logger.info((new StringBuilder()).append("Waiting for ").append(j).append(" milliseconds").toString());
                setStatus((new StringBuilder()).append("[Waiting for ").append(j).append(" milliseconds]").toString());
                long l1 = j;
                Iterator iterator1;
                Object obj1;
                Exception exception1;
                ConnectionLifeCycleListener connectionlifecyclelistener3;
                Exception exception4;
                try
                {
                    Thread.sleep(l1);
                }
                catch(InterruptedException interruptedexception) { }
                if(((TwitterException) (obj)).getStatusCode() > 200)
                    i = 0x3a980;
                else
                    i = 16000;
                k = Math.min(j * 2, i);
            }
            stream = null;
            TwitterStreamImpl.logger.debug(((TwitterException) (obj)).getMessage());
            i = 0;
            continue; /* Loop/switch isn't completed */
            if(stream == null || l == 0)
                break MISSING_BLOCK_LABEL_1271;
            stream.close();
            for(iterator1 = lifeCycleListeners.iterator(); iterator1.hasNext();)
            {
                obj1 = (ConnectionLifeCycleListener)iterator1.next();
                try
                {
                    ((ConnectionLifeCycleListener) (obj1)).onDisconnect();
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception1)
                {
                    TwitterStreamImpl.logger.warn(exception1.getMessage());
                }
            }

            break MISSING_BLOCK_LABEL_1271;
            iterator1;
            for(iterator1 = lifeCycleListeners.iterator(); iterator1.hasNext();)
            {
                exception1 = (ConnectionLifeCycleListener)iterator1.next();
                try
                {
                    exception1.onDisconnect();
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception1)
                {
                    TwitterStreamImpl.logger.warn(exception1.getMessage());
                }
            }

            break MISSING_BLOCK_LABEL_1271;
            iterator1;
            iterator1.printStackTrace();
            TwitterStreamImpl.logger.warn(iterator1.getMessage());
            for(iterator1 = lifeCycleListeners.iterator(); iterator1.hasNext();)
            {
                exception1 = (ConnectionLifeCycleListener)iterator1.next();
                try
                {
                    exception1.onDisconnect();
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception1)
                {
                    TwitterStreamImpl.logger.warn(exception1.getMessage());
                }
            }

            break MISSING_BLOCK_LABEL_1271;
            iterator1;
            for(exception1 = lifeCycleListeners.iterator(); exception1.hasNext();)
            {
                connectionlifecyclelistener3 = (ConnectionLifeCycleListener)exception1.next();
                try
                {
                    connectionlifecyclelistener3.onDisconnect();
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception4)
                {
                    TwitterStreamImpl.logger.warn(exception4.getMessage());
                }
            }

            throw iterator1;
            for(iterator1 = lifeCycleListeners.iterator(); iterator1.hasNext();)
            {
                exception1 = (ConnectionLifeCycleListener)iterator1.next();
                try
                {
                    exception1.onCleanUp();
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception1)
                {
                    TwitterStreamImpl.logger.warn(exception1.getMessage());
                }
            }

            return;
            if(true) goto _L14; else goto _L13
_L13:
        }

        void updateListeners()
        {
            static class _cls11
            {

                static final int $SwitchMap$twitter4j$TwitterStreamImpl$Mode[];

                static 
                {
                    $SwitchMap$twitter4j$TwitterStreamImpl$Mode = new int[Mode.values().length];
                    try
                    {
                        $SwitchMap$twitter4j$TwitterStreamImpl$Mode[Mode.site.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                }
            }

            _cls11..SwitchMap.twitter4j.TwitterStreamImpl.Mode[mode.ordinal()];
            JVM INSTR tableswitch 1 1: default 28
        //                       1 51;
               goto _L1 _L2
_L1:
            streamListeners = getStatusListeners();
_L4:
            rawStreamListeners = getRawStreamListeners();
            return;
_L2:
            streamListeners = getSiteStreamsListeners();
            if(true) goto _L4; else goto _L3
_L3:
        }

        private final String NAME;
        private volatile boolean closed;
        private final Mode mode;
        private RawStreamListener rawStreamListeners[];
        private StatusStreamBase stream;
        private StreamListener streamListeners[];
        final TwitterStreamImpl this$0;

        TwitterStreamConsumer(Mode mode1)
        {
            this$0 = TwitterStreamImpl.this;
            super();
            stream = null;
            closed = false;
            mode = mode1;
            NAME = String.format("Twitter Stream consumer / %s [%s]", new Object[] {
                conf.getStreamThreadName(), Integer.valueOf(int i = 
// JavaClassFileOutputException: get_constant: invalid tag
    }


    TwitterStreamImpl(Configuration configuration, Authorization authorization)
    {
        super(configuration, authorization);
        handler = null;
        http = HttpClientFactory.getInstance(new StreamingReadTimeoutConfiguration(configuration));
        http.addDefaultRequestHeader("Connection", "close");
        StringBuilder stringbuilder = (new StringBuilder()).append("stall_warnings=");
        if(configuration.isStallWarningsEnabled())
            authorization = "true";
        else
            authorization = "false";
        stallWarningsGetParam = stringbuilder.append(authorization).toString();
        stallWarningsParam = new HttpParameter("stall_warnings", configuration.isStallWarningsEnabled());
    }

    private void ensureSiteStreamsListenerIsSet()
    {
        if(getSiteStreamsListeners().length == 0 && getRawStreamListeners().length == 0)
            throw new IllegalStateException("SiteStreamsListener is not set.");
        else
            return;
    }

    private void ensureStatusStreamListenerIsSet()
    {
        if(streamListeners.size() == 0)
            throw new IllegalStateException("StatusListener is not set.");
        else
            return;
    }

    private StatusStream getCountStream(String s, int i)
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        try
        {
            Dispatcher dispatcher1 = getDispatcher();
            HttpClient httpclient = http;
            s = (new StringBuilder()).append(conf.getStreamBaseURL()).append(s).toString();
            HttpParameter httpparameter = new HttpParameter("count", String.valueOf(i));
            HttpParameter httpparameter1 = stallWarningsParam;
            Authorization authorization = auth;
            s = new StatusStreamImpl(dispatcher1, httpclient.post(s, new HttpParameter[] {
                httpparameter, httpparameter1
            }, authorization, null), conf);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new TwitterException(s);
        }
        return s;
    }

    private Dispatcher getDispatcher()
    {
        if(dispatcher != null) goto _L2; else goto _L1
_L1:
        twitter4j/TwitterStreamImpl;
        JVM INSTR monitorenter ;
        if(dispatcher == null)
            dispatcher = (new DispatcherFactory(conf)).getInstance();
        twitter4j/TwitterStreamImpl;
        JVM INSTR monitorexit ;
_L2:
        return dispatcher;
        Exception exception;
        exception;
        twitter4j/TwitterStreamImpl;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private RawStreamListener[] getRawStreamListeners()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = streamListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            StreamListener streamlistener = (StreamListener)iterator.next();
            if(streamlistener instanceof RawStreamListener)
                arraylist.add((RawStreamListener)streamlistener);
        } while(true);
        return (RawStreamListener[])arraylist.toArray(new RawStreamListener[arraylist.size()]);
    }

    private SiteStreamsListener[] getSiteStreamsListeners()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = streamListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            StreamListener streamlistener = (StreamListener)iterator.next();
            if(streamlistener instanceof SiteStreamsListener)
                arraylist.add((SiteStreamsListener)streamlistener);
        } while(true);
        return (SiteStreamsListener[])arraylist.toArray(new SiteStreamsListener[arraylist.size()]);
    }

    private StatusListener[] getStatusListeners()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = streamListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            StreamListener streamlistener = (StreamListener)iterator.next();
            if(streamlistener instanceof StatusListener)
                arraylist.add((StatusListener)streamlistener);
        } while(true);
        return (StatusListener[])arraylist.toArray(new StatusListener[arraylist.size()]);
    }

    private void startHandler(TwitterStreamConsumer twitterstreamconsumer)
    {
        this;
        JVM INSTR monitorenter ;
        cleanUp();
        handler = twitterstreamconsumer;
        handler.start();
        numberOfHandlers++;
        this;
        JVM INSTR monitorexit ;
        return;
        twitterstreamconsumer;
        throw twitterstreamconsumer;
    }

    private void updateListeners()
    {
        this;
        JVM INSTR monitorenter ;
        if(handler != null)
            handler.updateListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void addConnectionLifeCycleListener(ConnectionLifeCycleListener connectionlifecyclelistener)
    {
        lifeCycleListeners.add(connectionlifecyclelistener);
    }

    public void addListener(StreamListener streamlistener)
    {
        this;
        JVM INSTR monitorenter ;
        streamListeners.add(streamlistener);
        updateListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        streamlistener;
        throw streamlistener;
    }

    public void cleanUp()
    {
        this;
        JVM INSTR monitorenter ;
        if(handler != null)
        {
            handler.close();
            numberOfHandlers--;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void clearListeners()
    {
        this;
        JVM INSTR monitorenter ;
        streamListeners.clear();
        updateListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            if(!super.equals(obj))
                return false;
            obj = (TwitterStreamImpl)obj;
            if(handler == null ? ((TwitterStreamImpl) (obj)).handler != null : !handler.equals(((TwitterStreamImpl) (obj)).handler))
                return false;
            if(http == null ? ((TwitterStreamImpl) (obj)).http != null : !http.equals(((TwitterStreamImpl) (obj)).http))
                return false;
            if(lifeCycleListeners == null ? ((TwitterStreamImpl) (obj)).lifeCycleListeners != null : !lifeCycleListeners.equals(((TwitterStreamImpl) (obj)).lifeCycleListeners))
                return false;
            if(stallWarningsGetParam == null ? ((TwitterStreamImpl) (obj)).stallWarningsGetParam != null : !stallWarningsGetParam.equals(((TwitterStreamImpl) (obj)).stallWarningsGetParam))
                return false;
            if(stallWarningsParam == null ? ((TwitterStreamImpl) (obj)).stallWarningsParam != null : !stallWarningsParam.equals(((TwitterStreamImpl) (obj)).stallWarningsParam))
                return false;
            if(streamListeners == null ? ((TwitterStreamImpl) (obj)).streamListeners != null : !streamListeners.equals(((TwitterStreamImpl) (obj)).streamListeners))
                return false;
        }
        return true;
    }

    public void filter(FilterQuery filterquery)
    {
        ensureAuthorizationEnabled();
        ensureStatusStreamListenerIsSet();
        startHandler(new TwitterStreamConsumer(filterquery) {

            public StatusStream getStream()
                throws TwitterException
            {
                return getFilterStream(query);
            }

            final TwitterStreamImpl this$0;
            final FilterQuery val$query;

            
            {
                this$0 = TwitterStreamImpl.this;
                query = filterquery;
                TwitterStreamConsumer(final_mode);
            }
        }
);
    }

    public transient void filter(String as[])
    {
        filter((new FilterQuery()).track(as));
    }

    public void firehose(int i)
    {
        ensureAuthorizationEnabled();
        ensureStatusStreamListenerIsSet();
        startHandler(new TwitterStreamConsumer(i) {

            public StatusStream getStream()
                throws TwitterException
            {
                return getFirehoseStream(count);
            }

            final TwitterStreamImpl this$0;
            final int val$count;

            
            {
                this$0 = TwitterStreamImpl.this;
                count = i;
                TwitterStreamConsumer(final_mode);
            }
        }
);
    }

    StatusStream getFilterStream(FilterQuery filterquery)
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        try
        {
            filterquery = new StatusStreamImpl(getDispatcher(), http.post((new StringBuilder()).append(conf.getStreamBaseURL()).append("statuses/filter.json").toString(), filterquery.asHttpParameterArray(stallWarningsParam), auth, null), conf);
        }
        // Misplaced declaration of an exception variable
        catch(FilterQuery filterquery)
        {
            throw new TwitterException(filterquery);
        }
        return filterquery;
    }

    StatusStream getFirehoseStream(int i)
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        return getCountStream("statuses/firehose.json", i);
    }

    StatusStream getLinksStream(int i)
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        return getCountStream("statuses/links.json", i);
    }

    StatusStream getRetweetStream()
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        Object obj;
        try
        {
            obj = getDispatcher();
            HttpClient httpclient = http;
            String s = (new StringBuilder()).append(conf.getStreamBaseURL()).append("statuses/retweet.json").toString();
            HttpParameter httpparameter = stallWarningsParam;
            Authorization authorization = auth;
            obj = new StatusStreamImpl(((Dispatcher) (obj)), httpclient.post(s, new HttpParameter[] {
                httpparameter
            }, authorization, null), conf);
        }
        catch(IOException ioexception)
        {
            throw new TwitterException(ioexception);
        }
        return ((StatusStream) (obj));
    }

    StatusStream getSampleStream()
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        StatusStreamImpl statusstreamimpl;
        try
        {
            statusstreamimpl = new StatusStreamImpl(getDispatcher(), http.get((new StringBuilder()).append(conf.getStreamBaseURL()).append("statuses/sample.json?").append(stallWarningsGetParam).toString(), null, auth, null), conf);
        }
        catch(IOException ioexception)
        {
            throw new TwitterException(ioexception);
        }
        return statusstreamimpl;
    }

    StatusStream getSampleStream(String s)
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        try
        {
            s = new StatusStreamImpl(getDispatcher(), http.get((new StringBuilder()).append(conf.getStreamBaseURL()).append("statuses/sample.json?").append(stallWarningsGetParam).append("&language=").append(s).toString(), null, auth, null), conf);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new TwitterException(s);
        }
        return s;
    }

    InputStream getSiteStream(boolean flag, long al[])
        throws TwitterException
    {
        ensureOAuthEnabled();
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(conf.getSiteStreamBaseURL()).append("site.json").toString();
        Object obj;
        HttpParameter httpparameter;
        Authorization authorization;
        if(flag)
            obj = "followings";
        else
            obj = "user";
        obj = new HttpParameter("with", ((String) (obj)));
        al = new HttpParameter("follow", StringUtil.join(al));
        httpparameter = stallWarningsParam;
        authorization = auth;
        return httpclient.post(s, new HttpParameter[] {
            obj, al, httpparameter
        }, authorization, null).asStream();
    }

    UserStream getUserStream(String as[])
        throws TwitterException
    {
        ensureAuthorizationEnabled();
        ArrayList arraylist;
        try
        {
            arraylist = new ArrayList();
            arraylist.add(stallWarningsParam);
            if(conf.isUserStreamRepliesAllEnabled())
                arraylist.add(new HttpParameter("replies", "all"));
            if(!conf.isUserStreamWithFollowingsEnabled())
                arraylist.add(new HttpParameter("with", "user"));
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw new TwitterException(as);
        }
        if(as == null)
            break MISSING_BLOCK_LABEL_112;
        arraylist.add(new HttpParameter("track", StringUtil.join(as)));
        as = new UserStreamImpl(getDispatcher(), http.post((new StringBuilder()).append(conf.getUserStreamBaseURL()).append("user.json").toString(), (HttpParameter[])arraylist.toArray(new HttpParameter[arraylist.size()]), auth, null), conf);
        return as;
    }

    public int hashCode()
    {
        int j1 = 0;
        int k1 = super.hashCode();
        int i;
        int j;
        int k;
        int l;
        int i1;
        if(http != null)
            i = http.hashCode();
        else
            i = 0;
        if(lifeCycleListeners != null)
            j = lifeCycleListeners.hashCode();
        else
            j = 0;
        if(handler != null)
            k = handler.hashCode();
        else
            k = 0;
        if(stallWarningsGetParam != null)
            l = stallWarningsGetParam.hashCode();
        else
            l = 0;
        if(stallWarningsParam != null)
            i1 = stallWarningsParam.hashCode();
        else
            i1 = 0;
        if(streamListeners != null)
            j1 = streamListeners.hashCode();
        return (((((k1 * 31 + i) * 31 + j) * 31 + k) * 31 + l) * 31 + i1) * 31 + j1;
    }

    public void links(int i)
    {
        ensureAuthorizationEnabled();
        ensureStatusStreamListenerIsSet();
        startHandler(new TwitterStreamConsumer(i) {

            public StatusStream getStream()
                throws TwitterException
            {
                return getLinksStream(count);
            }

            final TwitterStreamImpl this$0;
            final int val$count;

            
            {
                this$0 = TwitterStreamImpl.this;
                count = i;
                TwitterStreamConsumer(final_mode);
            }
        }
);
    }

    public TwitterStream onException(final Consumer action)
    {
        this;
        JVM INSTR monitorenter ;
        streamListeners.add(new StatusAdapter() {

            public void onException(Exception exception)
            {
                action.accept(exception);
            }

            final TwitterStreamImpl this$0;
            final Consumer val$action;

            
            {
                this$0 = TwitterStreamImpl.this;
                action = consumer;
                StatusAdapter();
            }
        }
);
        updateListeners();
        this;
        JVM INSTR monitorexit ;
        return this;
        action;
        throw action;
    }

    public TwitterStream onStatus(final Consumer action)
    {
        this;
        JVM INSTR monitorenter ;
        streamListeners.add(new StatusAdapter() {

            public void onStatus(Status status)
            {
                action.accept(status);
            }

            final TwitterStreamImpl this$0;
            final Consumer val$action;

            
            {
                this$0 = TwitterStreamImpl.this;
                action = consumer;
                StatusAdapter();
            }
        }
);
        updateListeners();
        this;
        JVM INSTR monitorexit ;
        return this;
        action;
        throw action;
    }

    public void removeListener(StreamListener streamlistener)
    {
        this;
        JVM INSTR monitorenter ;
        streamListeners.remove(streamlistener);
        updateListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        streamlistener;
        throw streamlistener;
    }

    public void replaceListener(StreamListener streamlistener, StreamListener streamlistener1)
    {
        this;
        JVM INSTR monitorenter ;
        streamListeners.remove(streamlistener);
        streamListeners.add(streamlistener1);
        updateListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        streamlistener;
        throw streamlistener;
    }

    public void retweet()
    {
        ensureAuthorizationEnabled();
        ensureStatusStreamListenerIsSet();
        startHandler(new TwitterStreamConsumer(Mode.status) {

            public StatusStream getStream()
                throws TwitterException
            {
                return getRetweetStream();
            }

            final TwitterStreamImpl this$0;

            
            {
                this$0 = TwitterStreamImpl.this;
                TwitterStreamConsumer(mode);
            }
        }
);
    }

    public void sample()
    {
        ensureAuthorizationEnabled();
        ensureStatusStreamListenerIsSet();
        startHandler(new TwitterStreamConsumer(Mode.status) {

            public StatusStream getStream()
                throws TwitterException
            {
                return getSampleStream();
            }

            final TwitterStreamImpl this$0;

            
            {
                this$0 = TwitterStreamImpl.this;
                TwitterStreamConsumer(mode);
            }
        }
);
    }

    public void sample(String s)
    {
        ensureAuthorizationEnabled();
        ensureStatusStreamListenerIsSet();
        startHandler(new TwitterStreamConsumer(s) {

            public StatusStream getStream()
                throws TwitterException
            {
                return getSampleStream(language);
            }

            final TwitterStreamImpl this$0;
            final String val$language;

            
            {
                this$0 = TwitterStreamImpl.this;
                language = s;
                TwitterStreamConsumer(final_mode);
            }
        }
);
    }

    public void shutdown()
    {
        this;
        JVM INSTR monitorenter ;
        cleanUp();
        twitter4j/TwitterStreamImpl;
        JVM INSTR monitorenter ;
        if(numberOfHandlers == 0 && dispatcher != null)
        {
            dispatcher.shutdown();
            dispatcher = null;
        }
        twitter4j/TwitterStreamImpl;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        twitter4j/TwitterStreamImpl;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public StreamController site(boolean flag, long al[])
    {
        ensureOAuthEnabled();
        ensureSiteStreamsListenerIsSet();
        StreamController streamcontroller = new StreamController(http, auth);
        startHandler(new TwitterStreamConsumer(streamcontroller) {

            public StatusStream getStream()
                throws TwitterException
            {
                SiteStreamsImpl sitestreamsimpl;
                try
                {
                    sitestreamsimpl = new SiteStreamsImpl(getDispatcher(), getSiteStream(withFollowings, follow), conf, cs);
                }
                catch(IOException ioexception)
                {
                    throw new TwitterException(ioexception);
                }
                return sitestreamsimpl;
            }

            final TwitterStreamImpl this$0;
            final StreamController val$cs;
            final long val$follow[];
            final boolean val$withFollowings;

            
            {
                this$0 = TwitterStreamImpl.this;
                withFollowings = flag;
                follow = al;
                cs = streamcontroller;
                TwitterStreamConsumer(final_mode);
            }
        }
);
        return streamcontroller;
    }

    public String toString()
    {
        return (new StringBuilder()).append("TwitterStreamImpl{http=").append(http).append(", lifeCycleListeners=").append(lifeCycleListeners).append(", handler=").append(handler).append(", stallWarningsGetParam='").append(stallWarningsGetParam).append('\'').append(", stallWarningsParam=").append(stallWarningsParam).append(", streamListeners=").append(streamListeners).append('}').toString();
    }

    public void user()
    {
        user(null);
    }

    public void user(String as[])
    {
        ensureAuthorizationEnabled();
        ensureStatusStreamListenerIsSet();
        startHandler(new TwitterStreamConsumer(as) {

            public StatusStream getStream()
                throws TwitterException
            {
                return getUserStream(track);
            }

            final TwitterStreamImpl this$0;
            final String val$track[];

            
            {
                this$0 = TwitterStreamImpl.this;
                track = as;
                TwitterStreamConsumer(final_mode);
            }
        }
);
    }

    private static final int HTTP_ERROR_INITIAL_WAIT = 10000;
    private static final int HTTP_ERROR_WAIT_CAP = 0x3a980;
    private static final int NO_WAIT = 0;
    private static final int TCP_ERROR_INITIAL_WAIT = 250;
    private static final int TCP_ERROR_WAIT_CAP = 16000;
    private static int count = 0;
    private static volatile transient Dispatcher dispatcher;
    private static final Logger logger = Logger.getLogger(twitter4j/TwitterStreamImpl);
    private static int numberOfHandlers = 0;
    private static final long serialVersionUID = 0xb7228bd8L;
    private TwitterStreamConsumer handler;
    private final HttpClient http;
    private final List lifeCycleListeners = new ArrayList(0);
    private final String stallWarningsGetParam;
    private final HttpParameter stallWarningsParam;
    private final ArrayList streamListeners = new ArrayList(0);

    static 
    {
        numberOfHandlers = 0;
        count = 0;
    }



/*
    static int access$104()
    {
        int i = count + 1;
        count = i;
        return i;
    }

*/





}
