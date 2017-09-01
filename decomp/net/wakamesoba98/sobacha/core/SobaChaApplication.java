// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.core;

import android.app.Application;
import java.util.ArrayList;
import java.util.List;
import net.wakamesoba98.sobacha.image.LoadBitmapManager;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.preference.prefs.*;
import net.wakamesoba98.sobacha.twitter.stream.StreamManager;
import net.wakamesoba98.sobacha.view.card.PostCardManager;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.MuteNotifyTask;

// Referenced classes of package net.wakamesoba98.sobacha.core:
//            LoadMode

public class SobaChaApplication extends Application
{

    public SobaChaApplication()
    {
    }

    private void asyncNotifyMute()
    {
        (new MuteNotifyTask(userAccount)).execute(adapterList.toArray(new StatusAdapter[adapterList.size()]));
    }

    public void changeAccount(long l)
    {
        PreferenceUtil.putPreference(this, EnumExtraPrefs.LAST_USER_ID, Long.valueOf(l));
        StreamManager streammanager = getUserAccount().getStreamManager();
        if(streammanager != null && streammanager.isEnabled())
            streammanager.cleanup();
        loadPreferences(LoadMode.FORCE_ALL);
        int i = 0;
        while(i < adapterList.size()) 
        {
            StatusAdapter statusadapter = (StatusAdapter)adapterList.get(i);
            if(statusadapter == null)
                adapterList.remove(i);
            else
                statusadapter.clear();
            i++;
        }
        i = 0;
        while(i < accountHolderList.size()) 
        {
            AccountHolder accountholder = (AccountHolder)accountHolderList.get(i);
            if(accountholder == null)
                accountHolderList.remove(i);
            else
                accountholder.onAccountChanged(userAccount);
            i++;
        }
    }

    public ListSettings getListSettings()
    {
        return listSettings;
    }

    public LoadBitmapManager getLoadBitmapManager()
    {
        return loadBitmapManager;
    }

    public StreamPreferences getStreamPreferences()
    {
        return streamPreferences;
    }

    public UserAccount getUserAccount()
    {
        return userAccount;
    }

    public long getUserId()
    {
        return PreferenceUtil.getLongPreference(this, EnumExtraPrefs.LAST_USER_ID);
    }

    public void loadPreferences(LoadMode loadmode)
    {
        if(getUserId() <= 0L) goto _L2; else goto _L1
_L1:
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$core$LoadMode[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$core$LoadMode = new int[LoadMode.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$core$LoadMode[LoadMode.ONLY_TOKEN.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$core$LoadMode[LoadMode.ONLY_MUTE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$core$LoadMode[LoadMode.ONLY_STREAM.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$core$LoadMode[LoadMode.LOAD_ONCE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$core$LoadMode[LoadMode.FORCE_ALL.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.core.LoadMode[loadmode.ordinal()];
        JVM INSTR tableswitch 1 5: default 52
    //                   1 53
    //                   2 66
    //                   3 79
    //                   4 88
    //                   5 150;
           goto _L2 _L3 _L4 _L5 _L6 _L7
_L2:
        return;
_L3:
        userAccount.loadAccessToken(this, getUserId());
        return;
_L4:
        userAccount.loadMutePreferences(this);
        asyncNotifyMute();
        return;
_L5:
        streamPreferences.loadPreferences(this);
        return;
_L6:
        userAccount.loadAccessToken(this, getUserId());
        userAccount.loadMutePreferences(this);
        if(!userAccount.isConfigLoaded())
        {
            userAccount.callApis(this);
            userAccount.setConfigLoaded();
            listSettings.loadPreferences(this);
            streamPreferences.loadPreferences(this);
            return;
        }
        if(true) goto _L2; else goto _L7
_L7:
        userAccount.clear();
        loadPreferences(LoadMode.LOAD_ONCE);
        return;
    }

    public void notifyDelete(long l)
    {
        int i = 0;
        while(i < adapterList.size()) 
        {
            StatusAdapter statusadapter = (StatusAdapter)adapterList.get(i);
            if(statusadapter == null)
                adapterList.remove(i);
            else
                statusadapter.remove(l);
            i++;
        }
    }

    public void notifyStreamStatus(boolean flag)
    {
        int i = 0;
        while(i < postCardList.size()) 
        {
            PostCardManager postcardmanager = (PostCardManager)postCardList.get(i);
            if(postcardmanager == null)
                postCardList.remove(i);
            else
                postcardmanager.setStreamButtonResource(flag);
            i++;
        }
    }

    public void onCreate()
    {
        super.onCreate();
        adapterList = new ArrayList();
        accountHolderList = new ArrayList();
        postCardList = new ArrayList();
        userAccount = new UserAccount();
        listSettings = new ListSettings();
        streamPreferences = new StreamPreferences();
        loadBitmapManager = new LoadBitmapManager(this, userAccount);
    }

    public void registerAccountHolder(AccountHolder accountholder)
    {
        accountHolderList.add(accountholder);
    }

    public void registerAdapter(StatusAdapter statusadapter)
    {
        adapterList.add(statusadapter);
    }

    public void registerPostCard(PostCardManager postcardmanager)
    {
        postCardList.add(postcardmanager);
    }

    public void releaseAccountHolder(AccountHolder accountholder)
    {
        accountHolderList.remove(accountholder);
    }

    public void releaseAdapter(StatusAdapter statusadapter)
    {
        adapterList.remove(statusadapter);
    }

    public void releasePostCard(PostCardManager postcardmanager)
    {
        postCardList.remove(postcardmanager);
    }

    private List accountHolderList;
    private List adapterList;
    private ListSettings listSettings;
    private LoadBitmapManager loadBitmapManager;
    private List postCardList;
    private StreamPreferences streamPreferences;
    private UserAccount userAccount;
}
