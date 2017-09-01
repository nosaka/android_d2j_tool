// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.view.activity.*;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

public class ProfileButtonMenu extends MenuBase
{

    public ProfileButtonMenu(ViewPagerActivity viewpageractivity, long l)
    {
        activity = viewpageractivity;
        myUserId = l;
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.PROFILE, "", 0x7f0200d7, ResourceHelper.getString(activity, 0x7f0700e3)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.ACCOUNT_SETTING, "", 0x7f020057, ResourceHelper.getString(activity, 0x7f070018)));
        return arraylist;
    }

    protected void onMenuItemSelected(MenuItemWithIcon menuitemwithicon)
    {
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction = new int[EnumMenuAction.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.PROFILE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.ACCOUNT_SETTING.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.menu.EnumMenuAction[menuitemwithicon.getAction().ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            menuitemwithicon = new Bundle();
            menuitemwithicon.putLong("user_id", myUserId);
            (new IntentUtil()).startActivityOrAddFragment(activity, net/wakamesoba98/sobacha/view/activity/UserPageActivity, EnumViewPagerFragment.USER_PAGE, menuitemwithicon);
            return;

        case 2: // '\002'
            menuitemwithicon = new Intent(activity, net/wakamesoba98/sobacha/view/activity/AccountsActivity);
            break;
        }
        activity.startActivity(menuitemwithicon);
    }

    public void show(View view)
    {
        show(((android.content.Context) (activity)), view);
    }

    private ViewPagerActivity activity;
    private long myUserId;
}
