// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.conf;


// Referenced classes of package twitter4j.conf:
//            Configuration

public interface ConfigurationFactory
{

    public abstract void dispose();

    public abstract Configuration getInstance();

    public abstract Configuration getInstance(String s);
}
