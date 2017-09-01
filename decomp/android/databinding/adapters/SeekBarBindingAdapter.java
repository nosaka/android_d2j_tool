// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.SeekBar;

public class SeekBarBindingAdapter
{
    public static interface OnProgressChanged
    {

        public abstract void onProgressChanged(SeekBar seekbar, int i, boolean flag);
    }

    public static interface OnStartTrackingTouch
    {

        public abstract void onStartTrackingTouch(SeekBar seekbar);
    }

    public static interface OnStopTrackingTouch
    {

        public abstract void onStopTrackingTouch(SeekBar seekbar);
    }


    public SeekBarBindingAdapter()
    {
    }

    public static void setOnSeekBarChangeListener(SeekBar seekbar, OnStartTrackingTouch onstarttrackingtouch, OnStopTrackingTouch onstoptrackingtouch, OnProgressChanged onprogresschanged, InverseBindingListener inversebindinglistener)
    {
        if(onstarttrackingtouch == null && onstoptrackingtouch == null && onprogresschanged == null && inversebindinglistener == null)
        {
            seekbar.setOnSeekBarChangeListener(null);
            return;
        } else
        {
            seekbar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener(onprogresschanged, inversebindinglistener, onstarttrackingtouch, onstoptrackingtouch) {

                public void onProgressChanged(SeekBar seekbar1, int i, boolean flag)
                {
                    if(progressChanged != null)
                        progressChanged.onProgressChanged(seekbar1, i, flag);
                    if(attrChanged != null)
                        attrChanged.onChange();
                }

                public void onStartTrackingTouch(SeekBar seekbar1)
                {
                    if(start != null)
                        start.onStartTrackingTouch(seekbar1);
                }

                public void onStopTrackingTouch(SeekBar seekbar1)
                {
                    if(stop != null)
                        stop.onStopTrackingTouch(seekbar1);
                }

                final InverseBindingListener val$attrChanged;
                final OnProgressChanged val$progressChanged;
                final OnStartTrackingTouch val$start;
                final OnStopTrackingTouch val$stop;

            
            {
                progressChanged = onprogresschanged;
                attrChanged = inversebindinglistener;
                start = onstarttrackingtouch;
                stop = onstoptrackingtouch;
                super();
            }
            }
);
            return;
        }
    }

    public static void setProgress(SeekBar seekbar, int i)
    {
        if(i != seekbar.getProgress())
            seekbar.setProgress(i);
    }
}
