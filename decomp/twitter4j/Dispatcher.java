// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


public interface Dispatcher
{

    public abstract void invokeLater(Runnable runnable);

    public abstract void shutdown();
}
