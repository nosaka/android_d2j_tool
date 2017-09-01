// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference;

import android.content.Context;
import android.content.SharedPreferences;
import net.wakamesoba98.sobacha.preference.key.EnumValueType;
import net.wakamesoba98.sobacha.preference.key.Prefs;

public class PreferenceUtil
{

    public PreferenceUtil()
    {
    }

    public static boolean getBooleanPreference(Context context, Prefs prefs)
    {
        return context.getSharedPreferences("sobacha_prefs", 0).getBoolean(prefs.getKey(), ((Boolean)prefs.getDefaultValue()).booleanValue());
    }

    public static int getIntPreference(Context context, Prefs prefs)
    {
        context = context.getSharedPreferences("sobacha_prefs", 0).getString(prefs.getKey(), String.valueOf(prefs.getDefaultValue()));
        if(context.matches("[0-9]+"))
            return Integer.parseInt(context);
        else
            return ((Integer)prefs.getDefaultValue()).intValue();
    }

    public static long getLongPreference(Context context, Prefs prefs)
    {
        context = context.getSharedPreferences("sobacha_prefs", 0).getString(prefs.getKey(), String.valueOf(prefs.getDefaultValue()));
        if(context.matches("[0-9]+"))
            return Long.parseLong(context);
        else
            return ((Long)prefs.getDefaultValue()).longValue();
    }

    public static String getStringPreference(Context context, Prefs prefs)
    {
        return context.getSharedPreferences("sobacha_prefs", 0).getString(prefs.getKey(), (String)prefs.getDefaultValue());
    }

    public static void putPreference(Context context, Prefs prefs, Object obj)
    {
        EnumValueType enumvaluetype;
        context = context.getSharedPreferences("sobacha_prefs", 0).edit();
        enumvaluetype = prefs.getType();
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$preference$key$EnumValueType[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$preference$key$EnumValueType = new int[EnumValueType.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$key$EnumValueType[EnumValueType.STRING.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$key$EnumValueType[EnumValueType.INTEGER.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$key$EnumValueType[EnumValueType.LONG.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$key$EnumValueType[EnumValueType.BOOLEAN.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.preference.key.EnumValueType[enumvaluetype.ordinal()];
        JVM INSTR tableswitch 1 4: default 60
    //                   1 67
    //                   2 87
    //                   3 87
    //                   4 107;
           goto _L1 _L2 _L3 _L3 _L4
_L1:
        context.apply();
        return;
_L2:
        context.putString(prefs.getKey(), (String)obj);
        continue; /* Loop/switch isn't completed */
_L3:
        context.putString(prefs.getKey(), String.valueOf(obj));
        continue; /* Loop/switch isn't completed */
_L4:
        context.putBoolean(prefs.getKey(), ((Boolean)obj).booleanValue());
        if(true) goto _L1; else goto _L5
_L5:
    }

    public static final String PREF_NAME = "sobacha_prefs";
}
