// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.stream;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import java.util.*;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.preference.prefs.StreamPreferences;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.view.listview.adapter.*;
import net.wakamesoba98.sobacha.view.tab.EnumTab;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.stream:
//            StreamInstance

public class StreamManager
{

    public StreamManager(Context context1, UserAccount useraccount, StreamPreferences streampreferences)
    {
        isEnabled = false;
        context = context1;
        userAccount = useraccount;
        streamPreferences = streampreferences;
        streamInstances = new HashMap();
    }

    private void setSwipeRefreshEnable(boolean flag)
    {
        if(refreshLayout != null)
            refreshLayout.setEnabled(flag);
    }

    public void cleanup()
    {
        Iterator iterator = streamInstances.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            long l = ((Long)iterator.next()).longValue();
            StreamInstance streaminstance = (StreamInstance)streamInstances.get(Long.valueOf(l));
            if(streaminstance != null)
                streaminstance.shutdown();
        } while(true);
        streamInstances.clear();
        isEnabled = false;
        activeInstance = -1L;
    }

    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void setAdapters(AdapterHolder adapterholder)
    {
        home = (AnimationStatusAdapter)adapterholder.getAdapter(EnumTab.HOME);
        mention = adapterholder.getAdapter(EnumTab.MENTION);
        userList = adapterholder.getAdapter(EnumTab.USER_LIST);
        message = adapterholder.getAdapter(EnumTab.DIRECT_MESSAGE);
    }

    public void setRefreshLayout(SwipeRefreshLayout swiperefreshlayout)
    {
        refreshLayout = swiperefreshlayout;
    }

    public void setUserListMembers(Set set)
    {
        userListMembers = set;
        set = (StreamInstance)streamInstances.get(Long.valueOf(activeInstance));
        if(set != null)
            set.setUserListMembers(userListMembers);
    }

    public void shutdown()
    {
        setSwipeRefreshEnable(true);
        StreamInstance streaminstance = (StreamInstance)streamInstances.get(Long.valueOf(activeInstance));
        if(streaminstance != null)
        {
            streaminstance.setAdapters(null, null, null, null);
            streaminstance.shutdown();
        }
        streamInstances.remove(Long.valueOf(activeInstance));
        isEnabled = false;
        activeInstance = -1L;
        ((SobaChaApplication)context.getApplicationContext()).notifyStreamStatus(false);
    }

    public void user()
    {
        setSwipeRefreshEnable(false);
        StreamInstance streaminstance = new StreamInstance(context, this, userAccount, streamPreferences);
        long l = System.currentTimeMillis();
        streaminstance.setAdapters(home, mention, userList, message);
        streaminstance.setUserListMembers(userListMembers);
        streaminstance.user();
        streamInstances.put(Long.valueOf(l), streaminstance);
        isEnabled = true;
        activeInstance = l;
        ((SobaChaApplication)context.getApplicationContext()).notifyStreamStatus(true);
    }

    private long activeInstance;
    private Context context;
    private AnimationStatusAdapter home;
    private boolean isEnabled;
    private StatusAdapter mention;
    private StatusAdapter message;
    private SwipeRefreshLayout refreshLayout;
    private Map streamInstances;
    private StreamPreferences streamPreferences;
    private UserAccount userAccount;
    private StatusAdapter userList;
    private Set userListMembers;
}
