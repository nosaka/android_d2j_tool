// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.DatePicker;

// Referenced classes of package android.databinding.adapters:
//            ListenerUtil

public class DatePickerBindingAdapter
{
    private static class DateChangedListener
        implements android.widget.DatePicker.OnDateChangedListener
    {

        public void onDateChanged(DatePicker datepicker, int i, int j, int k)
        {
            if(mListener != null)
                mListener.onDateChanged(datepicker, i, j, k);
            if(mYearChanged != null)
                mYearChanged.onChange();
            if(mMonthChanged != null)
                mMonthChanged.onChange();
            if(mDayChanged != null)
                mDayChanged.onChange();
        }

        public void setListeners(android.widget.DatePicker.OnDateChangedListener ondatechangedlistener, InverseBindingListener inversebindinglistener, InverseBindingListener inversebindinglistener1, InverseBindingListener inversebindinglistener2)
        {
            mListener = ondatechangedlistener;
            mYearChanged = inversebindinglistener;
            mMonthChanged = inversebindinglistener1;
            mDayChanged = inversebindinglistener2;
        }

        InverseBindingListener mDayChanged;
        android.widget.DatePicker.OnDateChangedListener mListener;
        InverseBindingListener mMonthChanged;
        InverseBindingListener mYearChanged;

        private DateChangedListener()
        {
        }

    }


    public DatePickerBindingAdapter()
    {
    }

    public static void setListeners(DatePicker datepicker, int i, int j, int k, android.widget.DatePicker.OnDateChangedListener ondatechangedlistener, InverseBindingListener inversebindinglistener, InverseBindingListener inversebindinglistener1, InverseBindingListener inversebindinglistener2)
    {
        int l = i;
        if(i == 0)
            l = datepicker.getYear();
        i = k;
        if(k == 0)
            i = datepicker.getDayOfMonth();
        if(inversebindinglistener == null && inversebindinglistener1 == null && inversebindinglistener2 == null)
        {
            datepicker.init(l, j, i, ondatechangedlistener);
            return;
        }
        DateChangedListener datechangedlistener1 = (DateChangedListener)ListenerUtil.getListener(datepicker, com.android.databinding.library.baseAdapters.R.id.onDateChanged);
        DateChangedListener datechangedlistener = datechangedlistener1;
        if(datechangedlistener1 == null)
        {
            datechangedlistener = new DateChangedListener();
            ListenerUtil.trackListener(datepicker, datechangedlistener, com.android.databinding.library.baseAdapters.R.id.onDateChanged);
        }
        datechangedlistener.setListeners(ondatechangedlistener, inversebindinglistener, inversebindinglistener1, inversebindinglistener2);
        datepicker.init(l, j, i, datechangedlistener);
    }
}
