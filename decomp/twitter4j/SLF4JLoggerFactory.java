// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import org.slf4j.LoggerFactory;

// Referenced classes of package twitter4j:
//            LoggerFactory, SLF4JLogger, Logger

final class SLF4JLoggerFactory extends twitter4j.LoggerFactory
{

    SLF4JLoggerFactory()
    {
    }

    public Logger getLogger(Class class1)
    {
        return new SLF4JLogger(LoggerFactory.getLogger(class1));
    }
}
