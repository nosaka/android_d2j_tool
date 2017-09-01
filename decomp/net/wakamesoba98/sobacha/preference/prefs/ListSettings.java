// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.prefs;

import android.content.Context;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;

public class ListSettings
{

    public ListSettings()
    {
    }

    public int getIconSize()
    {
        return iconSize;
    }

    public int getThumbMode()
    {
        return thumbMode;
    }

    public boolean isLinkClickable()
    {
        return isLinkClickable;
    }

    public boolean isLowBandwidthMode()
    {
        return lowBandwidthMode;
    }

    public boolean isShowScreenNameInSingleLineMode()
    {
        return showScreenNameInSingleLineMode;
    }

    public boolean isShowVia()
    {
        return showVia;
    }

    public boolean isUseAbsoluteTime()
    {
        return useAbsoluteTime;
    }

    public boolean isWordWrapInSingleLineMode()
    {
        return wordWrapInSingleLineMode;
    }

    public void loadPreferences(Context context)
    {
        boolean flag1 = true;
        iconSize = PreferenceUtil.getIntPreference(context, EnumPrefs.ICON_SIZE);
        thumbMode = PreferenceUtil.getIntPreference(context, EnumPrefs.THUMBNAIL_MODE);
        useAbsoluteTime = PreferenceUtil.getBooleanPreference(context, EnumPrefs.ABSOLUTE_TIME);
        showVia = PreferenceUtil.getBooleanPreference(context, EnumPrefs.SHOW_VIA);
        lowBandwidthMode = PreferenceUtil.getBooleanPreference(context, EnumPrefs.LOW_BANDWIDTH_MODE);
        isLinkClickable = PreferenceUtil.getBooleanPreference(context, EnumPrefs.CLICKABLE_LINK);
        boolean flag2 = PreferenceUtil.getBooleanPreference(context, EnumPrefs.SINGLE_LINE_MODE);
        boolean flag;
        if(flag2 && PreferenceUtil.getBooleanPreference(context, EnumPrefs.WORD_WRAP))
            flag = true;
        else
            flag = false;
        wordWrapInSingleLineMode = flag;
        if(flag2 && PreferenceUtil.getBooleanPreference(context, EnumPrefs.SHOW_SCREEN_NAME))
            flag = flag1;
        else
            flag = false;
        showScreenNameInSingleLineMode = flag;
    }

    public static final int ICON_BIGGER = 2;
    public static final int ICON_MINI = 0;
    public static final int ICON_NORMAL = 1;
    public static final int THUMB_OFF = 0;
    public static final int THUMB_ON = 2;
    public static final int THUMB_WIFI_ONLY = 1;
    private int iconSize;
    private boolean isLinkClickable;
    private boolean lowBandwidthMode;
    private boolean showScreenNameInSingleLineMode;
    private boolean showVia;
    private int thumbMode;
    private boolean useAbsoluteTime;
    private boolean wordWrapInSingleLineMode;
}
