// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.app.Activity;
import android.view.View;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.core.*;
import net.wakamesoba98.sobacha.database.MuteDatabase;
import net.wakamesoba98.sobacha.twitter.api.NoRetweetApi;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

class AddMuteMenu extends MenuBase
{

    AddMuteMenu(Activity activity1, Status status1, String as[], String s)
    {
        activity = activity1;
        status = status1;
        users = as;
        client = s;
    }

    private void addMuteDatabase(String s, String s1)
    {
        s = new MuteDatabase(activity, s);
        s.openDatabase();
        s.putString(s1);
        s.closeDatabase();
        ((SobaChaApplication)activity.getApplication()).loadPreferences(LoadMode.ONLY_MUTE);
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        Status status1 = status;
        if(status.isRetweet())
            status1 = status.getRetweetedStatus();
        HashtagEntity ahashtagentity[] = status1.getHashtagEntities();
        twitter4j.MediaEntity amediaentity[] = status1.getMediaEntities();
        String as1[] = users;
        int l = as1.length;
        for(int i = 0; i < l; i++)
        {
            String s1 = as1[i];
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_USER, s1, 0x7f0200d7, String.format(ResourceHelper.getString(activity, 0x7f070138), new Object[] {
                s1
            })));
        }

        if(status.isRetweet())
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_RETWEET, String.valueOf(status.getUser().getId()), 0x7f0200f0, String.format(ResourceHelper.getString(activity, 0x7f0700f2), new Object[] {
                status.getUser().getScreenName()
            })));
        if(amediaentity.length > 0)
        {
            String as[] = users;
            l = as.length;
            for(int j = 0; j < l; j++)
            {
                String s = as[j];
                arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_THUMB, s, 0x7f0200db, String.format(ResourceHelper.getString(activity, 0x7f070120), new Object[] {
                    s
                })));
            }

        }
        l = ahashtagentity.length;
        for(int k = 0; k < l; k++)
        {
            Object obj = ahashtagentity[k];
            obj = (new StringBuilder()).append("#").append(((HashtagEntity) (obj)).getText()).toString();
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_HASHTAG, ((String) (obj)), 0x7f0200a3, String.format(ResourceHelper.getString(activity, 0x7f0700a7), new Object[] {
                obj
            })));
        }

        if(client != null)
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_CLIENT, client, 0x7f020079, String.format(ResourceHelper.getString(activity, 0x7f070033), new Object[] {
                client
            })));
        return arraylist;
    }

    protected void onMenuItemSelected(MenuItemWithIcon menuitemwithicon)
    {
        String s;
        SobaChaApplication sobachaapplication;
        sobachaapplication = (SobaChaApplication)activity.getApplication();
        if(menuitemwithicon.getAction() == EnumMenuAction.MUTE_RETWEET)
        {
            net.wakamesoba98.sobacha.preference.prefs.UserAccount useraccount = sobachaapplication.getUserAccount();
            (new NoRetweetApi(activity, useraccount)).setNoRetweetsUsers(useraccount, Long.parseLong(menuitemwithicon.getValue()), false);
            return;
        }
        s = "";
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction = new int[EnumMenuAction.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MUTE_USER.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MUTE_THUMB.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MUTE_HASHTAG.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MUTE_CLIENT.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.menu.EnumMenuAction[menuitemwithicon.getAction().ordinal()];
        JVM INSTR tableswitch 1 4: default 96
    //                   1 113
    //                   2 119
    //                   3 125
    //                   4 131;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        addMuteDatabase(s, menuitemwithicon.getValue());
        sobachaapplication.loadPreferences(LoadMode.ONLY_MUTE);
        return;
_L2:
        s = "users";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "thumbs";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "words";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "clients";
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void show(View view)
    {
        show(((android.content.Context) (activity)), view);
    }

    private Activity activity;
    private String client;
    private Status status;
    private String users[];
}
