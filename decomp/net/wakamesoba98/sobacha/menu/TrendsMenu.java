// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.view.fragment.pulltorefresh.SearchFragment;
import twitter4j.Trend;
import twitter4j.Trends;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

public class TrendsMenu extends MenuBase
{

    public TrendsMenu(Context context1, Trends trends1, SearchFragment searchfragment)
    {
        context = context1;
        trends = trends1;
        fragment = searchfragment;
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        Trend atrend[] = trends.getTrends();
        int j = atrend.length;
        for(int i = 0; i < j; i++)
        {
            Trend trend = atrend[i];
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.SEARCH_TREND, trend.getName(), 0x7f020105, trend.getName()));
        }

        return arraylist;
    }

    protected void onMenuItemSelected(MenuItemWithIcon menuitemwithicon)
    {
        fragment.search(menuitemwithicon.getValue());
    }

    public void show(View view)
    {
        show(context, view);
    }

    private Context context;
    private SearchFragment fragment;
    private Trends trends;
}
