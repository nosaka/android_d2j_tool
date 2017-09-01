// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.*;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.database.AccountsIdDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.twitter.api.LookupApi;
import net.wakamesoba98.sobacha.twitter.listener.OnLookupFinishedListener;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;
import net.wakamesoba98.sobacha.view.listview.adapter.SingleChoiceAccountAdapter;
import net.wakamesoba98.sobacha.view.listview.item.AccountListItem;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            LoginActivity

public class AccountsActivity extends ThemeActivity
    implements OnLookupFinishedListener
{

    public AccountsActivity()
    {
        selectedUserId = 0L;
        isFinishedByUser = true;
    }

    private void listViewSettings()
    {
        ListView listview = (ListView)findViewById(0x7f0e0077);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                adapterview = adapter.getItem(i);
                if(adapterview == null)
                    return;
                if(adapterview.isAddButton())
                {
                    startIntent();
                    return;
                }
                i = 0;
                while(i < adapter.getCount()) 
                {
                    view = adapter.getItem(i);
                    if(view != null)
                        if(adapter.getItemId(i) == l)
                            view.setSelected(true);
                        else
                            view.setSelected(false);
                    i++;
                }
                adapter.notifyDataSetChanged();
                selectedUserId = adapterview.getUserId();
            }

            final AccountsActivity this$0;

            
            {
                this$0 = AccountsActivity.this;
                super();
            }
        }
);
        listview.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                adapterview = adapter.getItem(i);
                if(adapterview != null)
                    if((l = adapterview.getUserId()) != selectedUserId)
                    {
                        (l. new ConfirmDialog() {

                            public void onPositiveButtonClick()
                            {
                                AccountsIdDatabase accountsiddatabase = new AccountsIdDatabase(_fld0);
                                accountsiddatabase.openDatabase();
                                accountsiddatabase.deleteData(String.valueOf(itemScreenName));
                                accountsiddatabase.closeDatabase();
                                loadAccountData();
                            }

                            final _cls2 this$1;
                            final long val$itemScreenName;

            
            {
                this$1 = final__pcls2;
                itemScreenName = J.this;
                super();
            }
                        }
).build(AccountsActivity.this, 0x7f07013b, 0x7f070025);
                        return true;
                    }
                return true;
            }

            final AccountsActivity this$0;

            
            {
                this$0 = AccountsActivity.this;
                super();
            }
        }
);
    }

    private void loadAccountData()
    {
        ids = new ArrayList();
        Object obj = new AccountsIdDatabase(this);
        ((AccountsIdDatabase) (obj)).openDatabase();
        List list = ((AccountsIdDatabase) (obj)).getAllAccount();
        ((AccountsIdDatabase) (obj)).closeDatabase();
        adapter.clear();
        obj = list.iterator();
        while(((Iterator) (obj)).hasNext()) 
        {
            AccountData accountdata = (AccountData)((Iterator) (obj)).next();
            AccountListItem accountlistitem = new AccountListItem(accountdata.getUserId(), accountdata.getScreenName(), accountdata.getUserName(), accountdata.getIconUrl());
            if(selectedUserId == accountlistitem.getUserId())
                accountlistitem.setSelected(true);
            else
                accountlistitem.setSelected(false);
            adapter.add(accountlistitem);
            ids.add(Long.valueOf(accountdata.getUserId()));
        }
        obj = new AccountListItem(-1L, null, null, null);
        ((AccountListItem) (obj)).setAddButton(true);
        adapter.add(obj);
        adapter.notifyDataSetChanged();
    }

    private void lookupUsers(List list)
    {
        (new LookupApi(this, ((SobaChaApplication)getApplication()).getUserAccount())).backgroundLookup(this, list);
    }

    private void startIntent()
    {
        startActivity(new Intent(this, net/wakamesoba98/sobacha/view/activity/LoginActivity));
        isFinishedByUser = false;
        finish();
    }

    public void finishedLookup()
    {
        loadAccountData();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001b);
        selectedUserId = ((SobaChaApplication)getApplication()).getUserId();
        adapter = new SingleChoiceAccountAdapter(this, 0, new ArrayList());
        listViewSettings();
        loadAccountData();
        if(adapter.getCount() <= 1)
        {
            startIntent();
            return;
        } else
        {
            lookupUsers(ids);
            return;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            SobaChaApplication sobachaapplication = (SobaChaApplication)getApplication();
            long l = sobachaapplication.getUserId();
            if(isFinishedByUser && adapter.getCount() > 0 && selectedUserId > 0L && selectedUserId != l)
            {
                sobachaapplication.changeAccount(selectedUserId);
                finish();
            }
        }
        return super.onKeyUp(i, keyevent);
    }

    private SingleChoiceAccountAdapter adapter;
    private List ids;
    private boolean isFinishedByUser;
    private long selectedUserId;





/*
    static long access$202(AccountsActivity accountsactivity, long l)
    {
        accountsactivity.selectedUserId = l;
        return l;
    }

*/

}
