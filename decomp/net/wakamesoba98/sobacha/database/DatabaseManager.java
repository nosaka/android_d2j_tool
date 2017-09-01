// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends ContextWrapper
{
    class DBHelper extends SQLiteOpenHelper
    {

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            sqlitedatabase.execSQL((new StringBuilder()).append("DROP TABLE IF EXISTS ").append(databaseName).toString());
            onCreate(sqlitedatabase);
        }

        final DatabaseManager this$0;

        public DBHelper(Context context1)
        {
            this$0 = DatabaseManager.this;
            super(context1, databaseName, null, version);
        }
    }


    public DatabaseManager(Context context1)
    {
        super(context1);
        sqLiteDatabase = null;
        context = context1;
        if(sqLiteDatabase != null)
            sqLiteDatabase.close();
    }

    private String[] whereArgs(String s)
    {
        return (new String[] {
            s
        });
    }

    private String whereClause()
    {
        return (new StringBuilder()).append(key).append("=?").toString();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
        sqLiteDatabase = null;
    }

    void createTableIfNotExists()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringbuilder.append(tableName);
        stringbuilder.append(" (");
        stringbuilder.append("_id");
        stringbuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT");
        String as[] = columns;
        int j = as.length;
        for(int i = 0; i < j; i++)
        {
            String s = as[i];
            stringbuilder.append(",");
            stringbuilder.append(s);
            stringbuilder.append(" TEXT NOT NULL");
        }

        stringbuilder.append(");");
        sqLiteDatabase.execSQL(stringbuilder.toString());
    }

    public void deleteAllDB()
    {
        sqLiteDatabase.delete(tableName, null, null);
    }

    public void deleteDB(String s)
    {
        sqLiteDatabase.delete(tableName, whereClause(), whereArgs(s));
    }

    public int getCount()
    {
        Cursor cursor = sqLiteDatabase.query(tableName, columns, null, null, null, null, null);
        int i = cursor.getCount();
        cursor.close();
        return i;
    }

    public void openDB()
    {
        if(sqLiteDatabase != null)
            closeDB();
        sqLiteDatabase = (new DBHelper(context)).getWritableDatabase();
        createTableIfNotExists();
    }

    public String[] readDB(String s)
    {
        s = sqLiteDatabase.query(tableName, columns, whereClause(), whereArgs(s), null, null, null);
        ArrayList arraylist = new ArrayList();
        if(s.moveToFirst())
        {
            for(int i = 0; i < s.getColumnCount(); i++)
                arraylist.add(s.getString(i));

        }
        s.close();
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public List readDBAll()
    {
        Cursor cursor = sqLiteDatabase.query(tableName, columns, null, null, null, null, null);
        boolean flag = cursor.moveToFirst();
        ArrayList arraylist = new ArrayList();
        for(; flag; flag = cursor.moveToNext())
        {
            ArrayList arraylist1 = new ArrayList();
            for(int i = 0; i < cursor.getColumnCount(); i++)
                arraylist1.add(cursor.getString(i));

            arraylist.add(((Object) (arraylist1.toArray(new String[arraylist1.size()]))));
        }

        cursor.close();
        return arraylist;
    }

    public void setColumns(String as[])
    {
        columns = as;
        key = as[0];
    }

    public void setDatabaseName(String s)
    {
        databaseName = s;
    }

    public void setTableName(String s)
    {
        tableName = s;
    }

    public void setVersion(int i)
    {
        version = i;
    }

    public void updateDB(String as[])
    {
        ContentValues contentvalues = new ContentValues();
        for(int i = 0; i < columns.length; i++)
            contentvalues.put(columns[i], as[i]);

        if(sqLiteDatabase.update(tableName, contentvalues, whereClause(), whereArgs(as[0])) == 0)
            sqLiteDatabase.insert(tableName, null, contentvalues);
    }

    static final String COL_ID = "_id";
    String columns[];
    private Context context;
    private String databaseName;
    private String key;
    SQLiteDatabase sqLiteDatabase;
    String tableName;
    private int version;


}
