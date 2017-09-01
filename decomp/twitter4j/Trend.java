// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

public interface Trend
    extends Serializable
{

    public abstract String getName();

    public abstract String getQuery();

    public abstract String getURL();
}
