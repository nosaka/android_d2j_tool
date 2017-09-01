// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.core;


public final class LoadMode extends Enum
{

    private LoadMode(String s, int i)
    {
        super(s, i);
    }

    public static LoadMode valueOf(String s)
    {
        return (LoadMode)Enum.valueOf(net/wakamesoba98/sobacha/core/LoadMode, s);
    }

    public static LoadMode[] values()
    {
        return (LoadMode[])$VALUES.clone();
    }

    private static final LoadMode $VALUES[];
    public static final LoadMode FORCE_ALL;
    public static final LoadMode LOAD_ONCE;
    public static final LoadMode ONLY_MUTE;
    public static final LoadMode ONLY_STREAM;
    public static final LoadMode ONLY_TOKEN;

    static 
    {
        LOAD_ONCE = new LoadMode("LOAD_ONCE", 0);
        ONLY_TOKEN = new LoadMode("ONLY_TOKEN", 1);
        ONLY_MUTE = new LoadMode("ONLY_MUTE", 2);
        ONLY_STREAM = new LoadMode("ONLY_STREAM", 3);
        FORCE_ALL = new LoadMode("FORCE_ALL", 4);
        $VALUES = (new LoadMode[] {
            LOAD_ONCE, ONLY_TOKEN, ONLY_MUTE, ONLY_STREAM, FORCE_ALL
        });
    }
}
