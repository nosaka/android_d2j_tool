// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.conf;


// Referenced classes of package twitter4j.conf:
//            ConfigurationFactory, Configuration

public final class ConfigurationContext
{

    public ConfigurationContext()
    {
    }

    public static Configuration getInstance()
    {
        return factory.getInstance();
    }

    public static Configuration getInstance(String s)
    {
        return factory.getInstance(s);
    }

    private static final String CONFIGURATION_IMPL = "twitter4j.configurationFactory";
    private static final String DEFAULT_CONFIGURATION_FACTORY = "twitter4j.conf.PropertyConfigurationFactory";
    private static final ConfigurationFactory factory;

    static 
    {
        String s;
        try
        {
            s = System.getProperty("twitter4j.configurationFactory", "twitter4j.conf.PropertyConfigurationFactory");
        }
        catch(SecurityException securityexception)
        {
            securityexception = "twitter4j.conf.PropertyConfigurationFactory";
        }
        try
        {
            factory = (ConfigurationFactory)Class.forName(s).newInstance();
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new AssertionError(classnotfoundexception);
        }
        catch(InstantiationException instantiationexception)
        {
            throw new AssertionError(instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new AssertionError(illegalaccessexception);
        }
    }
}
