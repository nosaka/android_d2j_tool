// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

public class GeoLocation
    implements Serializable
{

    GeoLocation()
    {
    }

    public GeoLocation(double d, double d1)
    {
        latitude = d;
        longitude = d1;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(!(obj instanceof GeoLocation))
                return false;
            obj = (GeoLocation)obj;
            if(Double.compare(((GeoLocation) (obj)).getLatitude(), latitude) != 0)
                return false;
            if(Double.compare(((GeoLocation) (obj)).getLongitude(), longitude) != 0)
                return false;
        }
        return true;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public int hashCode()
    {
        long l = Double.doubleToLongBits(latitude);
        int i = (int)(l >>> 32 ^ l);
        l = Double.doubleToLongBits(longitude);
        return i * 31 + (int)(l >>> 32 ^ l);
    }

    public String toString()
    {
        return (new StringBuilder()).append("GeoLocation{latitude=").append(latitude).append(", longitude=").append(longitude).append('}').toString();
    }

    private static final long serialVersionUID = 0xa0a774f5L;
    private double latitude;
    private double longitude;
}
