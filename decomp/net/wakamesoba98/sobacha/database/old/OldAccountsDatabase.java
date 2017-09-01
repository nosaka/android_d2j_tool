// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database.old;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.database.DatabaseManager;
import net.wakamesoba98.sobacha.database.MyDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;

public class OldAccountsDatabase
    implements MyDatabase
{

    public OldAccountsDatabase(Context context)
    {
        database = new DatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("accounts.db");
        database.setTableName("accounts");
        database.setVersion(1);
    }

    public void closeDatabase()
    {
        database.closeDB();
    }

    public void deleteAllData()
    {
        database.deleteAllDB();
    }

    public void deleteData(String s)
    {
        database.deleteDB(s);
    }

    public AccountData getAccount(String s)
    {
        s = getData(s);
        return new AccountData(-1L, s[0], s[3], s[4], s[1], s[2]);
    }

    public List getAllAccount()
    {
        Object obj = getAllData();
        ArrayList arraylist = new ArrayList();
        String as[];
        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new AccountData(-1L, as[0], as[3], as[4], as[1], as[2])))
            as = (String[])((Iterator) (obj)).next();

        return arraylist;
    }

    public List getAllData()
    {
        return database.readDBAll();
    }

    public int getCount()
    {
        return database.getCount();
    }

    public String[] getData(String s)
    {
        return database.readDB(s);
    }

    public String getName()
    {
        return "accounts.db";
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putAccount(AccountData accountdata)
    {
        String s = accountdata.getScreenName();
        String s1 = accountdata.getToken();
        String s2 = accountdata.getTokenSecret();
        String s3 = accountdata.getUserName();
        accountdata = accountdata.getIconUrl();
        database.updateDB(new String[] {
            s, s1, s2, s3, accountdata
        });
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    private static final String COLUMNS[] = {
        "screen_name", "token", "token_secret", "user_name", "icon_url"
    };
    private static final String DB_NAME = "accounts.db";
    private static final String DB_TABLE = "accounts";
    private static final int DB_VERSION = 1;
    private DatabaseManager database;

}
