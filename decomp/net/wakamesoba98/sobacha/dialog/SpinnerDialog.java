// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import net.wakamesoba98.sobacha.core.ResourceHelper;

public class SpinnerDialog
{

    public SpinnerDialog(Context context1)
    {
        context = context1;
    }

    private void showCancellable(int i, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        dialog = new ProgressDialog(context);
        dialog.setMessage(ResourceHelper.getString(context, i));
        dialog.setProgressStyle(0);
        if(oncancellistener == null)
            dialog.setCancelable(false);
        else
            dialog.setOnCancelListener(oncancellistener);
        dialog.show();
    }

    public void dismiss()
    {
        if(dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void show()
    {
        showCancellable(0x7f0700bd, null);
    }

    public void show(int i)
    {
        showCancellable(i, null);
    }

    public void showCancellable(android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        showCancellable(0x7f0700bd, oncancellistener);
    }

    private Context context;
    private ProgressDialog dialog;
}
