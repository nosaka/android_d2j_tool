// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.view.View;
import android.widget.AdapterView;

public class AdapterViewBindingAdapter
{
    public static interface OnItemSelected
    {

        public abstract void onItemSelected(AdapterView adapterview, View view, int i, long l);
    }

    public static class OnItemSelectedComponentListener
        implements android.widget.AdapterView.OnItemSelectedListener
    {

        public void onItemSelected(AdapterView adapterview, View view, int i, long l)
        {
            if(mSelected != null)
                mSelected.onItemSelected(adapterview, view, i, l);
            if(mAttrChanged != null)
                mAttrChanged.onChange();
        }

        public void onNothingSelected(AdapterView adapterview)
        {
            if(mNothingSelected != null)
                mNothingSelected.onNothingSelected(adapterview);
            if(mAttrChanged != null)
                mAttrChanged.onChange();
        }

        private final InverseBindingListener mAttrChanged;
        private final OnNothingSelected mNothingSelected;
        private final OnItemSelected mSelected;

        public OnItemSelectedComponentListener(OnItemSelected onitemselected, OnNothingSelected onnothingselected, InverseBindingListener inversebindinglistener)
        {
            mSelected = onitemselected;
            mNothingSelected = onnothingselected;
            mAttrChanged = inversebindinglistener;
        }
    }

    public static interface OnNothingSelected
    {

        public abstract void onNothingSelected(AdapterView adapterview);
    }


    public AdapterViewBindingAdapter()
    {
    }

    public static void setOnItemSelectedListener(AdapterView adapterview, OnItemSelected onitemselected, OnNothingSelected onnothingselected, InverseBindingListener inversebindinglistener)
    {
        if(onitemselected == null && onnothingselected == null && inversebindinglistener == null)
        {
            adapterview.setOnItemSelectedListener(null);
            return;
        } else
        {
            adapterview.setOnItemSelectedListener(new OnItemSelectedComponentListener(onitemselected, onnothingselected, inversebindinglistener));
            return;
        }
    }

    public static void setSelectedItemPosition(AdapterView adapterview, int i)
    {
        if(adapterview.getSelectedItemPosition() != i)
            adapterview.setSelection(i);
    }
}
