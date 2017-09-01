// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.key;


public final class EnumValueType extends Enum
{

    private EnumValueType(String s, int i)
    {
        super(s, i);
    }

    public static EnumValueType valueOf(String s)
    {
        return (EnumValueType)Enum.valueOf(net/wakamesoba98/sobacha/preference/key/EnumValueType, s);
    }

    public static EnumValueType[] values()
    {
        return (EnumValueType[])$VALUES.clone();
    }

    private static final EnumValueType $VALUES[];
    public static final EnumValueType BOOLEAN;
    public static final EnumValueType INTEGER;
    public static final EnumValueType LONG;
    public static final EnumValueType STRING;

    static 
    {
        BOOLEAN = new EnumValueType("BOOLEAN", 0);
        INTEGER = new EnumValueType("INTEGER", 1);
        LONG = new EnumValueType("LONG", 2);
        STRING = new EnumValueType("STRING", 3);
        $VALUES = (new EnumValueType[] {
            BOOLEAN, INTEGER, LONG, STRING
        });
    }
}
