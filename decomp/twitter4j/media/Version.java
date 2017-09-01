// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.media;

import java.io.PrintStream;

public final class Version
{

    private Version()
    {
        throw new AssertionError();
    }

    public static String getVersion()
    {
        return "4.0.5";
    }

    public static void main(String args[])
    {
        System.out.println("Twitter4J Media support 4.0.5");
    }

    private static final String TITLE = "Twitter4J Media support";
    private static final String VERSION = "4.0.5";
}
