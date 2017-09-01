// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.util.concurrent.*;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            Dispatcher, Logger

final class DispatcherImpl
    implements Dispatcher
{

    public DispatcherImpl(final Configuration conf)
    {
        executorService = Executors.newFixedThreadPool(conf.getAsyncNumThreads(), new ThreadFactory() {

            public Thread newThread(Runnable runnable)
            {
                runnable = new Thread(runnable);
                int i = count;
                count = i + 1;
                runnable.setName(String.format("Twitter4J Async Dispatcher[%d]", new Object[] {
                    Integer.valueOf(i)
                }));
                runnable.setDaemon(conf.isDaemonEnabled());
                return runnable;
            }

            int count;
            final DispatcherImpl this$0;
            final Configuration val$conf;

            
            {
                this$0 = DispatcherImpl.this;
                conf = configuration;
                super();
                count = 0;
            }
        }
);
        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run()
            {
                executorService.shutdown();
            }

            final DispatcherImpl this$0;

            
            {
                this$0 = DispatcherImpl.this;
                super();
            }
        }
);
    }

    public void invokeLater(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        executorService.execute(runnable);
        this;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    public void shutdown()
    {
        this;
        JVM INSTR monitorenter ;
        executorService.shutdown();
        if(!executorService.awaitTermination(5000L, TimeUnit.MILLISECONDS))
            executorService.shutdownNow();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        logger.warn(((InterruptedException) (obj)).getMessage());
          goto _L1
        obj;
        throw obj;
    }

    private static final long SHUTDOWN_TIME = 5000L;
    private static final Logger logger = Logger.getLogger(twitter4j/DispatcherImpl);
    private final ExecutorService executorService;


}
