// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            LoggerFactory, StdOutLogger, Logger

final class StdOutLoggerFactory extends LoggerFactory
{

    StdOutLoggerFactory()
    {
    }

    public Logger getLogger(Class class1)
    {
        return SINGLETON;
    }

    private static final Logger SINGLETON = new StdOutLogger();

}
