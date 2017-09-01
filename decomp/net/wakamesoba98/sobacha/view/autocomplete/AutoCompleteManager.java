// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.autocomplete;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.database.FriendsIdDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;

public class AutoCompleteManager
{

    public AutoCompleteManager()
    {
    }

    public String[] loadFriendsDatabase(Context context, long l)
    {
        Object obj = new FriendsIdDatabase(context, l);
        ((FriendsIdDatabase) (obj)).openDatabase();
        context = ((FriendsIdDatabase) (obj)).getAllAccount();
        ((FriendsIdDatabase) (obj)).closeDatabase();
        obj = new ArrayList();
        for(context = context.iterator(); context.hasNext(); ((List) (obj)).add(((AccountData)context.next()).getScreenName()));
        return (String[])((List) (obj)).toArray(new String[((List) (obj)).size()]);
    }
}
