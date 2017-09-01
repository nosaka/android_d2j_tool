// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;

class DialogShowListener
{

    DialogShowListener()
    {
    }

    void setListener(AlertDialog alertdialog, final Activity activity, final EditText editText)
    {
        alertdialog.setOnShowListener(new android.content.DialogInterface.OnShowListener() {

            public void onShow(DialogInterface dialoginterface)
            {
                (new ImeUtil()).showIme(activity, editText);
            }

            final DialogShowListener this$0;
            final Activity val$activity;
            final EditText val$editText;

            
            {
                this$0 = DialogShowListener.this;
                activity = activity1;
                editText = edittext;
                super();
            }
        }
);
    }
}
