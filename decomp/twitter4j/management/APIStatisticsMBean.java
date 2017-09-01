// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.management;

import java.util.Map;

// Referenced classes of package twitter4j.management:
//            InvocationStatistics

public interface APIStatisticsMBean
    extends InvocationStatistics
{

    public abstract Iterable getInvocationStatistics();

    public abstract Map getMethodLevelSummariesAsString();

    public abstract String getMethodLevelSummary(String s);
}
