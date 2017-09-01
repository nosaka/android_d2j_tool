// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.conf;


// Referenced classes of package twitter4j.conf:
//            ConfigurationFactory, PropertyConfiguration, Configuration

class PropertyConfigurationFactory
    implements ConfigurationFactory
{

    PropertyConfigurationFactory()
    {
    }

    public void dispose()
    {
    }

    public Configuration getInstance()
    {
        return ROOT_CONFIGURATION;
    }

    public Configuration getInstance(String s)
    {
        s = new PropertyConfiguration(s);
        s.dumpConfiguration();
        return s;
    }

    private static final PropertyConfiguration ROOT_CONFIGURATION = new PropertyConfiguration();

}
