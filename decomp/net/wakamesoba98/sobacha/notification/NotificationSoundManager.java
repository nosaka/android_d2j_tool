// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.notification;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import java.io.IOException;
import net.wakamesoba98.sobacha.preference.prefs.StreamPreferences;

class NotificationSoundManager
{

    NotificationSoundManager(Context context1, StreamPreferences streampreferences)
    {
        context = context1;
        uri = streampreferences.getNotificationSoundUri();
    }

    private boolean isMannerMode(AudioManager audiomanager)
    {
        return audiomanager.getRingerMode() == 0 || audiomanager.getRingerMode() == 1;
    }

    void play()
    {
        AudioManager audiomanager;
        if(uri != null)
            if((audiomanager = (AudioManager)context.getSystemService("audio")).getStreamVolume(3) != 0 && !isMannerMode(audiomanager))
            {
                MediaPlayer mediaplayer = new MediaPlayer();
                try
                {
                    mediaplayer.setDataSource(context, uri);
                    mediaplayer.setAudioStreamType(3);
                    mediaplayer.setLooping(false);
                    mediaplayer.prepare();
                    mediaplayer.start();
                    return;
                }
                catch(IOException ioexception)
                {
                    ioexception.printStackTrace();
                }
                return;
            }
    }

    private Context context;
    private Uri uri;
}
