// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.nfc;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class NfcData
{

    public NfcData(Context context, String s, int i, String s1, String as[])
    {
        packageName = context.getPackageName();
        versionCode = getAppVersionCode(context);
        dbName = s;
        dbVersion = i;
        dbTable = s1;
        dbData = as;
    }

    public NfcData(String s)
    {
        s = s.split(",");
        packageName = s[0];
        versionCode = Integer.parseInt(s[1]);
        dbName = s[2];
        dbVersion = Integer.parseInt(s[3]);
        dbTable = s[4];
        if(s.length > 10)
        {
            dbData = new String[s.length - 10];
            System.arraycopy(s, 10, dbData, 0, s.length - 10);
            return;
        } else
        {
            dbData = new String[0];
            return;
        }
    }

    private int getAppVersionCode(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        context = context.getPackageName();
        byte byte0 = -1;
        int i = byte0;
        if(packagemanager == null)
            break MISSING_BLOCK_LABEL_39;
        try
        {
            context = packagemanager.getPackageInfo(context, 1);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return -1;
        }
        i = byte0;
        if(context == null)
            break MISSING_BLOCK_LABEL_39;
        i = ((PackageInfo) (context)).versionCode;
        return i;
    }

    public String[] getDBData()
    {
        return dbData;
    }

    public String getDBName()
    {
        return dbName;
    }

    public String getDBTable()
    {
        return dbTable;
    }

    public int getDBVersion()
    {
        return dbVersion;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public int getVersionCode()
    {
        return versionCode;
    }

    public String toCSV()
    {
        String as[] = new String[dbData.length + 10];
        as[0] = packageName;
        as[1] = String.valueOf(versionCode);
        as[2] = dbName;
        as[3] = String.valueOf(dbVersion);
        as[4] = dbTable;
        for(int i = 5; i < 10; i++)
            as[i] = "";

        System.arraycopy(dbData, 0, as, 10, dbData.length);
        StringBuilder stringbuilder = new StringBuilder();
        int k = as.length;
        for(int j = 0; j < k; j++)
            stringbuilder.append(as[j]).append(",");

        return stringbuilder.substring(0, stringbuilder.length() - 1);
    }

    private static final int HEADER_SIZE = 10;
    private String dbData[];
    private String dbName;
    private String dbTable;
    private int dbVersion;
    private String packageName;
    private int versionCode;
}
