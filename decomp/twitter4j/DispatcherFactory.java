// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

// Referenced classes of package twitter4j:
//            Dispatcher

final class DispatcherFactory
{

    public DispatcherFactory()
    {
        this(ConfigurationContext.getInstance());
    }

    public DispatcherFactory(Configuration configuration)
    {
        dispatcherImpl = configuration.getDispatcherImpl();
        conf = configuration;
    }

    public Dispatcher getInstance()
    {
        Dispatcher dispatcher;
        try
        {
            dispatcher = (Dispatcher)Class.forName(dispatcherImpl).getConstructor(new Class[] {
                twitter4j/conf/Configuration
            }).newInstance(new Object[] {
                conf
            });
        }
        catch(InstantiationException instantiationexception)
        {
            throw new AssertionError(instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new AssertionError(illegalaccessexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new AssertionError(classnotfoundexception);
        }
        catch(ClassCastException classcastexception)
        {
            throw new AssertionError(classcastexception);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new AssertionError(nosuchmethodexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new AssertionError(invocationtargetexception);
        }
        return dispatcher;
    }

    private final Configuration conf;
    private final String dispatcherImpl;
}
