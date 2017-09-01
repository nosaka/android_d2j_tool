// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.os.Bundle;
import android.view.View;
import java.util.*;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.database.SavedSearchesDatabase;
import net.wakamesoba98.sobacha.database.data.IdWithNameData;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.SavedSearchApi;
import net.wakamesoba98.sobacha.twitter.api.TrendsApi;
import net.wakamesoba98.sobacha.view.activity.UserSearchActivity;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.fragment.pulltorefresh.SearchFragment;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

public class SavedSearchesMenu extends MenuBase
{

    public SavedSearchesMenu(ViewPagerActivity viewpageractivity, UserAccount useraccount, SearchFragment searchfragment, String s)
    {
        activity = viewpageractivity;
        userAccount = useraccount;
        fragment = searchfragment;
        text = s;
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.RELOAD, "", 0x7f0200e4, ResourceHelper.getString(activity, 0x7f0700ec)));
        Object obj = new SavedSearchesDatabase(activity, userAccount.getId());
        ((SavedSearchesDatabase) (obj)).openDatabase();
        List list = ((SavedSearchesDatabase) (obj)).getSavedSearches();
        ((SavedSearchesDatabase) (obj)).closeDatabase();
        boolean flag = false;
        obj = list.iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            IdWithNameData idwithnamedata = (IdWithNameData)((Iterator) (obj)).next();
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.SEARCH_WORD, idwithnamedata.getName(), 0x7f0200f8, idwithnamedata.getName()));
            if(idwithnamedata.getName().equals(text))
                flag = true;
        } while(true);
        if(text != null && !text.trim().equals(""))
            if(flag)
                arraylist.add(new MenuItemWithIcon(EnumMenuAction.DELETE_THIS_SEARCH, "", 0x7f020081, ResourceHelper.getString(activity, 0x7f070040)));
            else
                arraylist.add(new MenuItemWithIcon(EnumMenuAction.SAVE_THIS_SEARCH, "", 0x7f020061, ResourceHelper.getString(activity, 0x7f0700f8)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_TRENDS, "", 0x7f020105, ResourceHelper.getString(activity, 0x7f07010d)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.SEARCH_USER, "", 0x7f0200d7, ResourceHelper.getString(activity, 0x7f0700fd)));
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
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SHOW_TRENDS.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SAVE_THIS_SEARCH.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.DELETE_THIS_SEARCH.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.RELOAD.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SEARCH_USER.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SEARCH_WORD.ordinal()] = 6;
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
            (new TrendsApi(activity, userAccount)).getTrend(fragment, anchor);
            return;

        case 2: // '\002'
        case 3: // '\003'
            fragment.manageSavedSearch();
            return;

        case 4: // '\004'
            (new SavedSearchApi(activity, userAccount)).getSavedSearches(fragment, text, anchor);
            return;

        case 5: // '\005'
            menuitemwithicon = new Bundle();
            (new IntentUtil()).startActivityOrAddFragment(activity, net/wakamesoba98/sobacha/view/activity/UserSearchActivity, EnumViewPagerFragment.USER_SEARCH, menuitemwithicon);
            return;

        case 6: // '\006'
            fragment.search(menuitemwithicon.getValue());
            break;
        }
    }

    public void show(View view)
    {
        anchor = view;
        show(((android.content.Context) (activity)), view);
    }

    private ViewPagerActivity activity;
    private View anchor;
    private SearchFragment fragment;
    private String text;
    private UserAccount userAccount;
}
