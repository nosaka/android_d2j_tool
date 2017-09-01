// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.CalendarView;

public class CalendarViewBindingAdapter
{

    public CalendarViewBindingAdapter()
    {
    }

    public static void setDate(CalendarView calendarview, long l)
    {
        if(calendarview.getDate() != l)
            calendarview.setDate(l);
    }

    public static void setListeners(CalendarView calendarview, android.widget.CalendarView.OnDateChangeListener ondatechangelistener, InverseBindingListener inversebindinglistener)
    {
        if(inversebindinglistener == null)
        {
            calendarview.setOnDateChangeListener(ondatechangelistener);
            return;
        } else
        {
            calendarview.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener(ondatechangelistener, inversebindinglistener) {

                public void onSelectedDayChange(CalendarView calendarview1, int i, int j, int k)
                {
                    if(onDayChange != null)
                        onDayChange.onSelectedDayChange(calendarview1, i, j, k);
                    attrChange.onChange();
                }

                final InverseBindingListener val$attrChange;
                final android.widget.CalendarView.OnDateChangeListener val$onDayChange;

            
            {
                onDayChange = ondatechangelistener;
                attrChange = inversebindinglistener;
                super();
            }
            }
);
            return;
        }
    }
}
