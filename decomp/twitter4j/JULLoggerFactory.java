// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.util.logging.Logger;

// Referenced classes of package twitter4j:
//            LoggerFactory, JULLogger, Logger

final class JULLoggerFactory extends LoggerFactory
{

    JULLoggerFactory()
    {
    }

    public twitter4j.Logger getLogger(Class class1)
    {
        return new JULLogger(Logger.getLogger(class1.getName()));
    }
}
