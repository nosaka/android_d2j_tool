// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import java.util.*;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            MyDatabase, DatabaseManager

public class MuteDatabase
    implements MyDatabase
{

    public MuteDatabase(Context context, String s)
    {
        database = new DatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("mute.db");
        database.setTableName(s);
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

    public List getAllMutes()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = getAllData().iterator(); iterator.hasNext(); arraylist.add(((String[])iterator.next())[0]));
        return arraylist;
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
        return "mute.db";
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    public void putString(String s)
    {
        putData(new String[] {
            s
        });
    }

    private static final String COLUMNS[] = {
        "mute"
    };
    private static final String DB_NAME = "mute.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_CLIENTS = "clients";
    public static final String TABLE_THUMBS = "thumbs";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_WORDS = "words";
    private DatabaseManager database;

}
