// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.userlist;

import android.content.Context;
import java.util.*;
import net.wakamesoba98.sobacha.dialog.CheckBoxDialog;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.UserListApi;
import net.wakamesoba98.sobacha.twitter.listener.OnUserListsMembershipCheckedListener;
import twitter4j.User;
import twitter4j.UserList;

public class UserListDialogManager
    implements OnUserListsMembershipCheckedListener
{

    public UserListDialogManager(Context context1, UserAccount useraccount)
    {
        context = context1;
        userAccount = useraccount;
        spinnerDialog = new SpinnerDialog(context1);
        checkBoxDialog = new CheckBoxDialog() {

            public void onPositiveButtonClick(boolean aflag[])
            {
                UserListApi userlistapi = new UserListApi(context, userAccount);
                for(int i = 0; i < aflag.length; i++)
                {
                    long l = userListIds[i];
                    if(!userListChecks[i] && aflag[i])
                        userlistapi.createUserListMember(l, targetUserId);
                    if(userListChecks[i] && !aflag[i])
                        userlistapi.destroyUserListMember(l, targetUserId);
                }

            }

            final UserListDialogManager this$0;

            
            {
                this$0 = UserListDialogManager.this;
                super();
            }
        }
;
    }

    public void checkedUserListsMembership(Map map)
    {
        spinnerDialog.dismiss();
        if(map != null)
        {
            int j = map.size();
            boolean aflag[] = new boolean[j];
            for(int i = 0; i < j; i++)
            {
                boolean flag = ((Boolean)map.get(Long.valueOf(userListIds[i]))).booleanValue();
                aflag[i] = flag;
                userListChecks[i] = flag;
            }

            checkBoxDialog.build(context, 0x7f0700c1, userListNames, aflag);
        }
    }

    public void show(long l)
    {
        spinnerDialog.show();
        targetUserId = l;
        Object obj = userAccount.getUserLists();
        l = userAccount.getId();
        ArrayList arraylist = new ArrayList();
        obj = ((List) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            UserList userlist = (UserList)((Iterator) (obj)).next();
            if(userlist.getUser().getId() == l)
                arraylist.add(userlist);
        } while(true);
        int j = arraylist.size();
        userListIds = new long[j];
        userListNames = new String[j];
        userListChecks = new boolean[j];
        obj = new LinkedList();
        for(int i = 0; i < j; i++)
        {
            UserList userlist1 = (UserList)arraylist.get(i);
            userListIds[i] = userlist1.getId();
            userListNames[i] = userlist1.getName();
            ((Queue) (obj)).add(Long.valueOf(userlist1.getId()));
        }

        (new UserListApi(context, userAccount)).showUserListsMembership(this, ((Queue) (obj)), targetUserId);
    }

    private CheckBoxDialog checkBoxDialog;
    private Context context;
    private SpinnerDialog spinnerDialog;
    private long targetUserId;
    private UserAccount userAccount;
    private boolean userListChecks[];
    private long userListIds[];
    private String userListNames[];





}
