// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
import net.wakamesoba98.sobacha.compatible.Flavor;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.dialog.UserListEditDialog;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.UserListApi;
import net.wakamesoba98.sobacha.twitter.listener.*;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;
import net.wakamesoba98.sobacha.view.listview.adapter.UserListsAdapter;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.User;
import twitter4j.UserList;

public class UserListManageActivity extends ThemeActivity
    implements OnUserListCreatedListener, OnUserListUpdatedListener, OnUserListDestroyedListener
{

    public UserListManageActivity()
    {
    }

    private void setButtonProperties()
    {
        ThemeManager thememanager = new ThemeManager(this);
        ImageButton imagebutton = (ImageButton)findViewById(0x7f0e0079);
        if(Flavor.isMateCha())
            imagebutton.setImageResource(0x7f02005c);
        else
            imagebutton.setImageResource(thememanager.getThemeDrawableId(0x7f02005d));
        imagebutton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new UserListEditDialog() {

                    public void onPositiveButtonClick(String s, boolean flag, String s1)
                    {
                        (new UserListApi(_fld0, userAccount)).createUserList(_fld0, s, flag, s1);
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
).build(UserListManageActivity.this, "", true, "");
            }

            final UserListManageActivity this$0;

            
            {
                this$0 = UserListManageActivity.this;
                super();
            }
        }
);
    }

    private void setClickListener(ListView listview)
    {
        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, final int position, long l)
            {
                adapterview = (UserList)adapter.getItem(position);
                if(adapterview.getUser().getId() == userAccount.getId())
                    (adapterview. new UserListEditDialog() {

                        public void onPositiveButtonClick(String s, boolean flag, String s1)
                        {
                            (new UserListApi(_fld0, userAccount)).updateUserList(_fld0, position, item.getId(), s, flag, s1);
                        }

                        final _cls2 this$1;
                        final UserList val$item;
                        final int val$position;

            
            {
                this$1 = final__pcls2;
                position = i;
                item = UserList.this;
                super();
            }
                    }
).build(UserListManageActivity.this, adapterview.getName(), adapterview.isPublic(), adapterview.getDescription());
            }

            final UserListManageActivity this$0;

            
            {
                this$0 = UserListManageActivity.this;
                super();
            }
        }
);
    }

    private void setLongClickListener(ListView listview)
    {
        listview.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView adapterview, View view, final int position, long l)
            {
                adapterview = (UserList)adapter.getItem(position);
                if(adapterview.getUser().getId() == userAccount.getId())
                    (adapterview. new ConfirmDialog() {

                        public void onPositiveButtonClick()
                        {
                            (new UserListApi(_fld0, userAccount)).destroyUserList(_fld0, position, item.getId());
                        }

                        final _cls3 this$1;
                        final UserList val$item;
                        final int val$position;

            
            {
                this$1 = final__pcls3;
                position = i;
                item = UserList.this;
                super();
            }
                    }
).build(UserListManageActivity.this, 0x7f07013b, 0x7f070024);
                return true;
            }

            final UserListManageActivity this$0;

            
            {
                this$0 = UserListManageActivity.this;
                super();
            }
        }
);
    }

    private void showUserLists()
    {
        adapter = new UserListsAdapter(this, 0x1090003, new ArrayList());
        ListView listview = (ListView)findViewById(0x7f0e0078);
        listview.setAdapter(adapter);
        if(userLists != null)
        {
            UserList userlist;
            for(Iterator iterator = userLists.iterator(); iterator.hasNext(); adapter.add(userlist))
                userlist = (UserList)iterator.next();

            adapter.notifyDataSetChanged();
        }
        setClickListener(listview);
        setLongClickListener(listview);
    }

    public void createdUserList(UserList userlist)
    {
        userLists.add(userlist);
        adapter.add(userlist);
    }

    public void destroyedUserList(int i)
    {
        userLists.remove(i);
        adapter.remove(adapter.getItem(i));
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001c);
        userAccount = ((SobaChaApplication)getApplication()).getUserAccount();
        userLists = userAccount.getUserLists();
        setButtonProperties();
        showUserLists();
    }

    public void updatedUserList(UserList userlist, int i)
    {
        userLists.remove(i);
        userLists.add(i, userlist);
        adapter.remove(adapter.getItem(i));
        adapter.insert(userlist, i);
    }

    private UserListsAdapter adapter;
    private UserAccount userAccount;
    private List userLists;


}
