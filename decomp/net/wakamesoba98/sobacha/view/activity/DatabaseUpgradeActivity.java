// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.*;
import net.wakamesoba98.sobacha.consumer.Consumer;
import net.wakamesoba98.sobacha.database.*;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.database.old.OldAccountsDatabase;
import net.wakamesoba98.sobacha.database.old.OldFriendsDatabase;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.preference.key.EnumOldPrefs;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class DatabaseUpgradeActivity extends ThemeActivity
{
    private class UpgradeTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient Void doInBackground(Void avoid[])
        {
            avoid = DatabaseUpgradeActivity.this;
            getSharedPreferences("twitter_access_token", 0).edit().clear().apply();
            deleteDatabase("webview.db");
            deleteDatabase("webviewCookiesChromium.db");
            deleteDatabase("webviewCookiesChromiumPrivate.db");
            Object obj;
            Object obj1;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object obj6;
            obj1 = new ArrayList();
            obj = new HashMap();
            obj2 = PreferenceUtil.getStringPreference(DatabaseUpgradeActivity.this, EnumOldPrefs.MY_SCREEN_NAME);
            obj3 = new OldAccountsDatabase(avoid);
            ((OldAccountsDatabase) (obj3)).openDatabase();
            obj6 = ((OldAccountsDatabase) (obj3)).getAllAccount();
            obj4 = ((OldAccountsDatabase) (obj3)).getAccount(((String) (obj2)));
            ((OldAccountsDatabase) (obj3)).closeDatabase();
            obj5 = new ConfigurationBuilder();
            ((ConfigurationBuilder) (obj5)).setOAuthConsumerKey(Consumer.getConsumer(DatabaseUpgradeActivity.this)).setOAuthConsumerSecret(Consumer.getSecret(DatabaseUpgradeActivity.this)).setOAuthAccessToken(((AccountData) (obj4)).getToken()).setOAuthAccessTokenSecret(((AccountData) (obj4)).getTokenSecret());
            obj4 = (new TwitterFactory(((ConfigurationBuilder) (obj5)).build())).getInstance();
            obj5 = new AccountsIdDatabase(avoid);
            ((AccountsIdDatabase) (obj5)).openDatabase();
            obj6 = ((List) (obj6)).iterator();
_L1:
            String s1;
            if(!((Iterator) (obj6)).hasNext())
                break MISSING_BLOCK_LABEL_333;
            s1 = ((AccountData)((Iterator) (obj6)).next()).getScreenName();
            ((List) (obj1)).add(s1);
            try
            {
                User user = ((Twitter) (obj4)).showUser(s1);
                ((AccountsIdDatabase) (obj5)).putAccount(new AccountData(user));
                ((Map) (obj)).put(s1, Long.valueOf(user.getId()));
            }
            catch(TwitterException twitterexception)
            {
                try
                {
                    twitterexception.printStackTrace();
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    ((Exception) (obj)).printStackTrace();
                    avoid.deleteDatabase((new OldAccountsDatabase(avoid)).getName());
                    return null;
                }
            }
              goto _L1
            ((AccountsIdDatabase) (obj5)).closeDatabase();
            avoid.deleteDatabase(((OldAccountsDatabase) (obj3)).getName());
            lastId = ((Long)((Map) (obj)).get(obj2)).longValue();
            obj1 = ((List) (obj1)).iterator();
_L2:
            if(!((Iterator) (obj1)).hasNext())
                break MISSING_BLOCK_LABEL_523;
            obj4 = (String)((Iterator) (obj1)).next();
            obj2 = new OldFriendsDatabase(avoid, ((String) (obj4)));
            ((OldFriendsDatabase) (obj2)).openDatabase();
            if(((OldFriendsDatabase) (obj2)).getCount() <= 0)
                break MISSING_BLOCK_LABEL_502;
            obj3 = ((OldFriendsDatabase) (obj2)).getAllAccount();
            obj4 = new FriendsIdDatabase(avoid, ((Long)((Map) (obj)).get(obj4)).longValue());
            ((FriendsIdDatabase) (obj4)).openDatabase();
            for(obj3 = ((List) (obj3)).iterator(); ((Iterator) (obj3)).hasNext(); ((FriendsIdDatabase) (obj4)).putAccount((AccountData)((Iterator) (obj3)).next()));
            ((FriendsIdDatabase) (obj4)).closeDatabase();
            ((OldFriendsDatabase) (obj2)).closeDatabase();
            deleteDatabase(((OldFriendsDatabase) (obj2)).getName());
              goto _L2
            int j;
            String as[];
            String as1[];
            String as2[];
            as2 = PreferenceUtil.getStringPreference(DatabaseUpgradeActivity.this, EnumOldPrefs.MUTE_TWEET).split(",");
            as1 = PreferenceUtil.getStringPreference(DatabaseUpgradeActivity.this, EnumOldPrefs.MUTE_USER).split(",");
            as = PreferenceUtil.getStringPreference(DatabaseUpgradeActivity.this, EnumOldPrefs.MUTE_CLIENT).split(",");
            obj3 = new MuteDatabase(avoid, "words");
            ((MuteDatabase) (obj3)).openDatabase();
            j = as2.length;
            int i = 0;
_L4:
            if(i >= j)
                break MISSING_BLOCK_LABEL_636;
            obj4 = as2[i];
            if(!"".equals(((String) (obj4)).trim()))
                ((MuteDatabase) (obj3)).putString(((String) (obj4)));
            break MISSING_BLOCK_LABEL_771;
            ((MuteDatabase) (obj3)).closeDatabase();
            as2 = new MuteDatabase(avoid, "users");
            as2.openDatabase();
            j = as1.length;
            MuteDatabase mutedatabase;
            String s;
            for(i = 0; i >= j; i++)
                break MISSING_BLOCK_LABEL_700;

            s = as1[i];
            if(!"".equals(s.trim()))
                as2.putString(s);
            break MISSING_BLOCK_LABEL_778;
            as2.closeDatabase();
            mutedatabase = new MuteDatabase(avoid, "clients");
            mutedatabase.openDatabase();
            j = as.length;
            for(i = 0; i >= j; i++)
                break MISSING_BLOCK_LABEL_764;

            as2 = as[i];
            if(!"".equals(as2.trim()))
                mutedatabase.putString(as2);
            break MISSING_BLOCK_LABEL_785;
            mutedatabase.closeDatabase();
            return null;
            i++;
            if(true) goto _L4; else goto _L3
_L3:
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            PreferenceUtil.putPreference(DatabaseUpgradeActivity.this, EnumExtraPrefs.LAST_USER_ID, Long.valueOf(lastId));
            if(dialog != null)
                dialog.dismiss();
            Notificator.toast(DatabaseUpgradeActivity.this, 0x7f0700dd);
            finish();
        }

        protected void onPreExecute()
        {
            dialog = new SpinnerDialog(DatabaseUpgradeActivity.this);
            dialog.show();
        }

        private SpinnerDialog dialog;
        private long lastId;
        final DatabaseUpgradeActivity this$0;

        private UpgradeTask()
        {
            this$0 = DatabaseUpgradeActivity.this;
            super();
        }

    }


    public DatabaseUpgradeActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        (new UpgradeTask()).execute(new Void[0]);
    }
}
