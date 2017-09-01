// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;


public class DynamicUtil
{

    public DynamicUtil()
    {
    }

    public static byte safeUnbox(Byte byte1)
    {
        if(byte1 == null)
            return 0;
        else
            return byte1.byteValue();
    }

    public static char safeUnbox(Character character)
    {
        if(character == null)
            return '\0';
        else
            return character.charValue();
    }

    public static double safeUnbox(Double double1)
    {
        if(double1 == null)
            return 0.0D;
        else
            return double1.doubleValue();
    }

    public static float safeUnbox(Float float1)
    {
        if(float1 == null)
            return 0.0F;
        else
            return float1.floatValue();
    }

    public static int safeUnbox(Integer integer)
    {
        if(integer == null)
            return 0;
        else
            return integer.intValue();
    }

    public static long safeUnbox(Long long1)
    {
        if(long1 == null)
            return 0L;
        else
            return long1.longValue();
    }

    public static short safeUnbox(Short short1)
    {
        if(short1 == null)
            return 0;
        else
            return short1.shortValue();
    }

    public static boolean safeUnbox(Boolean boolean1)
    {
        if(boolean1 == null)
            return false;
        else
            return boolean1.booleanValue();
    }
}
