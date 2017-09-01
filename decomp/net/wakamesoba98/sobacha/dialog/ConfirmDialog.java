// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import net.wakamesoba98.sobacha.core.ResourceHelper;

public abstract class ConfirmDialog
{

    public ConfirmDialog()
    {
    }

    public void build(Context context, int i, int j)
    {
        build(context, i, j, null);
    }

    public void build(Context context, int i, int j, String s)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        String s1 = ResourceHelper.getString(context, j);
        context = s1;
        if(s != null)
            context = String.format(s1, new Object[] {
                s
            });
        builder.setTitle(i);
        builder.setMessage(context);
        builder.setPositiveButton(0x7f0700d3, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                onPositiveButtonClick();
            }

            final ConfirmDialog this$0;

            
            {
                this$0 = ConfirmDialog.this;
                super();
            }
        }
);
        builder.setNegativeButton(0x7f07002f, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                onNegativeButtonClick();
            }

            final ConfirmDialog this$0;

            
            {
                this$0 = ConfirmDialog.this;
                super();
            }
        }
);
        builder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                onDialogCancelled();
            }

            final ConfirmDialog this$0;

            
            {
                this$0 = ConfirmDialog.this;
                super();
            }
        }
);
        builder.create().show();
    }

    public void onDialogCancelled()
    {
    }

    public void onNegativeButtonClick()
    {
    }

    public abstract void onPositiveButtonClick();
}
