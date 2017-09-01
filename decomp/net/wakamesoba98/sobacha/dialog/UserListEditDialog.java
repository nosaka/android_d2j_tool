// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

public abstract class UserListEditDialog
{

    public UserListEditDialog()
    {
    }

    public void build(Context context, String s, boolean flag, String s1)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        context = LayoutInflater.from(context).inflate(0x7f030042, null);
        final EditText editTextName = (EditText)context.findViewById(0x7f0e00d3);
        final EditText editTextDescription = (EditText)context.findViewById(0x7f0e00d5);
        final RadioButton radioButtonPublic = (RadioButton)context.findViewById(0x7f0e00d7);
        RadioGroup radiogroup = (RadioGroup)context.findViewById(0x7f0e00d6);
        editTextName.setText(s);
        editTextDescription.setText(s1);
        if(flag)
            radiogroup.check(0x7f0e00d7);
        else
            radiogroup.check(0x7f0e00d8);
        builder.setView(context);
        builder.setPositiveButton(0x7f0700d3, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                onPositiveButtonClick(editTextName.getText().toString(), radioButtonPublic.isChecked(), editTextDescription.getText().toString());
            }

            final UserListEditDialog this$0;
            final EditText val$editTextDescription;
            final EditText val$editTextName;
            final RadioButton val$radioButtonPublic;

            
            {
                this$0 = UserListEditDialog.this;
                editTextName = edittext;
                radioButtonPublic = radiobutton;
                editTextDescription = edittext1;
                super();
            }
        }
);
        builder.setNegativeButton(0x7f07002f, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final UserListEditDialog this$0;

            
            {
                this$0 = UserListEditDialog.this;
                super();
            }
        }
);
        builder.create().show();
    }

    public abstract void onPositiveButtonClick(String s, boolean flag, String s1);
}
