// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.database.data.AccountData;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            MyDatabase, IdDatabaseManager, DatabaseManager

public class AccountsIdDatabase
    implements MyDatabase
{

    public AccountsIdDatabase(Context context)
    {
        database = new IdDatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("accounts_id.db");
        database.setTableName("accounts_id");
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

    public AccountData getAccount(long l)
    {
        String as[] = getData(String.valueOf(l));
        return new AccountData(Long.parseLong(as[0]), as[3], as[4], as[5], as[1], as[2]);
    }

    public List getAllAccount()
    {
        Object obj = getAllData();
        ArrayList arraylist = new ArrayList();
        String as[];
        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new AccountData(Long.parseLong(as[0]), as[3], as[4], as[5], as[1], as[2])))
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
        return "accounts_id.db";
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putAccount(AccountData accountdata)
    {
        if(accountdata.getToken() == null)
        {
            database.setColumns(COLUMNS_WITHOUT_TOKEN);
            long l = accountdata.getUserId();
            String s = accountdata.getScreenName();
            String s2 = accountdata.getUserName();
            accountdata = accountdata.getIconUrl();
            database.updateDB(new String[] {
                String.valueOf(l), s, s2, accountdata
            });
            database.setColumns(COLUMNS);
            return;
        } else
        {
            long l1 = accountdata.getUserId();
            String s1 = accountdata.getToken();
            String s3 = accountdata.getTokenSecret();
            String s4 = accountdata.getScreenName();
            String s5 = accountdata.getUserName();
            accountdata = accountdata.getIconUrl();
            database.updateDB(new String[] {
                String.valueOf(l1), s1, s3, s4, s5, accountdata
            });
            return;
        }
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    private static final String COLUMNS[] = {
        "user_id", "token", "token_secret", "screen_name", "user_name", "icon_url"
    };
    private static final String COLUMNS_WITHOUT_TOKEN[] = {
        "user_id", "screen_name", "user_name", "icon_url"
    };
    private static final String DB_NAME = "accounts_id.db";
    private static final String DB_TABLE = "accounts_id";
    private static final int DB_VERSION = 1;
    private DatabaseManager database;

}
