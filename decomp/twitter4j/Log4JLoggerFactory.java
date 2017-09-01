// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import org.apache.log4j.Logger;

// Referenced classes of package twitter4j:
//            LoggerFactory, Log4JLogger, Logger

final class Log4JLoggerFactory extends LoggerFactory
{

    Log4JLoggerFactory()
    {
    }

    public twitter4j.Logger getLogger(Class class1)
    {
        return new Log4JLogger(Logger.getLogger(class1));
    }
}
