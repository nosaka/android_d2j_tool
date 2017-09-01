// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import org.apache.commons.logging.LogFactory;

// Referenced classes of package twitter4j:
//            LoggerFactory, CommonsLoggingLogger, Logger

final class CommonsLoggingLoggerFactory extends LoggerFactory
{

    CommonsLoggingLoggerFactory()
    {
    }

    public Logger getLogger(Class class1)
    {
        return new CommonsLoggingLogger(LogFactory.getLog(class1));
    }
}
