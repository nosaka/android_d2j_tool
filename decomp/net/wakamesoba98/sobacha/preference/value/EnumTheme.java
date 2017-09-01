// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.value;


public final class EnumTheme extends Enum
{

    private EnumTheme(String s, int i)
    {
        super(s, i);
    }

    public static EnumTheme valueOf(String s)
    {
        return (EnumTheme)Enum.valueOf(net/wakamesoba98/sobacha/preference/value/EnumTheme, s);
    }

    public static EnumTheme[] values()
    {
        return (EnumTheme[])$VALUES.clone();
    }

    private static final EnumTheme $VALUES[];
    public static final EnumTheme DARK;
    public static final EnumTheme LIGHT;

    static 
    {
        LIGHT = new EnumTheme("LIGHT", 0);
        DARK = new EnumTheme("DARK", 1);
        $VALUES = (new EnumTheme[] {
            LIGHT, DARK
        });
    }
}
