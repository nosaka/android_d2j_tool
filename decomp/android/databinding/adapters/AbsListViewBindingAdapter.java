// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.widget.AbsListView;

public class AbsListViewBindingAdapter
{
    public static interface OnScroll
    {

        public abstract void onScroll(AbsListView abslistview, int i, int j, int k);
    }

    public static interface OnScrollStateChanged
    {

        public abstract void onScrollStateChanged(AbsListView abslistview, int i);
    }


    public AbsListViewBindingAdapter()
    {
    }

    public static void setOnScroll(AbsListView abslistview, OnScroll onscroll, OnScrollStateChanged onscrollstatechanged)
    {
        abslistview.setOnScrollListener(new android.widget.AbsListView.OnScrollListener(onscrollstatechanged, onscroll) {

            public void onScroll(AbsListView abslistview1, int i, int j, int k)
            {
                if(scrollListener != null)
                    scrollListener.onScroll(abslistview1, i, j, k);
            }

            public void onScrollStateChanged(AbsListView abslistview1, int i)
            {
                if(scrollStateListener != null)
                    scrollStateListener.onScrollStateChanged(abslistview1, i);
            }

            final OnScroll val$scrollListener;
            final OnScrollStateChanged val$scrollStateListener;

            
            {
                scrollStateListener = onscrollstatechanged;
                scrollListener = onscroll;
                super();
            }
        }
);
    }
}
