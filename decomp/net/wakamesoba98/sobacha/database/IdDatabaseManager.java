// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

// Referenced classes of package net.wakamesoba98.sobacha.database:
//            DatabaseManager

class IdDatabaseManager extends DatabaseManager
{

    IdDatabaseManager(Context context)
    {
        super(context);
    }

    protected void createTableIfNotExists()
    {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = true;
        stringbuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringbuilder.append(tableName);
        stringbuilder.append(" (");
        stringbuilder.append("_id");
        stringbuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT");
        String as[] = columns;
        int j = as.length;
        int i = 0;
        while(i < j) 
        {
            String s = as[i];
            stringbuilder.append(",");
            stringbuilder.append(s);
            if(flag)
            {
                stringbuilder.append(" INTEGER NOT NULL");
                flag = false;
            } else
            {
                stringbuilder.append(" TEXT NOT NULL");
            }
            i++;
        }
        stringbuilder.append(");");
        sqLiteDatabase.execSQL(stringbuilder.toString());
    }
}
