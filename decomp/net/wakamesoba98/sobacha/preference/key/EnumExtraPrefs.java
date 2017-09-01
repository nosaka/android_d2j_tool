// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.key;


// Referenced classes of package net.wakamesoba98.sobacha.preference.key:
//            Prefs, EnumValueType

public final class EnumExtraPrefs extends Enum
    implements Prefs
{

    private EnumExtraPrefs(String s, int i, EnumValueType enumvaluetype, String s1, Object obj)
    {
        super(s, i);
        type = enumvaluetype;
        key = s1;
        defaultValue = obj;
    }

    public static EnumExtraPrefs valueOf(String s)
    {
        return (EnumExtraPrefs)Enum.valueOf(net/wakamesoba98/sobacha/preference/key/EnumExtraPrefs, s);
    }

    public static EnumExtraPrefs[] values()
    {
        return (EnumExtraPrefs[])$VALUES.clone();
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

    private static final EnumExtraPrefs $VALUES[];
    public static final EnumExtraPrefs LAST_USER_ID;
    public static final EnumExtraPrefs LAYOUT_CHANGED;
    public static final EnumExtraPrefs REQUEST_TOKEN;
    public static final EnumExtraPrefs REQUEST_TOKEN_SECRET;
    public static final EnumExtraPrefs SET_CONFIRM_DEFAULT;
    public static final EnumExtraPrefs SET_DUAL_MODE_DEFAULT;
    public static final EnumExtraPrefs SET_OVERLAY_PERMISSION_DEFAULT;
    public static final EnumExtraPrefs USER_STREAM;
    private final Object defaultValue;
    private final String key;
    private final EnumValueType type;

    static 
    {
        LAST_USER_ID = new EnumExtraPrefs("LAST_USER_ID", 0, EnumValueType.LONG, "last_user_id", Long.valueOf(-1L));
        USER_STREAM = new EnumExtraPrefs("USER_STREAM", 1, EnumValueType.BOOLEAN, "user_stream", Boolean.valueOf(true));
        REQUEST_TOKEN = new EnumExtraPrefs("REQUEST_TOKEN", 2, EnumValueType.STRING, "request_token", "");
        REQUEST_TOKEN_SECRET = new EnumExtraPrefs("REQUEST_TOKEN_SECRET", 3, EnumValueType.STRING, "request_token_secret", "");
        SET_DUAL_MODE_DEFAULT = new EnumExtraPrefs("SET_DUAL_MODE_DEFAULT", 4, EnumValueType.BOOLEAN, "set_dual_mode_default", Boolean.valueOf(false));
        SET_CONFIRM_DEFAULT = new EnumExtraPrefs("SET_CONFIRM_DEFAULT", 5, EnumValueType.BOOLEAN, "set_confirm_default", Boolean.valueOf(false));
        SET_OVERLAY_PERMISSION_DEFAULT = new EnumExtraPrefs("SET_OVERLAY_PERMISSION_DEFAULT", 6, EnumValueType.BOOLEAN, "set_overlay_permission_default", Boolean.valueOf(false));
        LAYOUT_CHANGED = new EnumExtraPrefs("LAYOUT_CHANGED", 7, EnumValueType.BOOLEAN, "layout_changed", Boolean.valueOf(false));
        $VALUES = (new EnumExtraPrefs[] {
            LAST_USER_ID, USER_STREAM, REQUEST_TOKEN, REQUEST_TOKEN_SECRET, SET_DUAL_MODE_DEFAULT, SET_CONFIRM_DEFAULT, SET_OVERLAY_PERMISSION_DEFAULT, LAYOUT_CHANGED
        });
    }
}
