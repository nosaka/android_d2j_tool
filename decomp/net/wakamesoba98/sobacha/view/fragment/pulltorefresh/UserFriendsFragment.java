// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import net.wakamesoba98.sobacha.twitter.api.timeline.UserPageApi;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            CursorPullToRefreshFragment

public class UserFriendsFragment extends CursorPullToRefreshFragment
{

    public UserFriendsFragment()
    {
    }

    protected void initTimeLine()
    {
        if(adapter.getCount() == 0 && hasNext() && !isRefreshing())
        {
            showRefreshing();
            (new UserPageApi(getActivity(), adapter, userAccount)).getFriends(this, targetUserId, 0);
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        targetUserId = getArguments().getLong("user_id");
        adapter = ((AdapterHolder)getParentFragment()).getAdapter(EnumTab.FRIENDS);
        setAdapterToListView(adapter);
        setRefreshEnabled(false);
        initTimeLine();
    }

    public void pullToRefresh(EnumPullDirection enumpulldirection)
    {
        if(enumpulldirection == EnumPullDirection.UP)
            (new UserPageApi(getActivity(), adapter, userAccount)).getFriends(this, targetUserId, cursor, 0);
    }

    public void readMore(int i)
    {
    }

    private long targetUserId;
}
