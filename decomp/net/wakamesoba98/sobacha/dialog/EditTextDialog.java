// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;

// Referenced classes of package net.wakamesoba98.sobacha.dialog:
//            DialogShowListener

public abstract class EditTextDialog
{

    public EditTextDialog()
    {
    }

    public void build(final Activity activity)
    {
        Object obj = new android.app.AlertDialog.Builder(activity);
        View view = LayoutInflater.from(activity).inflate(0x7f030040, null);
        final EditText editText = (EditText)view.findViewById(0x7f0e00cf);
        ((android.app.AlertDialog.Builder) (obj)).setView(view);
        if(positiveButtonRes <= 0)
            positiveButtonRes = 0x7f0700d3;
        ((android.app.AlertDialog.Builder) (obj)).setPositiveButton(positiveButtonRes, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                (new ImeUtil()).hideIme(activity, editText);
                onPositiveButtonClick(editText.getText().toString());
            }

            final EditTextDialog this$0;
            final Activity val$activity;
            final EditText val$editText;

            
            {
                this$0 = EditTextDialog.this;
                activity = activity1;
                editText = edittext;
                super();
            }
        }
);
        ((android.app.AlertDialog.Builder) (obj)).setNegativeButton(0x7f07002f, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                (new ImeUtil()).hideIme(activity, editText);
            }

            final EditTextDialog this$0;
            final Activity val$activity;
            final EditText val$editText;

            
            {
                this$0 = EditTextDialog.this;
                activity = activity1;
                editText = edittext;
                super();
            }
        }
);
        obj = ((android.app.AlertDialog.Builder) (obj)).create();
        (new DialogShowListener()).setListener(((AlertDialog) (obj)), activity, editText);
        ((AlertDialog) (obj)).show();
    }

    public abstract void onPositiveButtonClick(String s);

    public void setButtonText(int i)
    {
        positiveButtonRes = i;
    }

    private int positiveButtonRes;
}
