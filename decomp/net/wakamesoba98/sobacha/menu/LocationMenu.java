// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.app.Activity;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import twitter4j.Location;
import twitter4j.ResponseList;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

public class LocationMenu extends MenuBase
{

    public LocationMenu(Activity activity1, ResponseList responselist)
    {
        activity = activity1;
        locations = responselist;
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        Location location;
        for(Iterator iterator = locations.iterator(); iterator.hasNext(); arraylist.add(new MenuItemWithIcon(EnumMenuAction.LOCATION, String.valueOf(location.getWoeid()), 0x7f020105, location.getName())))
            location = (Location)iterator.next();

        return arraylist;
    }

    protected void onMenuItemSelected(MenuItemWithIcon menuitemwithicon)
    {
        PreferenceUtil.putPreference(activity, EnumPrefs.TREND_LOCATION, Integer.valueOf(menuitemwithicon.getValue()));
    }

    public void show(View view)
    {
        show(((android.content.Context) (activity)), view);
    }

    private Activity activity;
    private ResponseList locations;
}
