// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.compatible;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import net.wakamesoba98.sobacha.notification.Notificator;

// Referenced classes of package net.wakamesoba98.sobacha.compatible:
//            SystemVersion

public class ClipboardHelper
{

    public ClipboardHelper()
    {
    }

    private void copy11(Context context, String s, String s1)
    {
        ((ClipboardManager)context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(s, s1));
    }

    public void copy(Context context, String s, String s1)
    {
        if(SystemVersion.isHoneycombOrLater())
            copy11(context, s, s1);
        else
            ((android.text.ClipboardManager)context.getSystemService("clipboard")).setText(s1);
        Notificator.toast(context, 0x7f070137);
    }
}
