// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public abstract class DontShowAgainDialog
{

    public DontShowAgainDialog()
    {
    }

    public void build(Context context, int i, int j)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        context = LayoutInflater.from(context).inflate(0x7f03003f, null);
        TextView textview = (TextView)context.findViewById(0x7f0e00cd);
        final CheckBox checkBox = (CheckBox)context.findViewById(0x7f0e00ce);
        builder.setView(context);
        builder.setTitle(i);
        textview.setText(j);
        builder.setPositiveButton(0x7f0700d3, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                onPositiveButtonClick(checkBox.isChecked());
            }

            final DontShowAgainDialog this$0;
            final CheckBox val$checkBox;

            
            {
                this$0 = DontShowAgainDialog.this;
                checkBox = checkbox;
                super();
            }
        }
);
        builder.setNegativeButton(0x7f07002f, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
            }

            final DontShowAgainDialog this$0;

            
            {
                this$0 = DontShowAgainDialog.this;
                super();
            }
        }
);
        builder.create().show();
    }

    public abstract void onPositiveButtonClick(boolean flag);
}
