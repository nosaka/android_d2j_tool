// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.tab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.view.tab:
//            TabManager, EnumViewPagerFragment, EnumTab

public class TabListCreator
{

    public TabListCreator()
    {
    }

    public TabManager createTab(Context context, ViewPagerFragment viewpagerfragment, List list, LinearLayout linearlayout, ViewPager viewpager)
    {
        context = new TabManager(context, viewpagerfragment);
        context.setTabList(list);
        context.setTabButton(linearlayout, viewpager);
        context.setHighlightButton(0);
        return context;
    }

    public List getTabList(EnumViewPagerFragment enumviewpagerfragment)
    {
        ArrayList arraylist = new ArrayList();
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment = new int[EnumViewPagerFragment.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.MAIN.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.USER_PAGE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.CONVERSATION.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.DIRECT_MESSAGE_CONVERSATION.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.SEARCH.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.USER_SEARCH.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.MAIN_TABLET_TIMELINE.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumViewPagerFragment[EnumViewPagerFragment.MAIN_TABLET_OTHER.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment[enumviewpagerfragment.ordinal()])
        {
        default:
            return arraylist;

        case 1: // '\001'
            arraylist.add(EnumTab.HOME);
            arraylist.add(EnumTab.MENTION);
            arraylist.add(EnumTab.USER_LIST);
            arraylist.add(EnumTab.SEARCH);
            arraylist.add(EnumTab.DIRECT_MESSAGE);
            return arraylist;

        case 2: // '\002'
            arraylist.add(EnumTab.DETAIL);
            arraylist.add(EnumTab.USER_TIMELINE);
            arraylist.add(EnumTab.FRIENDS);
            arraylist.add(EnumTab.FOLLOWERS);
            arraylist.add(EnumTab.FAVORITES);
            return arraylist;

        case 3: // '\003'
            arraylist.add(EnumTab.CONVERSATION);
            return arraylist;

        case 4: // '\004'
            arraylist.add(EnumTab.DM_CONVERSATION);
            return arraylist;

        case 5: // '\005'
            arraylist.add(EnumTab.SEARCH);
            return arraylist;

        case 6: // '\006'
            arraylist.add(EnumTab.USER_SEARCH);
            return arraylist;

        case 7: // '\007'
            arraylist.add(EnumTab.HOME);
            return arraylist;

        case 8: // '\b'
            arraylist.add(EnumTab.MENTION);
            break;
        }
        arraylist.add(EnumTab.USER_LIST);
        arraylist.add(EnumTab.SEARCH);
        arraylist.add(EnumTab.DIRECT_MESSAGE);
        return arraylist;
    }
}
