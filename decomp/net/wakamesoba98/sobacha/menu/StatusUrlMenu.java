// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.compatible.ClipboardHelper;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import net.wakamesoba98.sobacha.view.activity.ConversationActivity;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

public class StatusUrlMenu extends MenuBase
{

    public StatusUrlMenu(ViewPagerActivity viewpageractivity, String s)
    {
        activity = viewpageractivity;
        url = s;
    }

    private void showStatus(ViewPagerActivity viewpageractivity, String s)
    {
        long l = (new StatusUrlUtils()).getStatusId(s);
        if(l < 0L)
        {
            return;
        } else
        {
            s = new Bundle();
            s.putLong("status_id", l);
            (new IntentUtil()).startActivityOrAddFragment(viewpageractivity, net/wakamesoba98/sobacha/view/activity/ConversationActivity, EnumViewPagerFragment.CONVERSATION, s);
            return;
        }
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_TWEET, "", 0x7f020079, ResourceHelper.getString(activity, 0x7f07010e)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.OPEN_IN_BROWSER, "", 0x7f0200d3, ResourceHelper.getString(activity, 0x7f0700d5)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.COPY_TO_CLIPBOARD, "", 0x7f02007d, ResourceHelper.getString(activity, 0x7f07003a)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.TWEET_THIS_URL, "", 0x7f020077, ResourceHelper.getString(activity, 0x7f070127)));
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
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SHOW_TWEET.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.OPEN_IN_BROWSER.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.COPY_TO_CLIPBOARD.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.TWEET_THIS_URL.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.menu.EnumMenuAction[menuitemwithicon.getAction().ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 41
    //                   2 54
    //                   3 80
    //                   4 101;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        showStatus(activity, url);
        return;
_L3:
        menuitemwithicon = new Intent("android.intent.action.VIEW", Uri.parse(url));
        activity.startActivity(menuitemwithicon);
        return;
_L4:
        (new ClipboardHelper()).copy(activity, "Status URL", url);
        return;
_L5:
        menuitemwithicon = (EditText)activity.findViewById(0x7f0e009c);
        if(menuitemwithicon != null)
        {
            String s = menuitemwithicon.getText().toString();
            (new ImeUtil()).showIme(activity, menuitemwithicon);
            menuitemwithicon.setText((new StringBuilder()).append(s).append(" ").append(url).toString());
            return;
        }
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void show(View view)
    {
        show(((android.content.Context) (activity)), view);
    }

    private ViewPagerActivity activity;
    private String url;
}
