// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import net.wakamesoba98.sobacha.core.ResourceHelper;

public class Notificator
{

    public Notificator()
    {
    }

    public static void status(Context context, int i, int j)
    {
        Object obj = ResourceHelper.getString(context, i);
        String s = ResourceHelper.getString(context, j);
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context);
        builder.setSmallIcon(0x7f0200d0);
        builder.setContentTitle(((CharSequence) (obj)));
        builder.setContentText(s);
        builder.setTicker(s);
        builder.setAutoCancel(true);
        obj = builder.build();
        ((NotificationManager)context.getSystemService("notification")).notify(0, ((android.app.Notification) (obj)));
    }

    public static void toast(Context context, int i)
    {
        toast(context, ResourceHelper.getString(context, i));
    }

    public static void toast(Context context, int i, String s)
    {
        s = (new StringBuilder()).append(ResourceHelper.getString(context, i)).append("\n").append(s).toString();
        (new Handler(Looper.getMainLooper())).post(new Runnable(context, s) {

            public void run()
            {
                Toast.makeText(context, text, 1).show();
            }

            final Context val$context;
            final String val$text;

            
            {
                context = context1;
                text = s;
                super();
            }
        }
);
    }

    private static void toast(Context context, String s)
    {
        (new Handler(Looper.getMainLooper())).post(new Runnable(context, s) {

            public void run()
            {
                Toast.makeText(context, text, 0).show();
            }

            final Context val$context;
            final String val$text;

            
            {
                context = context1;
                text = s;
                super();
            }
        }
);
    }
}
