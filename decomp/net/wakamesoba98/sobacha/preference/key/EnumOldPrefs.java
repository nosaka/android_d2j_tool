// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.key;


// Referenced classes of package net.wakamesoba98.sobacha.preference.key:
//            Prefs, EnumValueType

public final class EnumOldPrefs extends Enum
    implements Prefs
{

    private EnumOldPrefs(String s, int i, EnumValueType enumvaluetype, String s1, Object obj)
    {
        super(s, i);
        type = enumvaluetype;
        key = s1;
        defaultValue = obj;
    }

    public static EnumOldPrefs valueOf(String s)
    {
        return (EnumOldPrefs)Enum.valueOf(net/wakamesoba98/sobacha/preference/key/EnumOldPrefs, s);
    }

    public static EnumOldPrefs[] values()
    {
        return (EnumOldPrefs[])$VALUES.clone();
    }

    public Object getDefaultValue()
    {
        return defaultValue;
    }

    public String getKey()
    {
        return key;
    }

    public EnumValueType getType()
    {
        return type;
    }

    private static final EnumOldPrefs $VALUES[];
    public static final EnumOldPrefs MUTE_CLIENT;
    public static final EnumOldPrefs MUTE_TWEET;
    public static final EnumOldPrefs MUTE_USER;
    public static final EnumOldPrefs MY_SCREEN_NAME;
    private final Object defaultValue;
    private final String key;
    private final EnumValueType type;

    static 
    {
        MY_SCREEN_NAME = new EnumOldPrefs("MY_SCREEN_NAME", 0, EnumValueType.STRING, "my_screen_name", "");
        MUTE_TWEET = new EnumOldPrefs("MUTE_TWEET", 1, EnumValueType.STRING, "pref_mute_tweet", "");
        MUTE_USER = new EnumOldPrefs("MUTE_USER", 2, EnumValueType.STRING, "pref_mute_user", "");
        MUTE_CLIENT = new EnumOldPrefs("MUTE_CLIENT", 3, EnumValueType.STRING, "pref_mute_client", "");
        $VALUES = (new EnumOldPrefs[] {
            MY_SCREEN_NAME, MUTE_TWEET, MUTE_USER, MUTE_CLIENT
        });
    }
}
