// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database.old;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.database.DatabaseManager;
import net.wakamesoba98.sobacha.database.MyDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;

public class OldFriendsDatabase
    implements MyDatabase
{

    public OldFriendsDatabase(Context context, String s)
    {
        databaseName = (new StringBuilder()).append("friends_").append(s).append(".db").toString();
        database = new DatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName(databaseName);
        database.setTableName("friends");
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
        return new AccountData(Long.parseLong(s[2]), s[0], s[1], s[3]);
    }

    public List getAllAccount()
    {
        Object obj = getAllData();
        ArrayList arraylist = new ArrayList();
        String as[];
        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new AccountData(Long.parseLong(as[2]), as[0], as[1], as[3])))
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
        return databaseName;
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putAccount(AccountData accountdata)
    {
        String s = accountdata.getScreenName();
        String s1 = accountdata.getUserName();
        long l = accountdata.getUserId();
        accountdata = accountdata.getIconUrl();
        database.updateDB(new String[] {
            s, s1, String.valueOf(l), accountdata
        });
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    private static final String COLUMNS[] = {
        "screen_name", "user_name", "user_id", "icon_url"
    };
    private static final String DB_NAME = "friends.db";
    private static final String DB_TABLE = "friends";
    private static final int DB_VERSION = 1;
    private DatabaseManager database;
    private String databaseName;

}
