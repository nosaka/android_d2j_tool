// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import java.util.List;
import net.wakamesoba98.sobacha.database.data.ListViewPosition;
import net.wakamesoba98.sobacha.view.tab.EnumTab;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            MyDatabase, IdDatabaseManager, DatabaseManager

public class LastPositionDatabase
    implements MyDatabase
{

    public LastPositionDatabase(Context context, long l, EnumTab enumtab)
    {
        userId = l;
        database = new IdDatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("status.db");
        context = "position";
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumTab[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumTab = new int[EnumTab.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$tab$EnumTab[EnumTab.HOME.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
            }
        }

        _cls1..SwitchMap.net.wakamesoba98.sobacha.view.tab.EnumTab[enumtab.ordinal()];
        JVM INSTR tableswitch 1 1: default 72
    //                   1 89;
           goto _L1 _L2
_L1:
        database.setTableName(context);
        database.setVersion(1);
        return;
_L2:
        context = (new StringBuilder()).append("position_home_").append(String.valueOf(l)).toString();
        if(true) goto _L1; else goto _L3
_L3:
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

    public ListViewPosition getLastVisiblePosition()
    {
        String as[] = getData(String.valueOf(userId));
        if(as.length > 0)
            return new ListViewPosition(Integer.parseInt(as[1]), Integer.parseInt(as[2]));
        else
            return new ListViewPosition(0, 0);
    }

    public String getName()
    {
        return "status.db";
    }

    public void openDatabase()
    {
        database.openDB();
    }

    public void putData(String as[])
    {
        database.updateDB(as);
    }

    public void putLastVisiblePosition(int i, int j)
    {
        long l = userId;
        database.updateDB(new String[] {
            String.valueOf(l), String.valueOf(i), String.valueOf(j)
        });
    }

    private static final String COLUMNS[] = {
        "user_id", "position", "offset"
    };
    private static final String DB_NAME = "status.db";
    private static final String DB_TABLE = "position";
    private static final int DB_VERSION = 1;
    private DatabaseManager database;
    private long userId;

}
