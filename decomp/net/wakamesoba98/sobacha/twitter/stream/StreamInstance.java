// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.stream;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.Set;
import java.util.Timer;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.notification.OverlayNotification;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.StreamPreferences;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.timer.OffTimerTask;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import net.wakamesoba98.sobacha.view.listview.adapter.AnimationStatusAdapter;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.stream:
//            StreamManager

class StreamInstance
{
    private class MyUserStreamAdapter extends UserStreamAdapter
    {

        private boolean isUserListMember(Status status)
        {
            return userListMembers != null && userListMembers.contains(Long.valueOf(status.getUser().getId()));
        }

        private void removeLastItem(StatusAdapter statusadapter)
        {
            if(statusadapter != null && statusadapter.getCount() >= 1000)
                for(; statusadapter.getCount() > 1000; statusadapter.remove(statusadapter.getItem(statusadapter.getCount() - 1)));
        }

        void deleteStatus(long l)
        {
            (new Handler(Looper.getMainLooper())).post(l. new Runnable() {

                public void run()
                {
                    if(home != null && mention != null && userList != null)
                    {
                        home.remove(statusId);
                        mention.remove(statusId);
                        userList.remove(statusId);
                    }
                }

                final MyUserStreamAdapter this$1;
                final long val$statusId;

            
            {
                this$1 = final_myuserstreamadapter;
                statusId = J.this;
                super();
            }
            }
);
        }

        public void onDeletionNotice(StatusDeletionNotice statusdeletionnotice)
        {
            super.onDeletionNotice(statusdeletionnotice);
            deleteStatus(statusdeletionnotice.getStatusId());
        }

        public void onDirectMessage(DirectMessage directmessage)
        {
label0:
            {
                super.onDirectMessage(directmessage);
                if(directmessage != null && directmessage.getSenderId() != myUserId)
                {
                    directmessage = new StatusItem(directmessage, false);
                    if(!directmessage.isMuteTarget())
                        break label0;
                }
                return;
            }
            (new Handler(Looper.getMainLooper())).post(directmessage. new Runnable() {

                public void run()
                {
                    if(message != null)
                    {
                        removeLastItem(message);
                        message.insert(item, 0);
                        if(isShowMessage)
                            notification.show(item.getDirectMessage().getSender().getScreenName(), item.getStatusType());
                    }
                }

                final MyUserStreamAdapter this$1;
                final StatusItem val$item;

            
            {
                this$1 = final_myuserstreamadapter;
                item = StatusItem.this;
                super();
            }
            }
);
        }

        public void onException(Exception exception)
        {
            exception.printStackTrace();
            if(!isFirstShow) goto _L2; else goto _L1
_L1:
            if(exception.getMessage() == null) goto _L4; else goto _L3
_L3:
            if(!exception.getMessage().contains("401")) goto _L6; else goto _L5
_L5:
            exception = ResourceHelper.getString(context, 0x7f070059);
            Notificator.toast(context, 0x7f070076, exception);
_L8:
            isFirstShow = false;
_L2:
            return;
_L6:
            if(exception.getMessage().contains("420"))
            {
                exception = ResourceHelper.getString(context, 0x7f07006c);
                Notificator.toast(context, 0x7f070076, exception);
            }
            continue; /* Loop/switch isn't completed */
_L4:
            Notificator.toast(context, 0x7f070076);
            if(true) goto _L8; else goto _L7
_L7:
        }

        public void onFavorite(User user1, final User item, Status status)
        {
label0:
            {
                super.onFavorite(user1, item, status);
                if(status != null && isShowFavorited)
                {
                    item = new StatusItem(userAccount, status, user1);
                    if(!item.isMuteTarget())
                        break label0;
                }
                return;
            }
            (new Handler(Looper.getMainLooper())).post(user1. new Runnable() {

                public void run()
                {
                    if(home != null)
                    {
                        removeLastItem(home);
                        home.insertWithAnimation(item, 0);
                        if(source.getId() != myUserId)
                            notification.show(item.getFavoritedBy().getScreenName(), item.getStatusType());
                    }
                }

                final MyUserStreamAdapter this$1;
                final StatusItem val$item;
                final User val$source;

            
            {
                this$1 = final_myuserstreamadapter;
                item = statusitem;
                source = User.this;
                super();
            }
            }
);
        }

        public void onStatus(Status status)
        {
            final StatusItem item;
label0:
            {
                super.onStatus(status);
                if(status != null)
                {
                    item = new StatusItem(userAccount, status);
                    if(!item.isMuteTarget())
                        break label0;
                }
                return;
            }
            boolean flag = isUserListMember(status);
            static class _cls3
            {

                static final int $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[];

                static 
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType = new int[EnumStatusType.values().length];
                    try
                    {
                        $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.MENTION.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.RETWEETED.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror)
                    {
                        return;
                    }
                }
            }

            switch(_cls3..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[item.getStatusType().ordinal()])
            {
            default:
                (new Handler(Looper.getMainLooper())).post(flag. new Runnable() {

                    public void run()
                    {
                        if(home != null && userList != null)
                        {
                            removeLastItem(home);
                            home.insertWithAnimation(item, 0);
                            if(userListMember)
                            {
                                removeLastItem(userList);
                                userList.insert(item, 0);
                            }
                        }
                    }

                    final MyUserStreamAdapter this$1;
                    final StatusItem val$item;
                    final boolean val$userListMember;

            
            {
                this$1 = final_myuserstreamadapter;
                item = statusitem;
                userListMember = Z.this;
                super();
            }
                }
);
                return;

            case 1: // '\001'
                (new Handler(Looper.getMainLooper())).post(flag. new Runnable() {

                    public void run()
                    {
                        if(home != null && mention != null && userList != null)
                        {
                            removeLastItem(home);
                            home.insertWithAnimation(item, 0);
                            removeLastItem(mention);
                            mention.insert(item, 0);
                            if(userListMember)
                            {
                                removeLastItem(userList);
                                userList.insert(item, 0);
                            }
                            if(isShowMention && item.getUser().getId() != myUserId)
                                notification.show(item.getUser().getScreenName(), item.getStatusType());
                        }
                    }

                    final MyUserStreamAdapter this$1;
                    final StatusItem val$item;
                    final boolean val$userListMember;

            
            {
                this$1 = final_myuserstreamadapter;
                item = statusitem;
                userListMember = Z.this;
                super();
            }
                }
);
                return;

            case 2: // '\002'
                (new Handler(Looper.getMainLooper())).post(flag. new Runnable() {

                    public void run()
                    {
                        if(home != null && userList != null)
                        {
                            home.insertWithAnimation(item, 0);
                            if(userListMember)
                            {
                                removeLastItem(userList);
                                userList.insert(item, 0);
                            }
                            if(isShowRetweeted && item.getStatus().getRetweetedStatus().getUser().getId() == myUserId && item.getStatus().getUser().getId() != myUserId)
                                notification.show(item.getStatus().getUser().getScreenName(), item.getStatusType());
                        }
                    }

                    final MyUserStreamAdapter this$1;
                    final StatusItem val$item;
                    final boolean val$userListMember;

            
            {
                this$1 = final_myuserstreamadapter;
                item = statusitem;
                userListMember = Z.this;
                super();
            }
                }
);
                break;
            }
        }

        private static final int MAX_STATUSES = 1000;
        private boolean isFirstShow;
        private boolean isShowFavorited;
        private boolean isShowMention;
        private boolean isShowMessage;
        private boolean isShowRetweeted;
        private long myUserId;
        private OverlayNotification notification;
        final StreamInstance this$0;







        MyUserStreamAdapter()
        {
            this$0 = StreamInstance.this;
            super();
            isFirstShow = true;
            myUserId = userAccount.getId();
            isShowMention = PreferenceUtil.getBooleanPreference(context, EnumPrefs.NOTIFY_MENTION);
            isShowRetweeted = PreferenceUtil.getBooleanPreference(context, EnumPrefs.NOTIFY_RETWEETED);
            isShowFavorited = PreferenceUtil.getBooleanPreference(context, EnumPrefs.NOTIFY_FAVORITED);
            isShowMessage = PreferenceUtil.getBooleanPreference(context, EnumPrefs.NOTIFY_MESSAGE);
            notification = new OverlayNotification(context.getApplicationContext(), streamPreferences);
        }
    }


    StreamInstance(Context context1, StreamManager streammanager, UserAccount useraccount, StreamPreferences streampreferences)
    {
        context = context1;
        userAccount = useraccount;
        streamPreferences = streampreferences;
        streamManager = streammanager;
    }

    void setAdapters(AnimationStatusAdapter animationstatusadapter, StatusAdapter statusadapter, StatusAdapter statusadapter1, StatusAdapter statusadapter2)
    {
        home = animationstatusadapter;
        mention = statusadapter;
        userList = statusadapter1;
        message = statusadapter2;
    }

    void setUserListMembers(Set set)
    {
        userListMembers = set;
    }

    void shutdown()
    {
        (new Thread(new Runnable() {

            public void run()
            {
                if(twitterStream != null)
                {
                    twitterStream.shutdown();
                    twitterStream = null;
                    if(timer != null)
                    {
                        timer.cancel();
                        timer = null;
                    }
                }
            }

            final StreamInstance this$0;

            
            {
                this$0 = StreamInstance.this;
                super();
            }
        }
)).start();
    }

    void user()
    {
        (new Thread(new Runnable() {

            public void run()
            {
                if(twitterStream != null)
                {
                    twitterStream.shutdown();
                    twitterStream = null;
                    if(timer != null)
                    {
                        timer.cancel();
                        timer = null;
                    }
                }
                Object obj = new MyUserStreamAdapter();
                TwitterUtils twitterutils = new TwitterUtils();
                twitterStream = twitterutils.getTwitterStreamInstance(context, userAccount.getAccessToken(context));
                twitterStream.addListener(((twitter4j.StreamListener) (obj)));
                twitterStream.user();
                obj = new OffTimerTask(context, streamManager, streamPreferences);
                timer = new Timer(true);
                timer.schedule(((java.util.TimerTask) (obj)), 0L, 60000L);
            }

            final StreamInstance this$0;

            
            {
                this$0 = StreamInstance.this;
                super();
            }
        }
)).start();
    }

    private Context context;
    private AnimationStatusAdapter home;
    private StatusAdapter mention;
    private StatusAdapter message;
    private StreamManager streamManager;
    private StreamPreferences streamPreferences;
    private Timer timer;
    private TwitterStream twitterStream;
    private UserAccount userAccount;
    private StatusAdapter userList;
    private Set userListMembers;



/*
    static TwitterStream access$002(StreamInstance streaminstance, TwitterStream twitterstream)
    {
        streaminstance.twitterStream = twitterstream;
        return twitterstream;
    }

*/



/*
    static Timer access$102(StreamInstance streaminstance, Timer timer1)
    {
        streaminstance.timer = timer1;
        return timer1;
    }

*/









}
