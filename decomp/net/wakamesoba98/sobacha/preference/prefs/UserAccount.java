// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.prefs;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import java.util.*;
import net.wakamesoba98.sobacha.database.*;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.twitter.api.*;
import net.wakamesoba98.sobacha.twitter.listener.*;
import net.wakamesoba98.sobacha.twitter.stream.StreamManager;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import twitter4j.UserList;
import twitter4j.auth.AccessToken;

// Referenced classes of package net.wakamesoba98.sobacha.preference.prefs:
//            StreamPreferences

public class UserAccount
    implements OnUserListsGotListener, OnNoRetweetsIdsGotListener, OnBlockingIdsGotListener, OnMuteUsersGotListener, OnLookupFinishedListener
{

    public UserAccount()
    {
        id = -1L;
        muteWords = new HashSet();
        muteClients = new HashSet();
        muteUsers = new HashSet();
        muteThumbs = new HashSet();
        officialMuteUsers = new HashSet();
        muteRetweets = new HashSet();
        blockingIds = new HashSet();
        friends = new HashSet();
    }

    private Set getFriendIds(Context context)
    {
        Object obj = new FriendsIdDatabase(context, id);
        ((FriendsIdDatabase) (obj)).openDatabase();
        context = ((FriendsIdDatabase) (obj)).getAllData();
        ((FriendsIdDatabase) (obj)).closeDatabase();
        obj = new HashSet();
        for(context = context.iterator(); context.hasNext(); ((Set) (obj)).add(Long.valueOf(Long.parseLong(((String[])context.next())[0]))));
        return ((Set) (obj));
    }

    private Set getMutes(Context context, String s, boolean flag)
    {
        context = new MuteDatabase(context, s);
        context.openDatabase();
        s = context.getAllData();
        context.closeDatabase();
        context = new HashSet();
        for(s = s.iterator(); s.hasNext();)
        {
            String as[] = (String[])s.next();
            if(flag)
                context.add(as[0].toLowerCase());
            else
                context.add(as[0]);
        }

        return context;
    }

    private void loadLastUserListId(Context context)
    {
        context = new LastUserListDatabase(context, id);
        context.openDatabase();
        lastUserListId = context.getLastUserListId();
        context.closeDatabase();
    }

    public void callApis(Context context)
    {
        (new UserListApi(context, this)).getUserLists(this, id);
        (new NoRetweetApi(context, this)).getNoRetweetsUsers(this);
        (new BlockApi(context, this)).getBlocksIds(this);
        (new MuteApi(context, this)).getMutesIds(this);
        (new LookupApi(context, this)).backgroundLookup(this, id);
    }

    public void clear()
    {
        accessToken = null;
        userLists = null;
        lastUserListId = -1L;
        muteWords.clear();
        muteClients.clear();
        muteUsers.clear();
        muteThumbs.clear();
        officialMuteUsers.clear();
        muteRetweets.clear();
        blockingIds.clear();
        friends.clear();
        isNoiseCancelMode = false;
        isConfigLoaded = false;
    }

    public void finishedLookup()
    {
    }

    public AccessToken getAccessToken(Context context)
    {
        if(accessToken == null)
            loadAccessToken(context, id);
        return accessToken;
    }

    public Set getBlockingIds()
    {
        return blockingIds;
    }

    public Set getFriends()
    {
        return friends;
    }

    public long getId()
    {
        return id;
    }

    public long getLastUserListId()
    {
        return lastUserListId;
    }

    public Set getMuteClients()
    {
        return muteClients;
    }

    public Set getMuteRetweets()
    {
        return muteRetweets;
    }

    public Set getMuteThumbs()
    {
        return muteThumbs;
    }

    public Set getMuteUsers()
    {
        return muteUsers;
    }

    public Set getMuteWords()
    {
        return muteWords;
    }

    public Set getOfficialMuteUsers()
    {
        return officialMuteUsers;
    }

    public StreamManager getStreamManager()
    {
        return streamManager;
    }

    public List getUserLists()
    {
        return userLists;
    }

    public void gotBlockingIds(Set set)
    {
        blockingIds = set;
    }

    public void gotMuteUsers(Set set)
    {
        officialMuteUsers = set;
    }

    public void gotNoRetweetsIds(Set set)
    {
        muteRetweets = set;
    }

    public void gotUserLists(List list)
    {
        if(list != null)
        {
            if(userLists == null)
                userLists = new ArrayList();
            userLists.clear();
            UserList userlist;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); userLists.add(userlist))
                userlist = (UserList)iterator.next();

        }
        if(listsGotListener != null)
            listsGotListener.gotUserLists(list);
    }

    public void initializeStream(Activity activity, AdapterHolder adapterholder, StreamPreferences streampreferences)
    {
        if(streamManager == null)
            streamManager = new StreamManager(activity, this, streampreferences);
        streamManager.setAdapters(adapterholder);
    }

    public boolean isConfigLoaded()
    {
        return isConfigLoaded;
    }

    public boolean isNoiseCancelMode()
    {
        return isNoiseCancelMode;
    }

    public void loadAccessToken(Context context, long l)
    {
        id = l;
        Object obj = new AccountsIdDatabase(context);
        ((AccountsIdDatabase) (obj)).openDatabase();
        context = ((AccountsIdDatabase) (obj)).getAccount(l);
        ((AccountsIdDatabase) (obj)).closeDatabase();
        obj = context.getToken();
        context = context.getTokenSecret();
        if(obj != null && context != null)
        {
            accessToken = new AccessToken(((String) (obj)), context);
            return;
        } else
        {
            accessToken = null;
            return;
        }
    }

    public void loadMutePreferences(Context context)
    {
        muteWords = getMutes(context, "words", false);
        muteClients = getMutes(context, "clients", false);
        muteUsers = getMutes(context, "users", true);
        muteThumbs = getMutes(context, "thumbs", true);
        boolean flag = PreferenceUtil.getBooleanPreference(context, EnumPrefs.NOISE_CANCEL);
        if(flag)
            friends = getFriendIds(context);
        isNoiseCancelMode = flag;
        loadLastUserListId(context);
    }

    public void setConfigLoaded()
    {
        isConfigLoaded = true;
    }

    public void setLastUserListId(long l)
    {
        lastUserListId = l;
    }

    public void setListsGotListener(OnUserListsGotListener onuserlistsgotlistener)
    {
        listsGotListener = onuserlistsgotlistener;
    }

    public void setStreamManager(StreamManager streammanager)
    {
        streamManager = streammanager;
    }

    public void startStream(Activity activity, AdapterHolder adapterholder, StreamPreferences streampreferences)
    {
        if(id > 0L) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag;
        if(streamManager == null)
            initializeStream(activity, adapterholder, streampreferences);
        flag = PreferenceUtil.getBooleanPreference(activity, EnumPrefs.KEEP_SCREEN_ON);
        if(!PreferenceUtil.getBooleanPreference(activity, EnumExtraPrefs.USER_STREAM))
            continue; /* Loop/switch isn't completed */
        streamManager.user();
        if(!flag) goto _L1; else goto _L3
_L3:
        activity.getWindow().addFlags(128);
        return;
        if(!flag) goto _L1; else goto _L4
_L4:
        activity.getWindow().clearFlags(128);
        return;
    }

    private AccessToken accessToken;
    private Set blockingIds;
    private Set friends;
    private long id;
    private boolean isConfigLoaded;
    private boolean isNoiseCancelMode;
    private long lastUserListId;
    private OnUserListsGotListener listsGotListener;
    private Set muteClients;
    private Set muteRetweets;
    private Set muteThumbs;
    private Set muteUsers;
    private Set muteWords;
    private Set officialMuteUsers;
    private StreamManager streamManager;
    private List userLists;
}
