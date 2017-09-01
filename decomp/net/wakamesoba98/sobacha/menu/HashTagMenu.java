// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.view.activity.SearchActivity;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

class HashTagMenu extends MenuBase
{

    HashTagMenu(ViewPagerActivity viewpageractivity, String s)
    {
        activity = viewpageractivity;
        hashTag = s;
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.SEARCH_HASHTAG, hashTag, 0x7f0200f8, String.format(ResourceHelper.getString(activity, 0x7f0700fb), new Object[] {
            hashTag
        })));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.POST_WITH_HASHTAG, hashTag, 0x7f020077, String.format(ResourceHelper.getString(activity, 0x7f0700e1), new Object[] {
            hashTag
        })));
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
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SEARCH_HASHTAG.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.POST_WITH_HASHTAG.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.menu.EnumMenuAction[menuitemwithicon.getAction().ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 33
    //                   2 72;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        Bundle bundle = new Bundle();
        bundle.putString("query", menuitemwithicon.getValue());
        (new IntentUtil()).startActivityOrAddFragment(activity, net/wakamesoba98/sobacha/view/activity/SearchActivity, EnumViewPagerFragment.SEARCH, bundle);
        return;
_L3:
        EditText edittext = (EditText)activity.findViewById(0x7f0e009c);
        if(edittext != null)
        {
            String s = edittext.getText().toString();
            (new ImeUtil()).showIme(activity, edittext);
            edittext.setText((new StringBuilder()).append(s).append(" ").append(menuitemwithicon.getValue()).toString());
            return;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void show(View view)
    {
        show(((android.content.Context) (activity)), view);
    }

    private ViewPagerActivity activity;
    private String hashTag;
}
