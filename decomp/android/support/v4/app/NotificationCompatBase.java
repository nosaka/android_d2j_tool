// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotificationCompatBase
{
    public static abstract class Action
    {

        public abstract PendingIntent getActionIntent();

        public abstract boolean getAllowGeneratedReplies();

        public abstract Bundle getExtras();

        public abstract int getIcon();

        public abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();

        public abstract CharSequence getTitle();

        public Action()
        {
        }
    }

    public static interface Action.Factory
    {

        public abstract Action build(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInputCompatBase.RemoteInput aremoteinput[], boolean flag);

        public abstract Action[] newArray(int i);
    }

    public static abstract class UnreadConversation
    {

        abstract long getLatestTimestamp();

        abstract String[] getMessages();

        abstract String getParticipant();

        abstract String[] getParticipants();

        abstract PendingIntent getReadPendingIntent();

        abstract RemoteInputCompatBase.RemoteInput getRemoteInput();

        abstract PendingIntent getReplyPendingIntent();

        public UnreadConversation()
        {
        }
    }

    public static interface UnreadConversation.Factory
    {

        public abstract UnreadConversation build(String as[], RemoteInputCompatBase.RemoteInput remoteinput, PendingIntent pendingintent, PendingIntent pendingintent1, String as1[], long l);
    }


    public NotificationCompatBase()
    {
    }

    public static Notification add(Notification notification, Context context, CharSequence charsequence, CharSequence charsequence1, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        if(sSetLatestEventInfo == null)
            try
            {
                sSetLatestEventInfo = android/app/Notification.getMethod("setLatestEventInfo", new Class[] {
                    android/content/Context, java/lang/CharSequence, java/lang/CharSequence, android/app/PendingIntent
                });
            }
            // Misplaced declaration of an exception variable
            catch(Notification notification)
            {
                throw new RuntimeException(notification);
            }
        sSetLatestEventInfo.invoke(notification, new Object[] {
            context, charsequence, charsequence1, pendingintent
        });
        notification.fullScreenIntent = pendingintent1;
        return notification;
        notification;
_L2:
        throw new RuntimeException(notification);
        notification;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static Method sSetLatestEventInfo;
}
