// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import net.wakamesoba98.sobacha.core.ResourceHelper;

public abstract class InformationDialog
{

    public InformationDialog()
    {
    }

    public void build(Context context, int i, int j)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(ResourceHelper.getString(context, i));
        builder.setMessage(ResourceHelper.getString(context, j));
        builder.setPositiveButton(0x7f0700d3, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                onOkButtonClick();
            }

            final InformationDialog this$0;

            
            {
                this$0 = InformationDialog.this;
                super();
            }
        }
);
        builder.create().show();
    }

    public abstract void onOkButtonClick();
}
