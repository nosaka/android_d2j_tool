// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.management;


public interface InvocationStatistics
{

    public abstract long getAverageTime();

    public abstract long getCallCount();

    public abstract long getErrorCount();

    public abstract String getName();

    public abstract long getTotalTime();

    public abstract void reset();
}
