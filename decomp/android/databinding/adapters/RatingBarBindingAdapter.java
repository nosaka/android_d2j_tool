// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.RatingBar;

public class RatingBarBindingAdapter
{

    public RatingBarBindingAdapter()
    {
    }

    public static void setListeners(RatingBar ratingbar, android.widget.RatingBar.OnRatingBarChangeListener onratingbarchangelistener, InverseBindingListener inversebindinglistener)
    {
        if(inversebindinglistener == null)
        {
            ratingbar.setOnRatingBarChangeListener(onratingbarchangelistener);
            return;
        } else
        {
            ratingbar.setOnRatingBarChangeListener(new android.widget.RatingBar.OnRatingBarChangeListener(onratingbarchangelistener, inversebindinglistener) {

                public void onRatingChanged(RatingBar ratingbar1, float f, boolean flag)
                {
                    if(listener != null)
                        listener.onRatingChanged(ratingbar1, f, flag);
                    ratingChange.onChange();
                }

                final android.widget.RatingBar.OnRatingBarChangeListener val$listener;
                final InverseBindingListener val$ratingChange;

            
            {
                listener = onratingbarchangelistener;
                ratingChange = inversebindinglistener;
                super();
            }
            }
);
            return;
        }
    }

    public static void setRating(RatingBar ratingbar, float f)
    {
        if(ratingbar.getRating() != f)
            ratingbar.setRating(f);
    }
}
