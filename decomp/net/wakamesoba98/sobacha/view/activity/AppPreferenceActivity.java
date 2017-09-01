// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.*;
import net.wakamesoba98.sobacha.compatible.Flavor;
import net.wakamesoba98.sobacha.core.LoadMode;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.database.FriendsIdDatabase;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.FriendsListApi;
import net.wakamesoba98.sobacha.twitter.api.TrendsApi;
import net.wakamesoba98.sobacha.view.activity.base.PreferenceActivityBase;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            VersionActivity

public class AppPreferenceActivity extends PreferenceActivityBase
{

    public AppPreferenceActivity()
    {
        tap = 0;
    }

    private void removeSplashScreenCategory()
    {
        PreferenceScreen preferencescreen = (PreferenceScreen)findPreference("pref_screen_appearance");
        PreferenceCategory preferencecategory = (PreferenceCategory)findPreference("pref_category_splash");
        if(!Flavor.isMateCha())
            preferencescreen.removePreference(preferencecategory);
    }

    private void setFriendsListListener()
    {
        Preference preference = findPreference("pref_get_following_list");
        if(preference != null)
            preference.setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {

                public boolean onPreferenceClick(Preference preference1)
                {
                    (new FriendsListApi(AppPreferenceActivity.this, userAccount)).getFriendsList(userAccount.getId());
                    return true;
                }

                final AppPreferenceActivity this$0;

            
            {
                this$0 = AppPreferenceActivity.this;
                super();
            }
            }
);
    }

    private void setNoiseCancelingEnable()
    {
        Object obj;
label0:
        {
            obj = new FriendsIdDatabase(this, userAccount.getId());
            ((FriendsIdDatabase) (obj)).openDatabase();
            int i = ((FriendsIdDatabase) (obj)).getCount();
            ((FriendsIdDatabase) (obj)).closeDatabase();
            obj = findPreference("pref_mute_mention_from_not_following");
            if(obj != null)
            {
                if(i <= 0)
                    break label0;
                ((Preference) (obj)).setEnabled(true);
            }
            return;
        }
        ((Preference) (obj)).setEnabled(false);
    }

    private void setNotificationSoundListener()
    {
        Preference preference = findPreference("pref_change_notification_sound");
        if(preference != null)
            preference.setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {

                public boolean onPreferenceClick(Preference preference1)
                {
                    preference1 = new Intent("android.intent.action.RINGTONE_PICKER");
                    preference1.putExtra("android.intent.extra.ringtone.TYPE", 2);
                    startActivityForResult(preference1, 1);
                    return true;
                }

                final AppPreferenceActivity this$0;

            
            {
                this$0 = AppPreferenceActivity.this;
                super();
            }
            }
);
    }

    private void setTrendLocationListener()
    {
        Preference preference = findPreference("pref_trend_location");
        if(preference != null)
            preference.setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {

                public boolean onPreferenceClick(Preference preference1)
                {
                    (new TrendsApi(AppPreferenceActivity.this, userAccount)).getAvailableTrends(null);
                    return true;
                }

                final AppPreferenceActivity this$0;

            
            {
                this$0 = AppPreferenceActivity.this;
                super();
            }
            }
);
    }

    private void showVersion()
    {
        Object obj;
        String s;
        Preference preference;
        String s1;
        String s2;
        Object obj1;
        preference = findPreference("pref_about");
        obj1 = getPackageManager();
        s1 = getString(0x7f07014e);
        s2 = getString(0x7f07014d);
        s = "";
        obj = s;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_64;
        obj1 = ((PackageManager) (obj1)).getPackageInfo(getPackageName(), 1);
        obj = s;
        if(obj1 != null)
            try
            {
                obj = ((PackageInfo) (obj1)).versionName;
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                namenotfoundexception.printStackTrace();
                namenotfoundexception = s;
            }
        if(preference != null)
        {
            preference.setTitle((new StringBuilder()).append(s1).append(" ver.").append(((String) (obj))).append(" '").append(s2).append("'").toString());
            preference.setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {

                public boolean onPreferenceClick(Preference preference1)
                {
                    int i = ((access._cls000) (this)).access$000;
                    if(tap >= 10)
                    {
                        tap = 0;
                        Notificator.toast(AppPreferenceActivity.this, 0x7f070111);
                        preference1 = new Intent(AppPreferenceActivity.this, net/wakamesoba98/sobacha/view/activity/VersionActivity);
                        startActivity(preference1);
                    }
                    return true;
                }

                final AppPreferenceActivity this$0;

            
            {
                this$0 = AppPreferenceActivity.this;
                super();
            }
            }
);
        }
        return;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
label0:
        {
            if(i == 1 && j == -1)
            {
                intent = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
                if(intent == null)
                    break label0;
                PreferenceUtil.putPreference(this, EnumPrefs.NOTIFICATION_SOUND, intent.toString());
            }
            return;
        }
        PreferenceUtil.putPreference(this, EnumPrefs.NOTIFICATION_SOUND, "");
    }

    public void onCreate(Bundle bundle)
    {
        setTheme((new ThemeManager(this)).getActivityTheme());
        super.onCreate(bundle);
        bundle = getPreferenceManager();
        bundle.setSharedPreferencesName("sobacha_prefs");
        bundle.setSharedPreferencesMode(0);
        userAccount = ((SobaChaApplication)getApplication()).getUserAccount();
        bundle = getIntent();
        if(bundle != null)
            if((bundle = bundle.getData()) != null)
            {
                int i = Integer.parseInt(bundle.getHost());
                prefNumber = i;
                switch(i)
                {
                default:
                    return;

                case 0: // '\0'
                    addPreferencesFromResource(0x7f060000);
                    showVersion();
                    return;

                case 1: // '\001'
                    addPreferencesFromResource(0x7f060003);
                    return;

                case 2: // '\002'
                    addPreferencesFromResource(0x7f060007);
                    return;

                case 3: // '\003'
                    addPreferencesFromResource(0x7f060001);
                    removeSplashScreenCategory();
                    return;

                case 4: // '\004'
                    addPreferencesFromResource(0x7f060008);
                    isDualMode = PreferenceUtil.getBooleanPreference(this, EnumPrefs.DUAL_TIMELINE_MODE);
                    splitVertically = PreferenceUtil.getBooleanPreference(this, EnumPrefs.SPLIT_VERTICALLY);
                    return;

                case 5: // '\005'
                    addPreferencesFromResource(0x7f060006);
                    setTrendLocationListener();
                    return;

                case 6: // '\006'
                    addPreferencesFromResource(0x7f060005);
                    setNotificationSoundListener();
                    return;

                case 7: // '\007'
                    addPreferencesFromResource(0x7f060004);
                    setNoiseCancelingEnable();
                    setFriendsListListener();
                    return;

                case 8: // '\b'
                    addPreferencesFromResource(0x7f060002);
                    break;
                }
                return;
            }
    }

    protected void onDestroy()
    {
        SobaChaApplication sobachaapplication;
        super.onDestroy();
        sobachaapplication = (SobaChaApplication)getApplication();
        prefNumber;
        JVM INSTR lookupswitch 3: default 52
    //                   0: 53
    //                   4: 61
    //                   6: 105;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        sobachaapplication.loadPreferences(LoadMode.ONLY_MUTE);
        return;
_L3:
        boolean flag = PreferenceUtil.getBooleanPreference(this, EnumPrefs.DUAL_TIMELINE_MODE);
        boolean flag1 = PreferenceUtil.getBooleanPreference(this, EnumPrefs.SPLIT_VERTICALLY);
        if(flag != isDualMode || flag1 != splitVertically)
        {
            PreferenceUtil.putPreference(this, EnumExtraPrefs.LAYOUT_CHANGED, Boolean.valueOf(true));
            return;
        }
          goto _L1
_L4:
        sobachaapplication.loadPreferences(LoadMode.ONLY_STREAM);
        return;
    }

    private static final int REQUEST_CODE_RINGTONE_PICKER = 1;
    private boolean isDualMode;
    private int prefNumber;
    private boolean splitVertically;
    private int tap;
    private UserAccount userAccount;



/*
    static int access$002(AppPreferenceActivity apppreferenceactivity, int i)
    {
        apppreferenceactivity.tap = i;
        return i;
    }

*/


/*
    static int access$008(AppPreferenceActivity apppreferenceactivity)
    {
        int i = apppreferenceactivity.tap;
        apppreferenceactivity.tap = i + 1;
        return i;
    }

*/

}
