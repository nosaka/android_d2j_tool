// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.TabHost;

public class TabHostBindingAdapter
{

    public TabHostBindingAdapter()
    {
    }

    public static int getCurrentTab(TabHost tabhost)
    {
        return tabhost.getCurrentTab();
    }

    public static String getCurrentTabTag(TabHost tabhost)
    {
        return tabhost.getCurrentTabTag();
    }

    public static void setCurrentTab(TabHost tabhost, int i)
    {
        if(tabhost.getCurrentTab() != i)
            tabhost.setCurrentTab(i);
    }

    public static void setCurrentTabTag(TabHost tabhost, String s)
    {
        if(tabhost.getCurrentTabTag() != s)
            tabhost.setCurrentTabByTag(s);
    }

    public static void setListeners(TabHost tabhost, android.widget.TabHost.OnTabChangeListener ontabchangelistener, InverseBindingListener inversebindinglistener)
    {
        if(inversebindinglistener == null)
        {
            tabhost.setOnTabChangedListener(ontabchangelistener);
            return;
        } else
        {
            tabhost.setOnTabChangedListener(new android.widget.TabHost.OnTabChangeListener(ontabchangelistener, inversebindinglistener) {

                public void onTabChanged(String s)
                {
                    if(listener != null)
                        listener.onTabChanged(s);
                    attrChange.onChange();
                }

                final InverseBindingListener val$attrChange;
                final android.widget.TabHost.OnTabChangeListener val$listener;

            
            {
                listener = ontabchangelistener;
                attrChange = inversebindinglistener;
                super();
            }
            }
);
            return;
        }
    }
}
