// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.item;

import android.os.AsyncTask;
import java.util.*;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;

// Referenced classes of package net.wakamesoba98.sobacha.view.listview.item:
//            MuteManager, StatusItem

public class MuteNotifyTask extends AsyncTask
{
    class RemoveItem
    {

        StatusAdapter getTargetAdapter()
        {
            return targetAdapter;
        }

        StatusItem getTargetItem()
        {
            return targetItem;
        }

        private StatusAdapter targetAdapter;
        private StatusItem targetItem;
        final MuteNotifyTask this$0;

        RemoveItem(StatusAdapter statusadapter, StatusItem statusitem)
        {
            this$0 = MuteNotifyTask.this;
            super();
            targetAdapter = statusadapter;
            targetItem = statusitem;
        }
    }


    public MuteNotifyTask(UserAccount useraccount)
    {
        account = useraccount;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((StatusAdapter[])aobj);
    }

    protected transient List doInBackground(StatusAdapter astatusadapter[])
    {
        ArrayList arraylist = new ArrayList();
        MuteManager mutemanager = new MuteManager();
        int k = astatusadapter.length;
        for(int i = 0; i < k; i++)
        {
            StatusAdapter statusadapter = astatusadapter[i];
            for(int j = 0; j < statusadapter.getCount(); j++)
            {
                StatusItem statusitem = (StatusItem)statusadapter.getItem(j);
                if(statusitem != null && statusitem.applyMute(account, mutemanager))
                    arraylist.add(new RemoveItem(statusadapter, statusitem));
            }

        }

        return arraylist;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((List)obj);
    }

    protected void onPostExecute(List list)
    {
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            Object obj = (RemoveItem)list.next();
            StatusAdapter statusadapter = ((RemoveItem) (obj)).getTargetAdapter();
            obj = ((RemoveItem) (obj)).getTargetItem();
            if(statusadapter != null && obj != null)
                statusadapter.remove(obj);
        } while(true);
    }

    private UserAccount account;
}
