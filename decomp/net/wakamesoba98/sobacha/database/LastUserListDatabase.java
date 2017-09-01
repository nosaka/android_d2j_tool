// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import java.util.List;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            MyDatabase, IdDatabaseManager, DatabaseManager

public class LastUserListDatabase
    implements MyDatabase
{

    public LastUserListDatabase(Context context, long l)
    {
        userId = l;
        database = new IdDatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("user_settings.db");
        database.setTableName("last_user_list");
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

    public long getLastUserListId()
    {
        String as[] = getData(String.valueOf(userId));
        if(as.length > 0)
            return Long.parseLong(as[1]);
        else
            return -1L;
    }

    public String getName()
    {
        return "user_settings.db";
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    public void putLastUserListId(long l)
    {
        long l1 = userId;
        database.updateDB(new String[] {
            String.valueOf(l1), String.valueOf(l)
        });
    }

    private static final String COLUMNS[] = {
        "user_id", "last_user_list_id"
    };
    private static final String DB_NAME = "user_settings.db";
    private static final String DB_TABLE = "last_user_list";
    private static final int DB_VERSION = 1;
    private DatabaseManager database;
    private long userId;

}
