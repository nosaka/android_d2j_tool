// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.timer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import java.util.TimerTask;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.preference.prefs.StreamPreferences;
import net.wakamesoba98.sobacha.twitter.stream.StreamManager;

public class OffTimerTask extends TimerTask
{

    public OffTimerTask(Context context1, StreamManager streammanager, StreamPreferences streampreferences)
    {
        context = context1;
        streamManager = streammanager;
        startMillisecond = System.currentTimeMillis();
        preferences = streampreferences;
    }

    private boolean isOffTime()
    {
        return System.currentTimeMillis() - startMillisecond > (long)preferences.getOffTimeMilliseconds();
    }

    public void run()
    {
        if(preferences.getOffTimeMilliseconds() > 0 && isOffTime() && streamManager.isEnabled())
            (new Handler(Looper.getMainLooper())).post(new Runnable() {

                public void run()
                {
                    Toast.makeText(context, ResourceHelper.getString(context, 0x7f070044), 1).show();
                    streamManager.shutdown();
                }

                final OffTimerTask this$0;

            
            {
                this$0 = OffTimerTask.this;
                super();
            }
            }
);
    }

    private Context context;
    private StreamPreferences preferences;
    private long startMillisecond;
    private StreamManager streamManager;


}
