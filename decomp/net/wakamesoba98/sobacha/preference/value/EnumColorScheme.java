// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.value;


public final class EnumColorScheme extends Enum
{

    private EnumColorScheme(String s, int i)
    {
        super(s, i);
    }

    public static EnumColorScheme valueOf(String s)
    {
        return (EnumColorScheme)Enum.valueOf(net/wakamesoba98/sobacha/preference/value/EnumColorScheme, s);
    }

    public static EnumColorScheme[] values()
    {
        return (EnumColorScheme[])$VALUES.clone();
    }

    private static final EnumColorScheme $VALUES[];
    public static final EnumColorScheme CLASSIC;
    public static final EnumColorScheme DEFAULT;
    public static final EnumColorScheme HIGH_CONTRAST;

    static 
    {
        DEFAULT = new EnumColorScheme("DEFAULT", 0);
        CLASSIC = new EnumColorScheme("CLASSIC", 1);
        HIGH_CONTRAST = new EnumColorScheme("HIGH_CONTRAST", 2);
        $VALUES = (new EnumColorScheme[] {
            DEFAULT, CLASSIC, HIGH_CONTRAST
        });
    }
}
