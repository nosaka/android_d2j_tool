// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.database.data.AccountData;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            MyDatabase, IdDatabaseManager, DatabaseManager

public class FriendsIdDatabase
    implements MyDatabase
{

    public FriendsIdDatabase(Context context, long l)
    {
        database = new IdDatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("friends_id.db");
        database.setTableName((new StringBuilder()).append("id_").append(String.valueOf(l)).toString());
        database.setVersion(1);
    }

    public void closeDatabase()
    {
        database.closeDB();
    }

    public void deleteAccount(long l)
    {
        deleteData(String.valueOf(l));
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
        return new AccountData(Long.parseLong(as[0]), as[1], as[2], as[3]);
    }

    public List getAllAccount()
    {
        Object obj = getAllData();
        ArrayList arraylist = new ArrayList();
        String as[];
        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new AccountData(Long.parseLong(as[0]), as[1], as[2], as[3])))
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
        return "friends_id.db";
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putAccount(AccountData accountdata)
    {
        long l = accountdata.getUserId();
        String s = accountdata.getScreenName();
        String s1 = accountdata.getUserName();
        accountdata = accountdata.getIconUrl();
        database.updateDB(new String[] {
            String.valueOf(l), s, s1, accountdata
        });
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    private static final String COLUMNS[] = {
        "user_id", "screen_name", "user_name", "icon_url"
    };
    private static final String DB_NAME = "friends_id.db";
    private static final String DB_TABLE = "id_";
    private static final int DB_VERSION = 1;
    private DatabaseManager database;

}
