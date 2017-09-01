// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.database;

import java.util.List;

public interface MyDatabase
{

    public abstract void closeDatabase();

    public abstract void deleteAllData();

    public abstract void deleteData(String s);

    public abstract List getAllData();

    public abstract int getCount();

    public abstract String[] getData(String s);

    public abstract String getName();

    public abstract void openDatabase();

    public abstract void putData(String as[]);
}
