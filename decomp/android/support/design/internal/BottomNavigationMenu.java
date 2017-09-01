// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.internal;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.MenuItem;
import android.view.SubMenu;

public final class BottomNavigationMenu extends MenuBuilder
{

    public BottomNavigationMenu(Context context)
    {
        super(context);
    }

    protected MenuItem addInternal(int i, int j, int k, CharSequence charsequence)
    {
        if(size() + 1 > 5)
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        stopDispatchingItemsChanged();
        charsequence = super.addInternal(i, j, k, charsequence);
        if(charsequence instanceof MenuItemImpl)
            ((MenuItemImpl)charsequence).setExclusiveCheckable(true);
        startDispatchingItemsChanged();
        return charsequence;
    }

    public SubMenu addSubMenu(int i, int j, int k, CharSequence charsequence)
    {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }

    public static final int MAX_ITEM_COUNT = 5;
}
