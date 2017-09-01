// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.core.*;
import net.wakamesoba98.sobacha.database.MuteDatabase;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.NoRetweetApi;
import net.wakamesoba98.sobacha.twitter.api.UserApiDialog;
import net.wakamesoba98.sobacha.twitter.listener.OnRelationshipGotListener;
import net.wakamesoba98.sobacha.twitter.relationship.EnumRelationshipType;
import net.wakamesoba98.sobacha.twitter.relationship.RelationshipUtil;
import net.wakamesoba98.sobacha.twitter.userlist.UserListDialogManager;
import net.wakamesoba98.sobacha.view.card.PostCardManager;
import twitter4j.Relationship;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

public class UserMenu extends MenuBase
{

    public UserMenu(Context context1, PostCardManager postcardmanager, UserAccount useraccount, Relationship relationship1, OnRelationshipGotListener onrelationshipgotlistener)
    {
        context = context1;
        postCardManager = postcardmanager;
        userAccount = useraccount;
        relationship = relationship1;
        listener = onrelationshipgotlistener;
    }

    private void putMute(String s)
    {
        MuteDatabase mutedatabase = new MuteDatabase(context, "users");
        mutedatabase.openDatabase();
        mutedatabase.putString(s);
        mutedatabase.closeDatabase();
        ((SobaChaApplication)context.getApplicationContext()).loadPreferences(LoadMode.ONLY_MUTE);
    }

    private void putMuteThumbs(String s)
    {
        MuteDatabase mutedatabase = new MuteDatabase(context, "thumbs");
        mutedatabase.openDatabase();
        mutedatabase.putString(s);
        mutedatabase.closeDatabase();
        ((SobaChaApplication)context.getApplicationContext()).loadPreferences(LoadMode.ONLY_MUTE);
    }

    private void setMention(Relationship relationship1)
    {
        relationship1 = (new StringBuilder()).append("@").append(relationship1.getTargetUserScreenName()).append(" ").toString();
        postCardManager.setText((new StringBuilder()).append(relationship1).append(postCardManager.getText()).toString());
        postCardManager.showIme();
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist;
        RelationshipUtil relationshiputil;
        arraylist = new ArrayList();
        relationshiputil = new RelationshipUtil();
        String s = String.format(ResourceHelper.getString(context, 0x7f070103), new Object[] {
            (new StringBuilder()).append("@").append(relationship.getTargetUserScreenName()).toString()
        });
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.MENTION, "", 0x7f0200ea, s));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.MANAGE_USER_LISTS, "", 0x7f02010f, ResourceHelper.getString(context, 0x7f0700c2)));
        if(relationship.getTargetUserId() == relationship.getSourceUserId()) goto _L2; else goto _L1
_L1:
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[];
            static final int $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType = new int[EnumRelationshipType.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType[EnumRelationshipType.BLOCKING.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction = new int[EnumMenuAction.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MENTION.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.BLOCK.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.UNBLOCK.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.REPORT.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MUTE_USER.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MUTE_THUMB.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MUTE_RETWEET.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SHOW_RETWEET.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.MANAGE_USER_LISTS.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.twitter.relationship.EnumRelationshipType[relationshiputil.getRelationshipType(relationship).ordinal()])
        {
        default:
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.BLOCK, "", 0x7f020067, ResourceHelper.getString(context, 0x7f07002c)));
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_322;
        }
_L3:
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.REPORT, "", 0x7f020116, ResourceHelper.getString(context, 0x7f0700ee)));
        if(relationship.isSourceWantRetweets())
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_RETWEET, "", 0x7f0200bf, ResourceHelper.getString(context, 0x7f0700cb)));
        else
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_RETWEET, "", 0x7f0200f0, ResourceHelper.getString(context, 0x7f07010c)));
_L2:
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_USER, "", 0x7f0200bd, ResourceHelper.getString(context, 0x7f0700ca)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.MUTE_THUMB, "", 0x7f0200c1, ResourceHelper.getString(context, 0x7f0700aa)));
        return arraylist;
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.UNBLOCK, "", 0x7f0200d7, ResourceHelper.getString(context, 0x7f07012d)));
          goto _L3
    }

    protected void onMenuItemSelected(MenuItemWithIcon menuitemwithicon)
    {
        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.menu.EnumMenuAction[menuitemwithicon.getAction().ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            setMention(relationship);
            return;

        case 2: // '\002'
            (new UserApiDialog(userAccount)).createBlock(context, relationship.getSourceUserId(), relationship.getTargetUserId(), listener);
            return;

        case 3: // '\003'
            (new UserApiDialog(userAccount)).destroyBlock(context, relationship.getSourceUserId(), relationship.getTargetUserId(), listener);
            return;

        case 4: // '\004'
            (new UserApiDialog(userAccount)).reportSpam(context, relationship.getSourceUserId(), relationship.getTargetUserId(), listener);
            return;

        case 5: // '\005'
            putMute(relationship.getTargetUserScreenName());
            return;

        case 6: // '\006'
            putMuteThumbs(relationship.getTargetUserScreenName());
            return;

        case 7: // '\007'
            (new NoRetweetApi(context, userAccount)).setNoRetweetsUsers(userAccount, relationship.getTargetUserId(), false);
            return;

        case 8: // '\b'
            (new NoRetweetApi(context, userAccount)).setNoRetweetsUsers(userAccount, relationship.getTargetUserId(), true);
            return;

        case 9: // '\t'
            (new UserListDialogManager(context, userAccount)).show(relationship.getTargetUserId());
            break;
        }
    }

    public void show(View view)
    {
        show(context, view);
    }

    private Context context;
    private OnRelationshipGotListener listener;
    private PostCardManager postCardManager;
    private Relationship relationship;
    private UserAccount userAccount;
}
