// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import java.util.*;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusActionFinishedListener;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusDeletedListener;
import net.wakamesoba98.sobacha.twitter.media.MediaIntentUtils;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import net.wakamesoba98.sobacha.view.activity.*;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.card.StatusCardManager;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction, HashTagMenu, 
//            StatusUrlMenu, AddMuteMenu

public class StatusMenu extends MenuBase
    implements OnStatusActionFinishedListener, OnStatusDeletedListener
{
    private class Elements
    {

        ExtendedMediaEntity extendedEntities[];
        MediaEntity mediaEntities[];
        long statusId;
        String statusUrl;
        final StatusMenu this$0;
        Map userIdMap;

        private Elements()
        {
            this$0 = StatusMenu.this;
            super();
        }

    }


    public StatusMenu(ViewPagerActivity viewpageractivity, StatusCardManager statuscardmanager, StatusItem statusitem, UserAccount useraccount)
    {
        activity = viewpageractivity;
        statusCardManager = statuscardmanager;
        statusItem = statusitem;
        account = useraccount;
        elements = new Elements();
    }

    private void delete(long l)
    {
        ((SobaChaApplication)activity.getApplication()).notifyDelete(l);
    }

    private void dismissProgress()
    {
        ((ProgressBar)activity.findViewById(0x7f0e0133)).setVisibility(8);
    }

    private void showConversation(ViewPagerActivity viewpageractivity, long l)
    {
        Bundle bundle = new Bundle();
        bundle.putLong("status_id", l);
        (new IntentUtil()).startActivityOrAddFragment(viewpageractivity, net/wakamesoba98/sobacha/view/activity/ConversationActivity, EnumViewPagerFragment.CONVERSATION, bundle);
    }

    private void showUserPage(ViewPagerActivity viewpageractivity, long l)
    {
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", l);
        (new IntentUtil()).startActivityOrAddFragment(viewpageractivity, net/wakamesoba98/sobacha/view/activity/UserPageActivity, EnumViewPagerFragment.USER_PAGE, bundle);
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        elements.userIdMap = new HashMap();
        if(statusItem.getStatusType() != EnumStatusType.PROFILE) goto _L2; else goto _L1
_L1:
        User user = statusItem.getUser();
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_USER, user.getScreenName(), 0x7f0200d7, (new StringBuilder()).append("@").append(user.getScreenName()).toString()));
        elements.userIdMap.put(user.getScreenName(), Long.valueOf(user.getId()));
_L8:
        return arraylist;
_L2:
        if(statusItem.getStatusType() != EnumStatusType.DIRECT_MESSAGE) goto _L4; else goto _L3
_L3:
        Object obj;
        Object obj1;
        Object obj2;
        DirectMessage directmessage = statusItem.getDirectMessage();
        User user1 = directmessage.getSender();
        obj2 = directmessage.getUserMentionEntities();
        obj1 = directmessage.getHashtagEntities();
        obj = directmessage.getURLEntities();
        elements.mediaEntities = directmessage.getMediaEntities();
        elements.extendedEntities = directmessage.getExtendedMediaEntities();
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_USER, user1.getScreenName(), 0x7f0200d7, (new StringBuilder()).append("@").append(user1.getScreenName()).toString()));
        elements.userIdMap.put(user1.getScreenName(), Long.valueOf(user1.getId()));
_L6:
        int i1 = obj2.length;
        for(int i = 0; i < i1; i++)
        {
            UserMentionEntity usermentionentity = obj2[i];
            if(!elements.userIdMap.keySet().contains(usermentionentity.getScreenName()))
            {
                arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_USER, usermentionentity.getScreenName(), 0x7f0200d7, (new StringBuilder()).append("@").append(usermentionentity.getScreenName()).toString()));
                elements.userIdMap.put(usermentionentity.getScreenName(), Long.valueOf(usermentionentity.getId()));
            }
        }

        break; /* Loop/switch isn't completed */
_L4:
        StatusUrlUtils statusurlutils = new StatusUrlUtils();
        obj2 = statusItem.getStatus();
        obj1 = statusItem.getStatus();
        Object obj4 = statusItem.getUser();
        obj = obj1;
        if(((Status) (obj1)).isRetweet())
            obj = statusItem.getStatus().getRetweetedStatus();
        elements.statusId = ((Status) (obj)).getId();
        elements.statusUrl = statusurlutils.createStatusUrl(((Status) (obj)).getUser().getScreenName(), elements.statusId);
        UserMentionEntity ausermentionentity[] = ((Status) (obj)).getUserMentionEntities();
        HashtagEntity ahashtagentity[] = ((Status) (obj)).getHashtagEntities();
        URLEntity aurlentity[] = ((Status) (obj)).getURLEntities();
        elements.mediaEntities = ((Status) (obj)).getMediaEntities();
        elements.extendedEntities = ((Status) (obj)).getExtendedMediaEntities();
        if(((Status) (obj)).getInReplyToStatusId() != -1L)
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_CONVERSATION, "", 0x7f020079, ResourceHelper.getString(activity, 0x7f07010a)));
        long l3 = ((User) (obj4)).getId();
        obj4 = ((User) (obj4)).getScreenName();
        long l2 = -1L;
        obj1 = "";
        obj = obj1;
        long l1 = l2;
        if(((Status) (obj2)).isRetweet())
        {
            obj = obj1;
            l1 = l2;
            if(((Status) (obj2)).getUser() != null)
            {
                l1 = ((Status) (obj2)).getUser().getId();
                obj = ((Status) (obj2)).getUser().getScreenName();
            }
        }
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_USER, ((String) (obj4)), 0x7f0200d7, (new StringBuilder()).append("@").append(((String) (obj4))).toString()));
        elements.userIdMap.put(obj4, Long.valueOf(l3));
        if(l1 > 0L && l1 != l3)
        {
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_USER, ((String) (obj)), 0x7f0200d7, (new StringBuilder()).append("@").append(((String) (obj))).toString()));
            elements.userIdMap.put(obj, Long.valueOf(l1));
        }
        obj4 = statusItem.getFavoritedBy();
        obj2 = ausermentionentity;
        obj1 = ahashtagentity;
        obj = aurlentity;
        if(obj4 != null)
        {
            obj2 = ausermentionentity;
            obj1 = ahashtagentity;
            obj = aurlentity;
            if(!elements.userIdMap.keySet().contains(((User) (obj4)).getScreenName()))
            {
                arraylist.add(new MenuItemWithIcon(EnumMenuAction.SHOW_USER, ((User) (obj4)).getScreenName(), 0x7f0200d7, (new StringBuilder()).append("@").append(((User) (obj4)).getScreenName()).toString()));
                elements.userIdMap.put(((User) (obj4)).getScreenName(), Long.valueOf(((User) (obj4)).getId()));
                obj2 = ausermentionentity;
                obj1 = ahashtagentity;
                obj = aurlentity;
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
        int j1 = obj1.length;
        for(int j = 0; j < j1; j++)
        {
            Object obj3 = obj1[j];
            obj3 = (new StringBuilder()).append("#").append(((HashtagEntity) (obj3)).getText()).toString();
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.OPEN_HASHTAG_MENU, ((String) (obj3)), 0x7f0200a3, ((String) (obj3))));
        }

        j1 = obj.length;
        for(int k = 0; k < j1; k++)
        {
            String s = obj[k].getExpandedURL();
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.OPEN_URL, s, 0x7f0200d3, s));
        }

        MediaEntity amediaentity[] = elements.mediaEntities;
        j1 = amediaentity.length;
        for(int l = 0; l < j1; l++)
        {
            String s1 = amediaentity[l].getExpandedURL();
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.OPEN_URL, s1, 0x7f0200db, s1));
        }

        if(statusItem.getStatus() != null)
        {
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.OPEN_STATUS_URL_MENU, "", 0x7f0200d3, ResourceHelper.getString(activity, 0x7f070114)));
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.OPEN_MUTE_MENU, "", 0x7f0200bd, ResourceHelper.getString(activity, 0x7f07001b)));
        }
        if(statusItem.isRetweetable())
        {
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.LIKE_AND_RETWEET, "", 0x7f0200ef, ResourceHelper.getString(activity, 0x7f0700b6)));
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.QUOTE, "", 0x7f0200e2, ResourceHelper.getString(activity, 0x7f0700e8)));
        }
        if(statusItem.isDeletable())
        {
            arraylist.add(new MenuItemWithIcon(EnumMenuAction.DELETE, "", 0x7f02007f, ResourceHelper.getString(activity, 0x7f07003d)));
            return arraylist;
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void deletedStatus(long l)
    {
        dismissProgress();
        if(l > 0L)
            delete(l);
    }

    public void finishedStatusAction()
    {
        dismissProgress();
    }

    protected void onMenuItemSelected(MenuItemWithIcon menuitemwithicon)
    {
        Object obj = (ProgressBar)activity.findViewById(0x7f0e0133);
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction = new int[EnumMenuAction.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SHOW_USER.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.OPEN_HASHTAG_MENU.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.OPEN_URL.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.LIKE_AND_RETWEET.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.QUOTE.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.DELETE.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.SHOW_CONVERSATION.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.OPEN_STATUS_URL_MENU.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.OPEN_MUTE_MENU.ordinal()] = 9;
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
            long l = ((Long)elements.userIdMap.get(menuitemwithicon.getValue())).longValue();
            showUserPage(activity, l);
            return;

        case 2: // '\002'
            (new HashTagMenu(activity, menuitemwithicon.getValue())).show(anchor);
            return;

        case 3: // '\003'
            (new MediaIntentUtils()).startActivityByMediaStatusURL(activity, menuitemwithicon.getValue(), statusItem.getUser().getScreenName(), elements.mediaEntities, elements.extendedEntities, anchor);
            return;

        case 4: // '\004'
            statusItem.favoriteRetweet(this, activity, account, ((ProgressBar) (obj)));
            return;

        case 5: // '\005'
            statusCardManager.quote();
            return;

        case 6: // '\006'
            statusItem.delete(this, activity, account, ((ProgressBar) (obj)));
            return;

        case 7: // '\007'
            showConversation(activity, elements.statusId);
            return;

        case 8: // '\b'
            (new StatusUrlMenu(activity, elements.statusUrl)).show(anchor);
            return;

        case 9: // '\t'
            menuitemwithicon = elements.userIdMap.keySet();
            break;
        }
        menuitemwithicon = (String[])menuitemwithicon.toArray(new String[menuitemwithicon.size()]);
        obj = new StatusUrlUtils();
        (new AddMuteMenu(activity, statusItem.getStatus(), menuitemwithicon, ((StatusUrlUtils) (obj)).getClientName(statusItem.getStatus()))).show(anchor);
    }

    public void show(View view)
    {
        anchor = view;
        show(((android.content.Context) (activity)), view);
    }

    private UserAccount account;
    private ViewPagerActivity activity;
    private View anchor;
    private Elements elements;
    private StatusCardManager statusCardManager;
    private StatusItem statusItem;
}
