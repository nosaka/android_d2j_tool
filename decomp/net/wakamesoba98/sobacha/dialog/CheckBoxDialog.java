// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public abstract class CheckBoxDialog
{

    public CheckBoxDialog()
    {
    }

    public void build(Context context, int i, CharSequence acharsequence[], final boolean checkedItems[])
    {
        context = new android.app.AlertDialog.Builder(context);
        context.setTitle(i);
        context.setMultiChoiceItems(acharsequence, checkedItems, new android.content.DialogInterface.OnMultiChoiceClickListener() {

            public void onClick(DialogInterface dialoginterface, int j, boolean flag)
            {
                checkedItems[j] = flag;
            }

            final CheckBoxDialog this$0;
            final boolean val$checkedItems[];

            
            {
                this$0 = CheckBoxDialog.this;
                checkedItems = aflag;
                super();
            }
        }
);
        context.setPositiveButton(0x7f0700d3, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                onPositiveButtonClick(checkedItems);
            }

            final CheckBoxDialog this$0;
            final boolean val$checkedItems[];

            
            {
                this$0 = CheckBoxDialog.this;
                checkedItems = aflag;
                super();
            }
        }
);
        context.setNegativeButton(0x7f07002f, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
            }

            final CheckBoxDialog this$0;

            
            {
                this$0 = CheckBoxDialog.this;
                super();
            }
        }
);
        context.create().show();
    }

    public abstract void onPositiveButtonClick(boolean aflag[]);
}
