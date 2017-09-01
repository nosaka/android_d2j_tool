// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.theme;

import android.content.Context;
import android.content.res.Resources;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.value.EnumColorScheme;
import net.wakamesoba98.sobacha.preference.value.EnumTheme;

public class ThemeManager
{

    public ThemeManager(Context context1)
    {
        context = context1;
        theme = getThemePreference();
        colorScheme = getColorScheme();
        useLike = PreferenceUtil.getBooleanPreference(context1, EnumPrefs.USE_LIKE);
    }

    private EnumColorScheme getColorScheme()
    {
        int i = PreferenceUtil.getIntPreference(context, EnumPrefs.COLOR_SCHEME);
        return EnumColorScheme.values()[i];
    }

    private int getThemeColorId(int i)
    {
        String s;
        String s1;
        s1 = context.getResources().getResourceEntryName(i);
        s = s1;
        if(!s1.contains("_light")) goto _L2; else goto _L1
_L1:
        s = s1;
        if(theme == EnumTheme.DARK)
            s = s1.replace("_light", "_dark");
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumColorScheme[];
            static final int $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumTheme[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumColorScheme = new int[EnumColorScheme.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumColorScheme[EnumColorScheme.CLASSIC.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumColorScheme[EnumColorScheme.HIGH_CONTRAST.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumTheme = new int[EnumTheme.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumTheme[EnumTheme.LIGHT.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$preference$value$EnumTheme[EnumTheme.DARK.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.preference.value.EnumColorScheme[colorScheme.ordinal()];
        JVM INSTR tableswitch 1 2: default 76
    //                   1 100
    //                   2 123;
           goto _L2 _L3 _L4
_L2:
        return context.getResources().getIdentifier(s, "color", context.getApplicationContext().getPackageName());
_L3:
        s = (new StringBuilder()).append(s).append("_classic").toString();
        continue; /* Loop/switch isn't completed */
_L4:
        s = (new StringBuilder()).append(s).append("_contrast").toString();
        if(true) goto _L2; else goto _L5
_L5:
    }

    private EnumTheme getThemePreference()
    {
        int i = PreferenceUtil.getIntPreference(context, EnumPrefs.THEME);
        return EnumTheme.values()[i];
    }

    public int getActivityTheme()
    {
        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.preference.value.EnumTheme[theme.ordinal()])
        {
        default:
            return 0x7f090024;

        case 1: // '\001'
            return 0x7f090027;
        }
    }

    public int getMenuIconDrawableId(int i)
    {
        int j = i;
        if(SystemVersion.isHoneycombOrLater())
            j = getThemeDrawableId(i);
        return j;
    }

    public int getNoTitleBarTheme()
    {
        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.preference.value.EnumTheme[theme.ordinal()])
        {
        default:
            return 0x7f090025;

        case 1: // '\001'
            return 0x7f090028;
        }
    }

    public int getTabDrawableId(int i, boolean flag)
    {
        int j = i;
        if(!useLike) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 2130837656 2130837657: default 32
    //                   2130837656 87
    //                   2130837657 93;
           goto _L3 _L4 _L5
_L3:
        j = i;
_L2:
        i = j;
        if(flag)
        {
            String s = context.getResources().getResourceEntryName(j).replace("_tab", "_tab_selected");
            i = context.getResources().getIdentifier(s, "drawable", context.getApplicationContext().getPackageName());
        }
        return i;
_L4:
        j = 0x7f0200ad;
        continue; /* Loop/switch isn't completed */
_L5:
        j = 0x7f0200ae;
        if(true) goto _L2; else goto _L6
_L6:
    }

    public int getThemeColor(int i)
    {
        return ResourceHelper.getColor(context, getThemeColorId(i));
    }

    public int getThemeDrawableId(int i)
    {
        int j = i;
        if(!useLike) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR lookupswitch 4: default 52
    //                   2130837654: 98
    //                   2130837655: 92
    //                   2130837743: 110
    //                   2130837769: 104;
           goto _L3 _L4 _L5 _L6 _L7
_L6:
        break MISSING_BLOCK_LABEL_110;
_L3:
        j = i;
_L2:
        String s;
        i = j;
        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.preference.value.EnumTheme[theme.ordinal()])
        {
        default:
            i = 0;
            // fall through

        case 1: // '\001'
            return i;

        case 2: // '\002'
            s = context.getResources().getResourceEntryName(j).replace("_light", "_dark");
            break;
        }
        break MISSING_BLOCK_LABEL_135;
_L5:
        j = 0x7f0200ac;
          goto _L2
_L4:
        j = 0x7f0200ab;
          goto _L2
_L7:
        j = 0x7f02010b;
          goto _L2
        j = 0x7f0200f2;
          goto _L2
        return context.getResources().getIdentifier(s, "drawable", context.getApplicationContext().getPackageName());
    }

    public int getTranslucentTheme()
    {
        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.preference.value.EnumTheme[theme.ordinal()])
        {
        default:
            return 0x7f090026;

        case 1: // '\001'
            return 0x7f090029;
        }
    }

    private EnumColorScheme colorScheme;
    private Context context;
    private EnumTheme theme;
    private boolean useLike;
}
