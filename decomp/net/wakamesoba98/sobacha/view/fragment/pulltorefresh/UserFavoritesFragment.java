// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import net.wakamesoba98.sobacha.twitter.api.timeline.TimeLineApi;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import twitter4j.Paging;
import twitter4j.Status;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public class UserFavoritesFragment extends PullToRefreshFragment
{

    public UserFavoritesFragment()
    {
    }

    protected void initTimeLine()
    {
        if(adapter.getCount() == 0 && hasNext() && !isRefreshing())
        {
            showRefreshing();
            TimeLineApi timelineapi = new TimeLineApi(getActivity(), adapter, userAccount);
            Paging paging = timelineapi.getDefaultPaging();
            timelineapi.getFavorites(this, targetUserId, paging, 0);
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        targetUserId = getArguments().getLong("user_id");
        adapter = ((AdapterHolder)getParentFragment()).getAdapter(EnumTab.FAVORITES);
        setAdapterToListView(adapter);
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
        timelineapi.getFavorites(this, targetUserId, paging, 0);
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

    private long targetUserId;
}
