// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import twitter4j.TwitterException;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            MyDatabase, IdDatabaseManager, DatabaseManager

public class StatusDatabase
    implements MyDatabase
{

    public StatusDatabase(Context context, long l, EnumTab enumtab)
    {
        database = new IdDatabaseManager(context);
        database.setColumns(COLUMNS);
        database.setDatabaseName("status.db");
        context = "id_";
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
        JVM INSTR tableswitch 1 1: default 64
    //                   1 81;
           goto _L1 _L2
_L1:
        database.setTableName(context);
        database.setVersion(1);
        return;
_L2:
        context = (new StringBuilder()).append("home_").append(String.valueOf(l)).toString();
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

    public String getName()
    {
        return "status.db";
    }

    public List getStatusItemList(UserAccount useraccount)
    {
        Object obj = getAllData();
        ArrayList arraylist = new ArrayList();
        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            String s = ((String[])((Iterator) (obj)).next())[0];
            if(s.equals("READ_MORE"))
                arraylist.add(new StatusItem());
            else
                try
                {
                    arraylist.add(new StatusItem(useraccount, s));
                }
                catch(TwitterException twitterexception)
                {
                    twitterexception.printStackTrace();
                }
        }

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

    public void putStatuses(StatusItem astatusitem[])
    {
        deleteAllData();
        String as[] = new String[1];
        as[0] = "";
        int j = astatusitem.length;
        int i = 0;
        while(i < j) 
        {
            StatusItem statusitem = astatusitem[i];
            if(statusitem.getJson() != null)
            {
                if(statusitem.getStatusType() != EnumStatusType.FAVORITED)
                {
                    as[0] = statusitem.getJson();
                    database.updateDB(as);
                }
            } else
            if(statusitem.getStatusType() == EnumStatusType.READ_MORE)
            {
                as[0] = "READ_MORE";
                database.updateDB(as);
            }
            i++;
        }
    }

    private static final String COLUMNS[] = {
        "json"
    };
    private static final String DB_NAME = "status.db";
    private static final int DB_VERSION = 1;
    private static final String READ_MORE = "READ_MORE";
    private DatabaseManager database;

}
