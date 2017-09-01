// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

public interface Location
    extends Serializable
{

    public abstract String getCountryCode();

    public abstract String getCountryName();

    public abstract String getName();

    public abstract int getPlaceCode();

    public abstract String getPlaceName();

    public abstract String getURL();

    public abstract int getWoeid();
}
