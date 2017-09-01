// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;

// Referenced classes of package net.wakamesoba98.sobacha.dialog:
//            EditTextDialog, DialogShowListener

public abstract class ScreenNameDialog extends EditTextDialog
{

    public ScreenNameDialog()
    {
    }

    public void build(final Activity activity)
    {
        Object obj = new android.app.AlertDialog.Builder(activity);
        View view = LayoutInflater.from(activity).inflate(0x7f030041, null);
        final AutoCompleteTextView editText = (AutoCompleteTextView)view.findViewById(0x7f0e00d1);
        if(friendArray != null)
        {
            editText.setAdapter(new ArrayAdapter(activity, 0x1090003, friendArray));
            editText.setThreshold(2);
        }
        TextView textview = (TextView)view.findViewById(0x7f0e00d0);
        textview.setText("@");
        if(!SystemVersion.isHoneycombOrLater())
            textview.setTextColor(-1);
        ((android.app.AlertDialog.Builder) (obj)).setView(view);
        ((android.app.AlertDialog.Builder) (obj)).setPositiveButton(0x7f0700d3, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                (new ImeUtil()).hideIme(activity, editText);
                onPositiveButtonClick(editText.getText().toString());
            }

            final ScreenNameDialog this$0;
            final Activity val$activity;
            final AutoCompleteTextView val$editText;

            
            {
                this$0 = ScreenNameDialog.this;
                activity = activity1;
                editText = autocompletetextview;
                super();
            }
        }
);
        ((android.app.AlertDialog.Builder) (obj)).setNegativeButton(0x7f07002f, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                (new ImeUtil()).hideIme(activity, editText);
            }

            final ScreenNameDialog this$0;
            final Activity val$activity;
            final AutoCompleteTextView val$editText;

            
            {
                this$0 = ScreenNameDialog.this;
                activity = activity1;
                editText = autocompletetextview;
                super();
            }
        }
);
        obj = ((android.app.AlertDialog.Builder) (obj)).create();
        (new DialogShowListener()).setListener(((AlertDialog) (obj)), activity, editText);
        ((AlertDialog) (obj)).show();
    }

    public void setFriends(String as[])
    {
        friendArray = as;
    }

    private String friendArray[];
}
