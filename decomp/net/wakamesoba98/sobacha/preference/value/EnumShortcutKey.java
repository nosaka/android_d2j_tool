// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.value;


public final class EnumShortcutKey extends Enum
{

    private EnumShortcutKey(String s, int i)
    {
        super(s, i);
    }

    public static EnumShortcutKey valueOf(String s)
    {
        return (EnumShortcutKey)Enum.valueOf(net/wakamesoba98/sobacha/preference/value/EnumShortcutKey, s);
    }

    public static EnumShortcutKey[] values()
    {
        return (EnumShortcutKey[])$VALUES.clone();
    }

    private static final EnumShortcutKey $VALUES[];
    public static final EnumShortcutKey ENTER;
    public static final EnumShortcutKey NONE;
    public static final EnumShortcutKey SHIFT_ENTER;

    static 
    {
        NONE = new EnumShortcutKey("NONE", 0);
        ENTER = new EnumShortcutKey("ENTER", 1);
        SHIFT_ENTER = new EnumShortcutKey("SHIFT_ENTER", 2);
        $VALUES = (new EnumShortcutKey[] {
            NONE, ENTER, SHIFT_ENTER
        });
    }
}
