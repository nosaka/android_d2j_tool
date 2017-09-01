// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

public class SelectionAdapter extends ArrayAdapter
{

    public SelectionAdapter(Context context, int i)
    {
        super(context, i);
        array = new SparseBooleanArray();
        themeManager = new ThemeManager(context);
    }

    public void clearSelection()
    {
        array = new SparseBooleanArray();
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        view = super.getView(i, view, viewgroup);
        if(array.get(i))
        {
            view.setBackgroundColor(themeManager.getThemeColor(0x7f0d0050));
            return view;
        } else
        {
            view.setBackgroundColor(ResourceHelper.getColor(getContext(), 0x7f0d00bd));
            return view;
        }
    }

    public void setSelection(int i, boolean flag)
    {
        array.put(i, flag);
    }

    private SparseBooleanArray array;
    private ThemeManager themeManager;
}
