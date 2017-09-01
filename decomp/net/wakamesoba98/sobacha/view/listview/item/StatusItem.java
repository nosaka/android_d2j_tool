// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.item;

import android.app.Activity;
import android.widget.ProgressBar;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.StatusActionApiDialog;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusActionFinishedListener;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusDeletedListener;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.view.listview.item:
//            EnumStatusType, MuteManager

public class StatusItem
{

    public StatusItem()
    {
        user = null;
        favoritedBy = null;
        status = null;
        message = null;
        isDeletable = false;
        isRetweetable = false;
        isMuteTarget = false;
        isThumbMuteTarget = false;
        statusType = EnumStatusType.READ_MORE;
    }

    public StatusItem(UserAccount useraccount, String s)
        throws TwitterException
    {
        json = s;
        status(useraccount, TwitterObjectFactory.createStatus(s), null);
    }

    public StatusItem(UserAccount useraccount, Status status1)
    {
        json = TwitterObjectFactory.getRawJSON(status1);
        status(useraccount, status1, null);
    }

    public StatusItem(UserAccount useraccount, Status status1, User user1)
    {
        json = TwitterObjectFactory.getRawJSON(status1);
        status(useraccount, status1, user1);
    }

    public StatusItem(DirectMessage directmessage, boolean flag)
    {
        json = TwitterObjectFactory.getRawJSON(directmessage);
        user = directmessage.getSender();
        favoritedBy = null;
        status = null;
        message = directmessage;
        isDeletable = true;
        isRetweetable = false;
        isMuteTarget = false;
        isThumbMuteTarget = false;
        text = (new StatusUrlUtils()).replaceToDisplayURL(directmessage);
        if(flag)
        {
            statusType = EnumStatusType.DIRECT_MESSAGE;
            return;
        } else
        {
            statusType = EnumStatusType.DIRECT_MESSAGE_USER;
            return;
        }
    }

    public StatusItem(User user1)
    {
        json = TwitterObjectFactory.getRawJSON(user1);
        user = user1;
        favoritedBy = null;
        status = null;
        message = null;
        isDeletable = false;
        isRetweetable = false;
        isMuteTarget = false;
        isThumbMuteTarget = false;
        text = getUser().getDescription();
        statusType = EnumStatusType.PROFILE;
    }

    private boolean isMention(UserAccount useraccount, Status status1)
    {
        boolean flag1 = false;
        status1 = status1.getUserMentionEntities();
        int j = status1.length;
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if(i < j)
                {
                    if(status1[i].getId() != useraccount.getId())
                        break label0;
                    flag = true;
                }
                return flag;
            }
            i++;
        } while(true);
    }

    private void status(UserAccount useraccount, Status status1, User user1)
    {
        boolean flag = true;
        Status status2 = status1;
        if(status1.isRetweet())
            status2 = status1.getRetweetedStatus();
        user = status2.getUser();
        favoritedBy = user1;
        status = status1;
        message = null;
        if(status1.getUser().getId() == useraccount.getId())
        {
            isDeletable = true;
            isRetweetable = true;
        } else
        {
            isDeletable = false;
            if(status2.getUser().isProtected())
                flag = false;
            isRetweetable = flag;
        }
        if(user1 != null)
            statusType = EnumStatusType.FAVORITED;
        else
        if(status1.isRetweet())
            statusType = EnumStatusType.RETWEETED;
        else
        if(isMention(useraccount, status2))
            statusType = EnumStatusType.MENTION;
        else
            statusType = EnumStatusType.NORMAL;
        text = (new StatusUrlUtils()).replaceToDisplayURL(status2);
        applyMute(useraccount, new MuteManager());
    }

    boolean applyMute(UserAccount useraccount, MuteManager mutemanager)
    {
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType = new int[EnumStatusType.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.FAVORITED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.RETWEETED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.MENTION.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.NORMAL.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[statusType.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 45
    //                   2 45
    //                   3 45
    //                   4 45;
           goto _L1 _L2 _L2 _L2 _L2
_L1:
        return isMuteTarget;
_L2:
        isMuteTarget = mutemanager.isMuteTarget(useraccount, this);
        isThumbMuteTarget = mutemanager.isThumbMuteTarget(useraccount, this);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void delete(OnStatusDeletedListener onstatusdeletedlistener, Activity activity, UserAccount useraccount, ProgressBar progressbar)
    {
        StatusUrlUtils statusurlutils = new StatusUrlUtils();
        if(statusType == EnumStatusType.DIRECT_MESSAGE)
        {
            if(message != null)
                (new StatusActionApiDialog(activity, useraccount, progressbar)).destroyMessage(onstatusdeletedlistener, message.getId(), statusurlutils.replaceToDisplayURL(message));
        } else
        if(status != null)
        {
            (new StatusActionApiDialog(activity, useraccount, progressbar)).destroy(onstatusdeletedlistener, status.getId(), statusurlutils.replaceToDisplayURL(status));
            return;
        }
    }

    public void favorite(OnStatusActionFinishedListener onstatusactionfinishedlistener, Activity activity, UserAccount useraccount, ProgressBar progressbar)
    {
        StatusUrlUtils statusurlutils = new StatusUrlUtils();
        (new StatusActionApiDialog(activity, useraccount, progressbar)).favorite(onstatusactionfinishedlistener, status.getId(), statusurlutils.replaceToDisplayURL(status));
    }

    public void favoriteRetweet(OnStatusActionFinishedListener onstatusactionfinishedlistener, Activity activity, UserAccount useraccount, ProgressBar progressbar)
    {
        StatusUrlUtils statusurlutils = new StatusUrlUtils();
        (new StatusActionApiDialog(activity, useraccount, progressbar)).favoriteRetweet(onstatusactionfinishedlistener, status.getId(), statusurlutils.replaceToDisplayURL(status));
    }

    public DirectMessage getDirectMessage()
    {
        return message;
    }

    public User getFavoritedBy()
    {
        return favoritedBy;
    }

    public String getJson()
    {
        return json;
    }

    public Status getStatus()
    {
        return status;
    }

    public EnumStatusType getStatusType()
    {
        return statusType;
    }

    public String getText()
    {
        return text;
    }

    public User getUser()
    {
        return user;
    }

    public boolean isDeletable()
    {
        return isDeletable;
    }

    public boolean isMuteTarget()
    {
        return isMuteTarget;
    }

    public boolean isRetweetable()
    {
        return isRetweetable;
    }

    public boolean isThumbMuteTarget()
    {
        return isThumbMuteTarget;
    }

    public void retweet(OnStatusActionFinishedListener onstatusactionfinishedlistener, Activity activity, UserAccount useraccount, ProgressBar progressbar)
    {
        StatusUrlUtils statusurlutils = new StatusUrlUtils();
        (new StatusActionApiDialog(activity, useraccount, progressbar)).retweet(onstatusactionfinishedlistener, status.getId(), statusurlutils.replaceToDisplayURL(status));
    }

    public void unFavorite(OnStatusActionFinishedListener onstatusactionfinishedlistener, Activity activity, UserAccount useraccount, ProgressBar progressbar)
    {
        StatusUrlUtils statusurlutils = new StatusUrlUtils();
        (new StatusActionApiDialog(activity, useraccount, progressbar)).unFavorite(onstatusactionfinishedlistener, status.getId(), statusurlutils.replaceToDisplayURL(status));
    }

    private User favoritedBy;
    private boolean isDeletable;
    private boolean isMuteTarget;
    private boolean isRetweetable;
    private boolean isThumbMuteTarget;
    private String json;
    private DirectMessage message;
    private Status status;
    private EnumStatusType statusType;
    private String text;
    private User user;
}
