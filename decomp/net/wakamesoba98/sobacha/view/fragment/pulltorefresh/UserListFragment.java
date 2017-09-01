// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.util.*;
import net.wakamesoba98.sobacha.database.LastUserListDatabase;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.UserListApi;
import net.wakamesoba98.sobacha.twitter.api.timeline.TimeLineApi;
import net.wakamesoba98.sobacha.twitter.listener.OnUserListMembersGotListener;
import net.wakamesoba98.sobacha.twitter.listener.OnUserListsGotListener;
import net.wakamesoba98.sobacha.twitter.stream.StreamManager;
import net.wakamesoba98.sobacha.view.activity.UserListManageActivity;
import net.wakamesoba98.sobacha.view.listview.adapter.*;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public class UserListFragment extends PullToRefreshFragment
    implements OnUserListsGotListener, OnUserListMembersGotListener
{

    public UserListFragment()
    {
    }

    private void addHeader()
    {
        View view = getActivity().getLayoutInflater().inflate(0x7f03004b, null);
        ViewGroup viewgroup = (ViewGroup)getView().findViewById(0x7f0e00db);
        if(view != null)
            viewgroup.addView(view);
    }

    private void getUserListStatuses(long l)
    {
        adapter.clear();
        showRefreshing();
        TimeLineApi timelineapi = new TimeLineApi(getActivity(), adapter, userAccount);
        timelineapi.getUserList(this, l, timelineapi.getDefaultPaging(), 0);
        (new UserListApi(getActivity(), userAccount)).getUserListMembers(this, l);
    }

    private void setHeaderProperties()
    {
        ThemeManager thememanager = new ThemeManager(getActivity());
        ImageButton imagebutton = (ImageButton)getView().findViewById(0x7f0e00f3);
        ImageButton imagebutton1 = (ImageButton)getView().findViewById(0x7f0e00f5);
        imagebutton.setImageResource(thememanager.getThemeDrawableId(0x7f020077));
        imagebutton1.setImageResource(thememanager.getThemeDrawableId(0x7f0200e4));
        imagebutton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view = new Intent(getActivity(), net/wakamesoba98/sobacha/view/activity/UserListManageActivity);
                getActivity().startActivity(view);
            }

            final UserListFragment this$0;

            
            {
                this$0 = UserListFragment.this;
                super();
            }
        }
);
        imagebutton1.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view = (UserList)spinner.getSelectedItem();
                if(view != null)
                {
                    long l = view.getId();
                    getUserListStatuses(l);
                    view = new LastUserListDatabase(getActivity(), userAccount.getId());
                    view.openDatabase();
                    view.putLastUserListId(l);
                    view.closeDatabase();
                    userAccount.setLastUserListId(l);
                }
            }

            final UserListFragment this$0;

            
            {
                this$0 = UserListFragment.this;
                super();
            }
        }
);
    }

    private void spinnerInitialize()
    {
        spinner = (Spinner)getView().findViewById(0x7f0e00f4);
        if(spinner != null)
        {
            spinner.setAdapter(null);
            UserListsSpinnerAdapter userlistsspinneradapter = new UserListsSpinnerAdapter(getActivity(), 0x7f030067, new ArrayList());
            userlistsspinneradapter.setDropDownViewResource(0x7f030066);
            List list = userAccount.getUserLists();
            if(list != null)
            {
                for(Iterator iterator = list.iterator(); iterator.hasNext(); userlistsspinneradapter.add((UserList)iterator.next()));
                boolean flag = false;
                long l = userAccount.getLastUserListId();
                int i = 0;
                do
                {
label0:
                    {
                        int j = ((flag) ? 1 : 0);
                        if(i < list.size())
                        {
                            if(((UserList)list.get(i)).getId() != l)
                                break label0;
                            j = i;
                        }
                        spinner.setAdapter(userlistsspinneradapter);
                        spinner.setSelection(j);
                        return;
                    }
                    i++;
                } while(true);
            }
        }
    }

    public void gotUserListMembers(Set set)
    {
        if(userAccount != null && userAccount.getStreamManager() != null)
            userAccount.getStreamManager().setUserListMembers(set);
    }

    public void gotUserLists(List list)
    {
        spinnerInitialize();
    }

    protected void initTimeLine()
    {
        spinnerInitialize();
        long l = userAccount.getLastUserListId();
        if(adapter.getCount() == 0 && hasNext() && !isRefreshing() && l > 0L)
            getUserListStatuses(l);
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        adapter = ((AdapterHolder)getActivity()).getAdapter(EnumTab.USER_LIST);
        setAdapterToListView(adapter);
        addHeader();
        setHeaderProperties();
        userAccount.setListsGotListener(this);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        userAccount.setListsGotListener(null);
    }

    public void onStart()
    {
        super.onStart();
        initTimeLine();
    }

    public void pullToRefresh(EnumPullDirection enumpulldirection)
    {
        if(adapter.getCount() <= 0) goto _L2; else goto _L1
_L1:
        TimeLineApi timelineapi;
        Paging paging;
        timelineapi = new TimeLineApi(getActivity(), adapter, userAccount);
        paging = timelineapi.getDefaultPaging();
        if(enumpulldirection != EnumPullDirection.DOWN) goto _L4; else goto _L3
_L3:
        enumpulldirection = (StatusItem)adapter.getItem(0);
        if(enumpulldirection != null)
            paging.setSinceId(enumpulldirection.getStatus().getId());
_L5:
        timelineapi.getUserList(this, userAccount.getLastUserListId(), paging, 0);
        return;
_L4:
        enumpulldirection = (StatusItem)adapter.getItem(adapter.getCount() - 1);
        if(enumpulldirection != null)
            paging.setMaxId(enumpulldirection.getStatus().getId() - 1L);
        if(true) goto _L5; else goto _L2
_L2:
        hideRefreshing();
        return;
    }

    public void readMore(int i)
    {
    }

    private Spinner spinner;


}
