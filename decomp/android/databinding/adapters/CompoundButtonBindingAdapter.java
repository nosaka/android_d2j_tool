// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.CompoundButton;

public class CompoundButtonBindingAdapter
{

    public CompoundButtonBindingAdapter()
    {
    }

    public static void setChecked(CompoundButton compoundbutton, boolean flag)
    {
        if(compoundbutton.isChecked() != flag)
            compoundbutton.setChecked(flag);
    }

    public static void setListeners(CompoundButton compoundbutton, android.widget.CompoundButton.OnCheckedChangeListener oncheckedchangelistener, InverseBindingListener inversebindinglistener)
    {
        if(inversebindinglistener == null)
        {
            compoundbutton.setOnCheckedChangeListener(oncheckedchangelistener);
            return;
        } else
        {
            compoundbutton.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener(oncheckedchangelistener, inversebindinglistener) {

                public void onCheckedChanged(CompoundButton compoundbutton1, boolean flag)
                {
                    if(listener != null)
                        listener.onCheckedChanged(compoundbutton1, flag);
                    attrChange.onChange();
                }

                final InverseBindingListener val$attrChange;
                final android.widget.CompoundButton.OnCheckedChangeListener val$listener;

            
            {
                listener = oncheckedchangelistener;
                attrChange = inversebindinglistener;
                super();
            }
            }
);
            return;
        }
    }
}
