// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.RadioGroup;

public class RadioGroupBindingAdapter
{

    public RadioGroupBindingAdapter()
    {
    }

    public static void setCheckedButton(RadioGroup radiogroup, int i)
    {
        if(i != radiogroup.getCheckedRadioButtonId())
            radiogroup.check(i);
    }

    public static void setListeners(RadioGroup radiogroup, android.widget.RadioGroup.OnCheckedChangeListener oncheckedchangelistener, InverseBindingListener inversebindinglistener)
    {
        if(inversebindinglistener == null)
        {
            radiogroup.setOnCheckedChangeListener(oncheckedchangelistener);
            return;
        } else
        {
            radiogroup.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener(oncheckedchangelistener, inversebindinglistener) {

                public void onCheckedChanged(RadioGroup radiogroup1, int i)
                {
                    if(listener != null)
                        listener.onCheckedChanged(radiogroup1, i);
                    attrChange.onChange();
                }

                final InverseBindingListener val$attrChange;
                final android.widget.RadioGroup.OnCheckedChangeListener val$listener;

            
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
