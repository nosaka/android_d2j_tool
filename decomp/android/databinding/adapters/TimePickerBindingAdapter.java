// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.TimePicker;

public class TimePickerBindingAdapter
{

    public TimePickerBindingAdapter()
    {
    }

    public static int getHour(TimePicker timepicker)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return timepicker.getHour();
        timepicker = timepicker.getCurrentHour();
        if(timepicker == null)
            return 0;
        else
            return timepicker.intValue();
    }

    public static int getMinute(TimePicker timepicker)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return timepicker.getMinute();
        timepicker = timepicker.getCurrentMinute();
        if(timepicker == null)
            return 0;
        else
            return timepicker.intValue();
    }

    public static void setHour(TimePicker timepicker, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            if(timepicker.getHour() != i)
                timepicker.setHour(i);
        } else
        if(timepicker.getCurrentHour().intValue() != i)
        {
            timepicker.setCurrentHour(Integer.valueOf(i));
            return;
        }
    }

    public static void setListeners(TimePicker timepicker, android.widget.TimePicker.OnTimeChangedListener ontimechangedlistener, InverseBindingListener inversebindinglistener, InverseBindingListener inversebindinglistener1)
    {
        if(inversebindinglistener == null && inversebindinglistener1 == null)
        {
            timepicker.setOnTimeChangedListener(ontimechangedlistener);
            return;
        } else
        {
            timepicker.setOnTimeChangedListener(new android.widget.TimePicker.OnTimeChangedListener(ontimechangedlistener, inversebindinglistener, inversebindinglistener1) {

                public void onTimeChanged(TimePicker timepicker1, int i, int j)
                {
                    if(listener != null)
                        listener.onTimeChanged(timepicker1, i, j);
                    if(hourChange != null)
                        hourChange.onChange();
                    if(minuteChange != null)
                        minuteChange.onChange();
                }

                final InverseBindingListener val$hourChange;
                final android.widget.TimePicker.OnTimeChangedListener val$listener;
                final InverseBindingListener val$minuteChange;

            
            {
                listener = ontimechangedlistener;
                hourChange = inversebindinglistener;
                minuteChange = inversebindinglistener1;
                super();
            }
            }
);
            return;
        }
    }

    public static void setMinute(TimePicker timepicker, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            if(timepicker.getMinute() != i)
                timepicker.setMinute(i);
        } else
        if(timepicker.getCurrentMinute().intValue() != i)
        {
            timepicker.setCurrentHour(Integer.valueOf(i));
            return;
        }
    }
}
