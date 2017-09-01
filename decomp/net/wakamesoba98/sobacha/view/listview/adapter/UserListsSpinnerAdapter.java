// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import twitter4j.UserList;

public class UserListsSpinnerAdapter extends ArrayAdapter
{

    public UserListsSpinnerAdapter(Context context, int i, List list)
    {
        super(context, i, list);
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        view = (TextView)super.getDropDownView(i, view, viewgroup);
        view.setText(((UserList)getItem(i)).getName());
        return view;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        view = (TextView)super.getView(i, view, viewgroup);
        view.setText(((UserList)getItem(i)).getName());
        return view;
    }
}
