// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.database.data.IdWithNameData;
import twitter4j.SavedSearch;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            MyDatabase, IdDatabaseManager, DatabaseManager

public class SavedSearchesDatabase
    implements MyDatabase
{

    public SavedSearchesDatabase(Context context, long l)
    {
        database = new IdDatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("saved_searches.db");
        database.setTableName((new StringBuilder()).append("id_").append(String.valueOf(l)).toString());
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

    public void deleteSavedSearch(long l)
    {
        deleteData(String.valueOf(l));
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
        return "saved_searches.db";
    }

    public List getSavedSearches()
    {
        Object obj = getAllData();
        ArrayList arraylist = new ArrayList();
        String as[];
        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new IdWithNameData(Long.parseLong(as[0]), as[1])))
            as = (String[])((Iterator) (obj)).next();

        return arraylist;
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    public void putSavedSearch(SavedSearch savedsearch)
    {
        long l = savedsearch.getId();
        savedsearch = savedsearch.getName();
        database.updateDB(new String[] {
            String.valueOf(l), savedsearch
        });
    }

    public void putSavedSearches(List list)
    {
        long l;
        Object obj;
        for(list = list.iterator(); list.hasNext(); database.updateDB(new String[] {
    String.valueOf(l), obj
}))
        {
            obj = (SavedSearch)list.next();
            l = ((SavedSearch) (obj)).getId();
            obj = ((SavedSearch) (obj)).getName();
        }

    }

    private static final String COLUMNS[] = {
        "query_id", "query_word"
    };
    private static final String DB_NAME = "saved_searches.db";
    private static final String DB_TABLE = "id_";
    private static final int DB_VERSION = 1;
    private DatabaseManager database;

}
