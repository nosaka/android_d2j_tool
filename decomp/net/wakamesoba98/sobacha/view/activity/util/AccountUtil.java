// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity.util;

import android.app.Activity;
import android.content.Intent;
import java.util.List;
import net.wakamesoba98.sobacha.database.AccountsIdDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.database.old.OldAccountsDatabase;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.view.activity.AccountsActivity;
import net.wakamesoba98.sobacha.view.activity.DatabaseUpgradeActivity;

public class AccountUtil
{

    public AccountUtil()
    {
    }

    public boolean accountInitialize(Activity activity, long l)
    {
        AccountsIdDatabase accountsiddatabase = new AccountsIdDatabase(activity);
        accountsiddatabase.openDatabase();
        int i = accountsiddatabase.getCount();
        accountsiddatabase.closeDatabase();
        if(i == 0)
        {
            OldAccountsDatabase oldaccountsdatabase = new OldAccountsDatabase(activity);
            oldaccountsdatabase.openDatabase();
            int j = oldaccountsdatabase.getCount();
            oldaccountsdatabase.closeDatabase();
            if(j > 0)
            {
                activity.startActivity(new Intent(activity, net/wakamesoba98/sobacha/view/activity/DatabaseUpgradeActivity));
                activity.finish();
                return true;
            } else
            {
                activity.deleteDatabase(oldaccountsdatabase.getName());
                activity.startActivity(new Intent(activity, net/wakamesoba98/sobacha/view/activity/AccountsActivity));
                activity.finish();
                return true;
            }
        }
        if(l == 0L)
        {
            AccountsIdDatabase accountsiddatabase1 = new AccountsIdDatabase(activity);
            accountsiddatabase1.openDatabase();
            List list = accountsiddatabase1.getAllAccount();
            accountsiddatabase1.closeDatabase();
            PreferenceUtil.putPreference(activity, EnumExtraPrefs.LAST_USER_ID, Long.valueOf(((AccountData)list.get(0)).getUserId()));
            return false;
        } else
        {
            return false;
        }
    }
}
