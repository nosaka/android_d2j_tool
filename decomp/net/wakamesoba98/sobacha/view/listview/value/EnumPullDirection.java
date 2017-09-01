// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.value;


public final class EnumPullDirection extends Enum
{

    private EnumPullDirection(String s, int i)
    {
        super(s, i);
    }

    public static EnumPullDirection valueOf(String s)
    {
        return (EnumPullDirection)Enum.valueOf(net/wakamesoba98/sobacha/view/listview/value/EnumPullDirection, s);
    }

    public static EnumPullDirection[] values()
    {
        return (EnumPullDirection[])$VALUES.clone();
    }

    private static final EnumPullDirection $VALUES[];
    public static final EnumPullDirection DOWN;
    public static final EnumPullDirection UP;

    static 
    {
        DOWN = new EnumPullDirection("DOWN", 0);
        UP = new EnumPullDirection("UP", 1);
        $VALUES = (new EnumPullDirection[] {
            DOWN, UP
        });
    }
}
