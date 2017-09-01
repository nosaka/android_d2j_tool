// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.util;


public final class CharacterUtil
{

    private CharacterUtil()
    {
        throw new AssertionError();
    }

    public static int count(String s)
    {
        return s.length();
    }

    public static boolean isExceedingLengthLimitation(String s)
    {
        return count(s) > 140;
    }
}
