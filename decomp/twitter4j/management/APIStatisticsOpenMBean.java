// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.management;

import java.util.Iterator;
import javax.management.*;
import javax.management.openmbean.*;

// Referenced classes of package twitter4j.management:
//            APIStatisticsMBean, InvocationStatistics, APIStatistics

public class APIStatisticsOpenMBean
    implements DynamicMBean
{

    public APIStatisticsOpenMBean(APIStatistics apistatistics)
    {
        API_STATISTICS = apistatistics;
        try
        {
            METHOD_STATS_TYPE = new CompositeType("method statistics", "method statistics", ITEM_NAMES, ITEM_DESCRIPTIONS, ITEM_TYPES);
            API_STATISTICS_TYPE = new TabularType("API statistics", "list of methods", METHOD_STATS_TYPE, new String[] {
                "methodName"
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch(APIStatistics apistatistics)
        {
            throw new RuntimeException(apistatistics);
        }
    }

    public Object getAttribute(String s)
        throws AttributeNotFoundException, MBeanException, ReflectionException
    {
        if(s.equals("statisticsTable"))
            return getStatistics();
        if(s.equals("callCount"))
            return Long.valueOf(API_STATISTICS.getCallCount());
        if(s.equals("errorCount"))
            return Long.valueOf(API_STATISTICS.getErrorCount());
        if(s.equals("totalTime"))
            return Long.valueOf(API_STATISTICS.getTotalTime());
        if(s.equals("averageTime"))
            return Long.valueOf(API_STATISTICS.getAverageTime());
        else
            throw new AttributeNotFoundException((new StringBuilder()).append("Cannot find ").append(s).append(" attribute ").toString());
    }

    public AttributeList getAttributes(String as[])
    {
        AttributeList attributelist = new AttributeList();
        if(as.length != 0)
        {
            int j = as.length;
            int i = 0;
            while(i < j) 
            {
                String s = as[i];
                try
                {
                    attributelist.add(new Attribute(s, getAttribute(s)));
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                i++;
            }
        }
        return attributelist;
    }

    public MBeanInfo getMBeanInfo()
    {
        OpenMBeanAttributeInfoSupport aopenmbeanattributeinfosupport[] = new OpenMBeanAttributeInfoSupport[5];
        int j = 0 + 1;
        aopenmbeanattributeinfosupport[0] = new OpenMBeanAttributeInfoSupport("callCount", "Total number of API calls", SimpleType.LONG, true, false, false);
        int i = j + 1;
        aopenmbeanattributeinfosupport[j] = new OpenMBeanAttributeInfoSupport("errorCount", "The number of failed API calls", SimpleType.LONG, true, false, false);
        j = i + 1;
        aopenmbeanattributeinfosupport[i] = new OpenMBeanAttributeInfoSupport("averageTime", "Average time spent invoking any API method", SimpleType.LONG, true, false, false);
        i = j + 1;
        aopenmbeanattributeinfosupport[j] = new OpenMBeanAttributeInfoSupport("totalTime", "Average time spent invoking any API method", SimpleType.LONG, true, false, false);
        aopenmbeanattributeinfosupport[i] = new OpenMBeanAttributeInfoSupport("statisticsTable", "Table of statisics for all API methods", API_STATISTICS_TYPE, true, false, false);
        OpenMBeanConstructorInfoSupport openmbeanconstructorinfosupport = new OpenMBeanConstructorInfoSupport("APIStatisticsOpenMBean", "Constructs an APIStatisticsOpenMBean instance", new OpenMBeanParameterInfoSupport[0]);
        Object obj = SimpleType.VOID;
        obj = new OpenMBeanOperationInfoSupport("reset", "reset the statistics", new OpenMBeanParameterInfoSupport[0], ((OpenType) (obj)), 0);
        return new OpenMBeanInfoSupport(getClass().getName(), "API Statistics Open MBean", aopenmbeanattributeinfosupport, new OpenMBeanConstructorInfoSupport[] {
            openmbeanconstructorinfosupport
        }, new OpenMBeanOperationInfoSupport[] {
            obj
        }, new MBeanNotificationInfo[0]);
    }

    public TabularDataSupport getStatistics()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        Iterator iterator;
        obj = new TabularDataSupport(API_STATISTICS_TYPE);
        iterator = API_STATISTICS.getInvocationStatistics().iterator();
_L1:
        long l;
        long l1;
        long l2;
        long l3;
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_176;
        InvocationStatistics invocationstatistics = (InvocationStatistics)iterator.next();
        s = invocationstatistics.getName();
        l = invocationstatistics.getCallCount();
        l1 = invocationstatistics.getErrorCount();
        l2 = invocationstatistics.getTotalTime();
        l3 = invocationstatistics.getAverageTime();
        ((TabularDataSupport) (obj)).put(new CompositeDataSupport(METHOD_STATS_TYPE, ITEM_NAMES, new Object[] {
            s, Long.valueOf(l), Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l3)
        }));
          goto _L1
        obj;
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        this;
        JVM INSTR monitorexit ;
        return ((TabularDataSupport) (obj));
    }

    public Object invoke(String s, Object aobj[], String as[])
        throws MBeanException, ReflectionException
    {
        if(s.equals("reset"))
        {
            reset();
            return "Statistics reset";
        } else
        {
            throw new ReflectionException(new NoSuchMethodException(s), (new StringBuilder()).append("Cannot find the operation ").append(s).toString());
        }
    }

    public void reset()
    {
        API_STATISTICS.reset();
    }

    public void setAttribute(Attribute attribute)
        throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException
    {
        throw new AttributeNotFoundException("No attributes can be set in this MBean");
    }

    public AttributeList setAttributes(AttributeList attributelist)
    {
        return new AttributeList();
    }

    private static final String ITEM_DESCRIPTIONS[] = {
        "The method name", "The number of times this method has been called", "The number of calls that failed", "The total amount of time spent invoking this method in milliseconds", "The average amount of time spent invoking this method in milliseconds"
    };
    private static final String ITEM_NAMES[] = {
        "methodName", "callCount", "errorCount", "totalTime", "avgTime"
    };
    private static final OpenType ITEM_TYPES[];
    private final APIStatisticsMBean API_STATISTICS;
    private final TabularType API_STATISTICS_TYPE;
    private final CompositeType METHOD_STATS_TYPE;

    static 
    {
        ITEM_TYPES = (new OpenType[] {
            SimpleType.STRING, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG
        });
    }
}
