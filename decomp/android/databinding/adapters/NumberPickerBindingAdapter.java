// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.NumberPicker;

public class NumberPickerBindingAdapter
{

    public NumberPickerBindingAdapter()
    {
    }

    public static void setListeners(NumberPicker numberpicker, android.widget.NumberPicker.OnValueChangeListener onvaluechangelistener, InverseBindingListener inversebindinglistener)
    {
        if(inversebindinglistener == null)
        {
            numberpicker.setOnValueChangedListener(onvaluechangelistener);
            return;
        } else
        {
            numberpicker.setOnValueChangedListener(new android.widget.NumberPicker.OnValueChangeListener(onvaluechangelistener, inversebindinglistener) {

                public void onValueChange(NumberPicker numberpicker1, int i, int j)
                {
                    if(listener != null)
                        listener.onValueChange(numberpicker1, i, j);
                    attrChange.onChange();
                }

                final InverseBindingListener val$attrChange;
                final android.widget.NumberPicker.OnValueChangeListener val$listener;

            
            {
                listener = onvaluechangelistener;
                attrChange = inversebindinglistener;
                super();
            }
            }
);
            return;
        }
    }

    public static void setValue(NumberPicker numberpicker, int i)
    {
        if(numberpicker.getValue() != i)
            numberpicker.setValue(i);
    }
}
