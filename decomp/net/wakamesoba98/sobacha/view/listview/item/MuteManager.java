// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.item;

import java.util.Iterator;
import java.util.Set;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import twitter4j.Status;
import twitter4j.User;

// Referenced classes of package net.wakamesoba98.sobacha.view.listview.item:
//            StatusItem, EnumStatusType

class MuteManager
{

    MuteManager()
    {
    }

    boolean isMuteTarget(UserAccount useraccount, StatusItem statusitem)
    {
        Object obj;
        obj = statusitem.getStatus();
        if(statusitem.getStatus().isRetweet())
            obj = statusitem.getStatus().getRetweetedStatus();
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType = new int[EnumStatusType.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.RETWEETED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.FAVORITED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.MENTION.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[statusitem.getStatusType().ordinal()];
        JVM INSTR tableswitch 1 2: default 64
    //                   1 93
    //                   2 211;
           goto _L1 _L2 _L3
_L1:
        if(!useraccount.getMuteUsers().contains(((Status) (obj)).getUser().getScreenName().toLowerCase())) goto _L5; else goto _L4
_L4:
        return true;
_L2:
        if(!useraccount.getMuteUsers().contains(statusitem.getStatus().getUser().getScreenName().toLowerCase()) && !useraccount.getBlockingIds().contains(Long.valueOf(statusitem.getStatus().getUser().getId())) && !useraccount.getOfficialMuteUsers().contains(Long.valueOf(statusitem.getStatus().getUser().getId())))
        {
            if(useraccount.getMuteRetweets().contains(Long.valueOf(statusitem.getStatus().getUser().getId())))
                return true;
            continue; /* Loop/switch isn't completed */
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!useraccount.getMuteUsers().contains(statusitem.getFavoritedBy().getScreenName().toLowerCase()) && !useraccount.getBlockingIds().contains(Long.valueOf(statusitem.getFavoritedBy().getId())))
        {
            if(useraccount.getOfficialMuteUsers().contains(Long.valueOf(statusitem.getFavoritedBy().getId())))
                return true;
            continue; /* Loop/switch isn't completed */
        }
        if(true) goto _L4; else goto _L5
_L5:
        Iterator iterator = useraccount.getMuteUsers().iterator();
        String s1;
        do
        {
            if(!iterator.hasNext())
                continue; /* Loop/switch isn't completed */
            s1 = (String)iterator.next();
        } while(!statusitem.getText().contains((new StringBuilder()).append("@").append(s1).toString()));
        return true;
        if(useraccount.getBlockingIds().contains(Long.valueOf(((Status) (obj)).getUser().getId())) || useraccount.getOfficialMuteUsers().contains(Long.valueOf(((Status) (obj)).getUser().getId()))) goto _L4; else goto _L6
_L6:
        String s = (new StatusUrlUtils()).getClientName(statusitem.getStatus());
        if(useraccount.getMuteClients().contains(s)) goto _L4; else goto _L7
_L7:
        long l1;
        if(!useraccount.isNoiseCancelMode() || useraccount.getFriends().size() <= 1)
            break; /* Loop/switch isn't completed */
        l1 = -1L;
        _cls1..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[statusitem.getStatusType().ordinal()];
        JVM INSTR tableswitch 1 3: default 496
    //                   1 601
    //                   2 642
    //                   3 565;
           goto _L8 _L9 _L10 _L11
_L8:
        long l = l1;
_L17:
        if(l > 0L && !useraccount.getFriends().contains(Long.valueOf(l))) goto _L4; else goto _L12
_L12:
        useraccount = useraccount.getMuteWords().iterator();
_L16:
        if(!useraccount.hasNext()) goto _L14; else goto _L13
_L13:
        obj = (String)useraccount.next();
        if(!statusitem.getText().contains(((CharSequence) (obj)))) goto _L16; else goto _L15
_L15:
        return true;
_L11:
        l = l1;
        if(((Status) (obj)).getUser().getId() != useraccount.getId())
            l = statusitem.getUser().getId();
          goto _L17
_L9:
        l = l1;
        if(((Status) (obj)).getUser().getId() == useraccount.getId())
            l = statusitem.getStatus().getUser().getId();
          goto _L17
_L10:
        l = l1;
        if(statusitem.getFavoritedBy() != null)
        {
            l = l1;
            if(((Status) (obj)).getUser().getId() == useraccount.getId())
                l = statusitem.getFavoritedBy().getId();
        }
          goto _L17
_L14:
        return false;
        if(true) goto _L1; else goto _L18
_L18:
    }

    boolean isThumbMuteTarget(UserAccount useraccount, StatusItem statusitem)
    {
        Status status;
        status = statusitem.getStatus();
        if(statusitem.getStatus().isRetweet())
            status = statusitem.getStatus().getRetweetedStatus();
        _cls1..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[statusitem.getStatusType().ordinal()];
        JVM INSTR tableswitch 1 1: default 56
    //                   1 84;
           goto _L1 _L2
_L1:
        return useraccount.getMuteThumbs().contains(status.getUser().getScreenName().toLowerCase());
_L2:
        if(useraccount.getMuteThumbs().contains(statusitem.getStatus().getUser().getScreenName().toLowerCase()))
            return true;
          goto _L1
    }
}
