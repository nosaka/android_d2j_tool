// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.prefs;

import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;

public class StreamPreferences
{

    public StreamPreferences()
    {
    }

    private Uri loadSoundSettings(Context context)
    {
        if(PreferenceUtil.getBooleanPreference(context, EnumPrefs.USE_NOTIFICATION_SOUND))
        {
            context = PreferenceUtil.getStringPreference(context, EnumPrefs.NOTIFICATION_SOUND);
            if(context != null && !context.equals(""))
                return Uri.parse(context);
            else
                return RingtoneManager.getDefaultUri(2);
        } else
        {
            return null;
        }
    }

    public Uri getNotificationSoundUri()
    {
        return notificationSoundUri;
    }

    public int getOffTimeMilliseconds()
    {
        return offTimeMilliseconds;
    }

    public void loadPreferences(Context context)
    {
        notificationSoundUri = loadSoundSettings(context);
        offTimeMilliseconds = PreferenceUtil.getIntPreference(context, EnumPrefs.OFF_TIMER) * 1000 * 60;
    }

    private Uri notificationSoundUri;
    private int offTimeMilliseconds;
}
